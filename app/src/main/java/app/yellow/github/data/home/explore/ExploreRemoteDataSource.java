package app.yellow.github.data.home.explore;

import java.util.List;

import app.yellow.github.util.RetrofitUtil;
import rx.Observable;
import rx.functions.Func1;

public class ExploreRemoteDataSource implements ExploreDataSource {

    private static ExploreRemoteDataSource INSTANCE;

    public static ExploreRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ExploreRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<RepositoryBean>> getRepositorys() {
        return RetrofitUtil
                .getHomeService()
                .getExploreRepositoryData()
                .flatMap(new Func1<RepositoryResponse, Observable<List<RepositoryBean>>>() {
                    @Override
                    public Observable<List<RepositoryBean>> call(RepositoryResponse repositoryResponse) {
                        return Observable.from(repositoryResponse.getItems()).toList();
                    }
                });

    }

}
