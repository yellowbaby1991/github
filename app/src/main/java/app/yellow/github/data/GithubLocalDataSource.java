package app.yellow.github.data;

import com.alibaba.fastjson.JSON;

import java.util.List;

import app.yellow.github.base.BaseLocalOnSubscribe;
import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.SearchParams;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.data.db.KeyFactory;
import rx.Observable;

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
        return Observable.create(new BaseLocalOnSubscribe<RepositoryBean>(KeyFactory.getKeyByParams(params)) {
            @Override
            protected List paresToList(String json) {
                return JSON.parseArray(json, RepositoryBean.class);
            }
        });
    }

    @Override
    public Observable getUsersRepositoryList(String username, int page, String seachType) {
        return Observable.create(new BaseLocalOnSubscribe<RepositoryBean>(KeyFactory.getRepositoryKey(username, page, seachType)) {
            @Override
            protected List paresToList(String json) {
                return JSON.parseArray(json, RepositoryBean.class);
            }
        });
    }

    @Override
    public Observable getUserListByParams(SearchParams params) {
        return Observable.create(new BaseLocalOnSubscribe<UserBean>(KeyFactory.getKeyByParams(params)) {
            @Override
            protected List paresToList(String json) {
                return JSON.parseArray(json, UserBean.class);
            }
        });
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
