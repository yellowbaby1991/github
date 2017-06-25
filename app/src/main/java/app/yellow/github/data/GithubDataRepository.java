package app.yellow.github.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import app.yellow.github.bean.home.explore.SearchParams;
import rx.Observable;

public class GithubDataRepository implements GithubDataSource {

    @Nullable
    protected static GithubDataRepository INSTANCE = null;

    @NonNull
    protected GithubDataSource mRemoteDataSource;

    @NonNull
    protected GithubLocalDataSource mLocalDataSource;

    // Prevent direct instantiation.
    private GithubDataRepository(GithubDataSource remoteDataSource,
                                 GithubLocalDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static GithubDataRepository getInstance(GithubDataSource remoteDataSource,
                                                   GithubLocalDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new GithubDataRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }


    public Observable getRepositoryListByParams(SearchParams params) {

        //  https://api.github.com/search/repositories?q=language:java&page=1&per_page=10
        final StringBuilder url = new StringBuilder("");
        url.append("search/");
        url.append(params.type + "?");
        url.append("q=" + params.key + "?");
        url.append("page=" + params.page + "&");
        url.append("par_page=" + params.pageSize);

        Observable localTask = mLocalDataSource.getRepositoryListByParams(params);
        return localTask;

        /*Observable remoteTask = mRemoteDataSource.getRepositoryListByParams(params).doOnNext(new Action1<List<RepositoryBean>>() {
            @Override
            public void call(List<RepositoryBean> repositoryList) {
                DbBean dbBean = new DbBean();
                dbBean.setUrl("1");
                dbBean.setJson(JSON.toJSONString(repositoryList));
                dbBean.setDate(String.valueOf(System.currentTimeMillis()));
                mLocalDataSource.saveDbBean(dbBean);
            }
        });*/

        //return remoteTask;

        //return Observable.concat(localTask, remoteTask).first();
    }

    @Override
    public Observable getUsersRepositoryList(String username, int page, String seachType) {
        return mRemoteDataSource.getUsersRepositoryList(username, page, seachType);
    }

    @Override
    public Observable getUserListByParams(SearchParams params) {
        return mRemoteDataSource.getUserListByParams(params);
    }

    @Override
    public Observable getUserByName(String name) {
        return mRemoteDataSource
                .getUserByName(name);
    }

    @Override
    public Observable loginWithAuth(String baseAuth) {
        return mRemoteDataSource.loginWithAuth(baseAuth);
    }

    @Override
    public Observable getFollowing(String username, int page) {
        return mRemoteDataSource.getFollowing(username, page);
    }

    @Override
    public Observable getFollowers(String username, int page) {
        return mRemoteDataSource.getFollowers(username, page);
    }
}
