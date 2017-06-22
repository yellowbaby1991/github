package app.yellow.github.data.home.explore;

import app.yellow.github.bean.home.explore.SearchParams;
import app.yellow.github.data.DataRepository;
import rx.Observable;

public class ExploreDataRepository extends DataRepository<ExploreDataSource> implements ExploreDataSource {

    // Prevent direct instantiation.
    private ExploreDataRepository(ExploreDataSource remoteDataSource,
                                  ExploreDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static ExploreDataRepository getInstance(ExploreDataSource remoteDataSource,
                                                    ExploreDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ExploreDataRepository(remoteDataSource, localDataSource);
        }
        return (ExploreDataRepository) INSTANCE;
    }


    public Observable getRepositoryListByParams(SearchParams params) {
        return mRemoteDataSource.getRepositoryListByParams(params);
    }

    @Override
    public Observable getUserListByParams(SearchParams params) {
        return mRemoteDataSource.getUserListByParams(params);
    }
}
