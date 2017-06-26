package app.yellow.github.data;

import com.alibaba.fastjson.JSON;

import org.litepal.crud.DataSupport;

import java.util.List;

import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.SearchParams;
import app.yellow.github.config.GithubConfig;
import app.yellow.github.data.db.KeyJsonBean;
import app.yellow.github.util.Constants;
import rx.Observable;
import rx.Subscriber;

public class GithubLocalDataSource implements GithubDataSource {

    private static GithubLocalDataSource INSTANCE;

    private GithubLocalDataSource() {

    }

    public static GithubLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GithubLocalDataSource();
        }
        return INSTANCE;
    }


    @Override
    public Observable getRepositoryListByParams(final SearchParams params) {
        return Observable.create(new Observable.OnSubscribe<List<RepositoryBean>>() {
            @Override
            public void call(Subscriber<? super List<RepositoryBean>> subscriber) {
                List<KeyJsonBean> beans = DataSupport.where("key =?", Constants.getKeyByParams(params)).find(KeyJsonBean.class);
                if (beans == null || beans.isEmpty()) {
                    subscriber.onCompleted();
                } else {
                    String data = beans.get(0).getDate();
                    if (System.currentTimeMillis() - Long.valueOf(data) > GithubConfig.CAHE_TIME) {
                        beans.get(0).delete();
                        subscriber.onCompleted();
                        return;
                    }
                    String json = beans.get(0).getJson();
                    List<RepositoryBean> list = JSON.parseArray(json, RepositoryBean.class);
                    subscriber.onNext(list);
                }
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

}
