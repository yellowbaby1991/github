package app.yellow.github.data.home.explore;

import java.util.List;

import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.UserBean;
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

    @Override
    public Observable<List<RepositoryBean>> getRepositorys(int pageIndex) {
        return mRemoteDataSource.getRepositorys(pageIndex);
    }

    @Override
    public Observable<List<UserBean>> getUsers(int pageIndex) {
        return mRemoteDataSource.getUsers(pageIndex);
    }
}
