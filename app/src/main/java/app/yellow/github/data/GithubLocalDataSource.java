package app.yellow.github.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.SearchParams;
import app.yellow.github.data.db.DbBean;
import app.yellow.github.data.db.GithubDbHelper;
import app.yellow.github.data.db.GithubPersistenceContract.GithubEntry;
import app.yellow.github.util.UIUtils;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class GithubLocalDataSource implements GithubDataSource {

    private static GithubLocalDataSource INSTANCE;

    private final BriteDatabase mDatabaseHelper;

    public GithubLocalDataSource() {
        GithubDbHelper dbHelper = new GithubDbHelper(UIUtils.getContext());
        SqlBrite sqlBrite = SqlBrite.create();
        mDatabaseHelper = sqlBrite.wrapDatabaseHelper(dbHelper, Schedulers.io());
    }

    public static GithubLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GithubLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable getRepositoryListByParams(SearchParams params) {
        //  https://api.github.com/search/repositories?q=language:java&page=1&per_page=10
        StringBuilder url = new StringBuilder("");
        url.append("search/");
        url.append(params.type + "?");
        url.append("q=" + params.key + "?");
        url.append("page=" + params.page + "&");
        url.append("par_page=" + params.pageSize);

        String[] projection = {
                GithubEntry.COLUMN_NAME_URL,
                GithubEntry.COLUMN_NAME_JSON,
                GithubEntry.COLUMN_NAME_DATE
        };
        String sql = String.format("SELECT %s FROM %s WHERE %s LIKE ?",
                TextUtils.join(",", projection), GithubEntry.TABLE_NAME, GithubEntry.COLUMN_NAME_URL);

        return mDatabaseHelper
                .createQuery(GithubEntry.TABLE_NAME, "SELECT url,json,date FROM github_db where url=?", "1")
                .mapToOne(new Func1<Cursor, DbBean>() {
                    @Override
                    public DbBean call(Cursor cursor) {
                        String url = cursor.getString(cursor.getColumnIndex(GithubEntry.COLUMN_NAME_URL));
                        String json = cursor.getString(cursor.getColumnIndex(GithubEntry.COLUMN_NAME_JSON));
                        String date = cursor.getString(cursor.getColumnIndex(GithubEntry.COLUMN_NAME_DATE));
                        DbBean dbBean = new DbBean(url, json, date);
                        return dbBean;

                    }
                })
                .map(new Func1<DbBean, List<RepositoryBean>>() {
                    @Override
                    public List<RepositoryBean> call(DbBean dbBean) {
                        List<RepositoryBean> list = JSON.parseArray(dbBean.getJson(), RepositoryBean.class);
                        return list;
                    }
                });
    }

    @Override
    public Observable getUsersRepositoryList(String username, int page, String seachType) {
        return null;
    }

    @Override
    public Observable getUserListByParams(SearchParams params) {
        return null;
    }

    @Override
    public Observable getUserByName(String name) {
        return null;
    }

    @Override
    public Observable loginWithAuth(String baseAuth) {
        return null;
    }

    @Override
    public Observable getFollowing(String username, int page) {
        return null;
    }

    @Override
    public Observable getFollowers(String username, int page) {
        return null;
    }

    public void saveDbBean(DbBean dbBean) {
        ContentValues values = new ContentValues();
        values.put(GithubEntry.COLUMN_NAME_URL, dbBean.getUrl());
        values.put(GithubEntry.COLUMN_NAME_JSON, dbBean.getJson());
        values.put(GithubEntry.COLUMN_NAME_DATE, dbBean.getDate());
        mDatabaseHelper.insert(GithubEntry.TABLE_NAME, values, SQLiteDatabase.CONFLICT_REPLACE);
    }
}
