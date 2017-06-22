package app.yellow.github.data.home.explore;

import java.util.List;

import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.RepositoryResponse;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.bean.home.explore.UserResponse;
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
    public Observable<List<RepositoryBean>> getRepositorys(int pageIndex) {
        return RetrofitUtil
                .getHomeService()
                .getExploreRepositoryData(pageIndex)
                .flatMap(new Func1<RepositoryResponse, Observable<List<RepositoryBean>>>() {
                    @Override
                    public Observable<List<RepositoryBean>> call(RepositoryResponse repositoryResponse) {
                        return Observable.from(repositoryResponse.getItems()).toList();
                    }
                });

    }

    @Override
    public Observable<List<UserBean>> getUsers(int pageIndex) {
        return RetrofitUtil
                .getHomeService()
                .getExploreUserData(pageIndex)
                .flatMap(new Func1<UserResponse, Observable<List<UserBean>>>() {
                    @Override
                    public Observable<List<UserBean>> call(UserResponse userResponse) {
                        return Observable.from(userResponse.getItems()).toList();
                    }
                });
    }

}
