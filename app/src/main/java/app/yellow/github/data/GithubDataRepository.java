package app.yellow.github.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import app.yellow.github.base.BaseSaveAction;
import app.yellow.github.bean.home.event.EventBean;
import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.SearchParams;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.bean.repositorydetail.RepositoryDetailBean;
import app.yellow.github.bean.userdetail.UserDetailBean;
import app.yellow.github.data.db.KeyFactory;
import rx.Observable;

public class GithubDataRepository implements GithubDataSource {

    @Nullable
    protected static GithubDataRepository INSTANCE = null;

    @NonNull
    protected GithubDataSource mRemoteDataSource;

    @NonNull
    protected GithubDataSource mLocalDataSource;

    // Prevent direct instantiation.
    private GithubDataRepository(GithubDataSource remoteDataSource,
                                 GithubDataSource localDataSource) {
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
        final String url = KeyFactory.getKeyByParams(params);

        Observable localTask = mLocalDataSource.getRepositoryListByParams(params);

        Observable remoteTask = mRemoteDataSource.getRepositoryListByParams(params).doOnNext(new BaseSaveAction<List<RepositoryBean>>(url));

        return Observable.concat(localTask, remoteTask).first();
    }

    @Override
    public Observable getRepostitoryByUrl(String url, int page) {

        final String key = KeyFactory.getUrlKey(url, page);

        Observable localTask = mLocalDataSource.getRepostitoryByUrl(url, page);

        Observable remoteTask = mRemoteDataSource.getRepostitoryByUrl(url, page).doOnNext(new BaseSaveAction<List<RepositoryBean>>(key));

        return Observable.concat(localTask, remoteTask).first();
    }

    @Override
    public Observable getUserListByParams(SearchParams params) {

        final String url = KeyFactory.getKeyByParams(params);

        Observable localTask = mLocalDataSource.getUserListByParams(params);

        Observable remoteTask = mRemoteDataSource.getUserListByParams(params).doOnNext(new BaseSaveAction<List<UserBean>>(url));

        return Observable.concat(localTask, remoteTask).first();

    }

    @Override
    public Observable getUsersRepositoryList(String username, int page, String seachType) {

        final String url = KeyFactory.getRepositoryKey(username, page, seachType);

        Observable localTask = mLocalDataSource.getUsersRepositoryList(username, page, seachType);

        Observable remoteTask = mRemoteDataSource.getUsersRepositoryList(username, page, seachType).doOnNext(new BaseSaveAction<List<RepositoryBean>>(url));

        return Observable.concat(localTask, remoteTask).first();

    }


    @Override
    public Observable getUserByName(String name) {

        final String url = KeyFactory.getUserKey(name);

        Observable localTask = mLocalDataSource.getUserByName(name);

        Observable remoteTask = mRemoteDataSource.getUserByName(name).doOnNext(new BaseSaveAction<List<UserDetailBean>>(url));

        return Observable.concat(localTask, remoteTask).first();

    }


    @Override
    public Observable getFollowing(String username, int page) {

        final String url = KeyFactory.getFollowingKey(username, page);

        Observable localTask = mLocalDataSource.getFollowing(username, page);

        Observable remoteTask = mRemoteDataSource.getFollowing(username, page).doOnNext(new BaseSaveAction<List<UserDetailBean>>(url));

        return Observable.concat(localTask, remoteTask).first();

    }

    @Override
    public Observable getFollowers(String username, int page) {

        final String url = KeyFactory.getFollowerKey(username, page);

        Observable localTask = mLocalDataSource.getFollowers(username, page);

        Observable remoteTask = mRemoteDataSource.getFollowers(username, page).doOnNext(new BaseSaveAction<List<UserDetailBean>>(url));

        return Observable.concat(localTask, remoteTask).first();
    }

    @Override
    public Observable getUsersByUrl(String url, int page) {

        final String key = KeyFactory.getUrlKey(url, page);

        Observable localTask = mLocalDataSource.getUsersByUrl(url, page);

        Observable remoteTask = mRemoteDataSource.getUsersByUrl(url, page).doOnNext(new BaseSaveAction<List<UserBean>>(key));

        return Observable.concat(localTask, remoteTask).first();
    }

    @Override
    public Observable getEvents(String username, int page, String seachType, String reposname) {

        final String url = KeyFactory.getEventKey(username, page, seachType, reposname);

        Observable localTask = mLocalDataSource.getEvents(username, page, seachType, reposname);

        Observable remoteTask = mRemoteDataSource.getEvents(username, page, seachType, reposname).doOnNext(new BaseSaveAction<List<EventBean>>(url));

        return Observable.concat(localTask, remoteTask).first();

    }

    @Override
    public Observable getRepositoryByFullName(String name) {

        Observable localTask = mLocalDataSource.getRepositoryByFullName(name);

        Observable remoteTask = mRemoteDataSource.getRepositoryByFullName(name).doOnNext(new BaseSaveAction<List<RepositoryDetailBean>>(name));

        return Observable.concat(localTask, remoteTask).first();
    }

    @Override
    public Observable checkRepBeingStarred(String owner, String repo) {
        return mRemoteDataSource.checkRepBeingStarred(owner, repo);
    }

    @Override
    public Observable starRepo(String owner, String repo) {
        return mRemoteDataSource.starRepo(owner, repo);
    }

    @Override
    public Observable unStarRepo(String owner, String repo) {
        return mRemoteDataSource.unStarRepo(owner, repo);
    }

    @Override
    public Observable forkRepo(String owner, String repo) {
        return mRemoteDataSource.forkRepo(owner, repo);
    }

    @Override
    public Observable checkUserBeingFollowed(String user) {
        return mRemoteDataSource.checkUserBeingFollowed(user);
    }

    @Override
    public Observable followUser(String user) {
        return mRemoteDataSource.followUser(user);
    }

    @Override
    public Observable unFollowUser(String user) {
        return mRemoteDataSource.unFollowUser(user);
    }

    @Override
    public Observable loadContentByUrl(String url) {
        return mRemoteDataSource.loadContentByUrl(url);
    }

    @Override
    public Observable loadContentListByUrl(String url) {
        return mRemoteDataSource.loadContentListByUrl(url);
    }

    @Override
    public Observable loginWithAuth(String baseAuth) {
        return mRemoteDataSource.loginWithAuth(baseAuth);
    }
}