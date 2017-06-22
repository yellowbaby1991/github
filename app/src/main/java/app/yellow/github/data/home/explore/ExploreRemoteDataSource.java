package app.yellow.github.data.home.explore;

import java.util.List;

import app.yellow.github.bean.home.explore.BaseResponse;
import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.SearchParams;
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
    public Observable getRepositoryListByParams(SearchParams params) {
        return RetrofitUtil
                .getRepositroyService()
                .loadData(params.type, params.key, params.page, params.pageSize, params.sort, params.order)
                .flatMap(new Func1<BaseResponse, Observable<List<RepositoryBean>>>() {
                    @Override
                    public Observable<List<RepositoryBean>> call(BaseResponse baseResponse) {
                        return Observable.from(baseResponse.getItems()).toList();
                    }
                });
    }

    @Override
    public Observable getUserListByParams(SearchParams params) {
        return RetrofitUtil
                .getUserService()
                .loadData(params.type, params.key, params.page, params.pageSize, params.sort, params.order)
                .flatMap(new Func1<BaseResponse, Observable<List<RepositoryBean>>>() {
                    @Override
                    public Observable<List<RepositoryBean>> call(BaseResponse baseResponse) {
                        return Observable.from(baseResponse.getItems()).toList();
                    }
                });
    }

}
