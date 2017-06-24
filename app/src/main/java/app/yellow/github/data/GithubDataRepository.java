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
    protected GithubDataSource mLocalDataSource;

    // Prevent direct instantiation.
    private GithubDataRepository(GithubDataSource remoteDataSource,
                                 GithubDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static GithubDataRepository getInstance(GithubDataSource remoteDataSource,
                                                   GithubDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new GithubDataRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }


    public Observable getRepositoryListByParams(SearchParams params) {
        return mRemoteDataSource.getRepositoryListByParams(params);
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
    public Observable login(String username, String password) {
        return mRemoteDataSource.login(username,password);
    }
}
