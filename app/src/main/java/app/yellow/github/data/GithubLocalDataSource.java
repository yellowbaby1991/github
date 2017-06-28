package app.yellow.github.data;

import com.alibaba.fastjson.JSON;

import java.util.List;

import app.yellow.github.base.BaseLocalListOnSubscribe;
import app.yellow.github.base.BaseLocalOnSubscribe;
import app.yellow.github.bean.home.event.EventBean;
import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.SearchParams;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.bean.repositorydetail.RepositoryDetailBean;
import app.yellow.github.bean.userdetail.UserDetailBean;
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
        return Observable.create(new BaseLocalListOnSubscribe<RepositoryBean>(KeyFactory.getKeyByParams(params)) {
            @Override
            protected List paresToList(String json) {
                return JSON.parseArray(json, RepositoryBean.class);
            }
        });
    }

    @Override
    public Observable getUsersRepositoryList(String username, int page, String seachType) {
        return Observable.create(new BaseLocalListOnSubscribe<RepositoryBean>(KeyFactory.getRepositoryKey(username, page, seachType)) {
            @Override
            protected List paresToList(String json) {
                return JSON.parseArray(json, RepositoryBean.class);
            }
        });
    }

    @Override
    public Observable getUserListByParams(SearchParams params) {
        return Observable.create(new BaseLocalListOnSubscribe<UserBean>(KeyFactory.getKeyByParams(params)) {
            @Override
            protected List paresToList(String json) {
                return JSON.parseArray(json, UserBean.class);
            }
        });
    }

    @Override
    public Observable getUserByName(String name) {
        return Observable.create(new BaseLocalOnSubscribe<UserDetailBean>(KeyFactory.getUserKey(name)) {
            @Override
            protected UserDetailBean paresToBean(String json) {
                return JSON.parseObject(json, UserDetailBean.class);
            }
        });
    }

    @Override
    public Observable getRepostitoryByUrl(String url, int page) {
        return Observable.create(new BaseLocalListOnSubscribe(KeyFactory.getUrlKey(url, page)) {
            @Override
            protected List<RepositoryBean> paresToList(String json) {
                return JSON.parseArray(json, RepositoryBean.class);
            }
        });
    }


    @Override
    public Observable loginWithAuth(String baseAuth) {
        return null;
    }

    @Override
    public Observable getFollowing(String username, int page) {
        return Observable.create(new BaseLocalListOnSubscribe<UserBean>(KeyFactory.getFollowingKey(username, page)) {
            @Override
            protected List paresToList(String json) {
                return JSON.parseArray(json, UserBean.class);
            }
        });
    }

    @Override
    public Observable getFollowers(String username, int page) {
        return Observable.create(new BaseLocalListOnSubscribe<UserBean>(KeyFactory.getFollowerKey(username, page)) {
            @Override
            protected List paresToList(String json) {
                return JSON.parseArray(json, UserBean.class);
            }
        });
    }

    @Override
    public Observable getUsersByUrl(String url, int page) {
        return Observable.create(new BaseLocalListOnSubscribe<UserBean>(KeyFactory.getUrlKey(url, page)) {
            @Override
            protected List paresToList(String json) {
                return JSON.parseArray(json, UserBean.class);
            }
        });
    }

    @Override
    public Observable getEvents(String username, int page, String seachType, String reposname) {
        return Observable.create(new BaseLocalListOnSubscribe<EventBean>(KeyFactory.getEventKey(username, page, seachType, reposname)) {
            @Override
            protected List paresToList(String json) {
                return JSON.parseArray(json, EventBean.class);
            }
        });
    }

    @Override
    public Observable getRepositoryByFullName(String name) {
        return Observable.create(new BaseLocalOnSubscribe<RepositoryDetailBean>(name) {
            @Override
            protected RepositoryDetailBean paresToBean(String json) {
                return JSON.parseObject(json, RepositoryDetailBean.class);
            }
        });
    }

    @Override
    public Observable checkRepBeingStarred(String owner, String repo) {
        return null;
    }

    @Override
    public Observable starRepo(String owner, String repo) {
        return null;
    }

    @Override
    public Observable unStarRepo(String owner, String repo) {
        return null;
    }

    @Override
    public Observable forkRepo(String owner, String repo) {
        return null;
    }

    @Override
    public Observable checkUserBeingFollowed(String user) {
        return null;
    }

    @Override
    public Observable followUser(String user) {
        return null;
    }

    @Override
    public Observable unFollowUser(String user) {
        return null;
    }

    @Override
    public Observable loadContentByUrl(String url) {
        return null;
    }

}
