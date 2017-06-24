package app.yellow.github.data;

import java.util.List;

import app.yellow.github.api.AuthService;
import app.yellow.github.base.BaseResponse;
import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.SearchParams;
import app.yellow.github.bean.login.AuthorizationResponse;
import app.yellow.github.bean.login.CreateAuthorization;
import app.yellow.github.bean.userdetail.UserDetailBean;
import app.yellow.github.bean.userdetail.UserDetailResponse;
import app.yellow.github.config.GithubConfig;
import app.yellow.github.util.Constants;
import app.yellow.github.util.RetrofitUtil;
import app.yellow.github.util.SPUtils;
import app.yellow.github.util.UIUtils;
import rx.Observable;
import rx.functions.Func1;

public class GithubRemoteDataSource implements GithubDataSource {

    private static GithubRemoteDataSource INSTANCE;

    public static GithubRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GithubRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable getRepositoryListByParams(SearchParams params) {
        return RetrofitUtil
                .getRepositroyService()
                .getRepositoryListsByParams(params.type, params.key, params.page, params.pageSize, params.sort, params.order)
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
                .getUserListsByParams(params.type, params.key, params.page, params.pageSize, params.sort, params.order)
                .flatMap(new Func1<BaseResponse, Observable<List<RepositoryBean>>>() {
                    @Override
                    public Observable<List<RepositoryBean>> call(BaseResponse baseResponse) {
                        return Observable.from(baseResponse.getItems()).toList();
                    }
                });
    }

    @Override
    public Observable getUserByName(String name) {
        return RetrofitUtil
                .getUserService()
                .getUserByName(name)
                .flatMap(new Func1<UserDetailResponse, Observable<UserDetailBean>>() {
                    @Override
                    public Observable<UserDetailBean> call(UserDetailResponse response) {
                        return Observable.just(createUserDetailBean(response));
                    }

                });
    }

    @Override
    public Observable login(String username, String password) {

        final AuthService authService = RetrofitUtil.getAutoService(username, password);

        CreateAuthorization createAuthorization = new CreateAuthorization();
        createAuthorization.note = GithubConfig.NOTE;
        createAuthorization.client_id = GithubConfig.CLIENT_ID;
        createAuthorization.client_secret = GithubConfig.CLIENT_SECRET;
        createAuthorization.scopes = GithubConfig.SCOPES;

        return authService
                .createAuthorization(createAuthorization)
                .flatMap(new Func1<AuthorizationResponse, Observable<UserDetailResponse>>() {
                    @Override
                    public Observable<UserDetailResponse> call(AuthorizationResponse authorizationResponse) {
                        String token = authorizationResponse.getToken();
                        SPUtils.putString(UIUtils.getContext(), Constants.SP_TOKEN, token);
                        return authService.getUserInfo(token);
                    }
                })
                .flatMap(new Func1<UserDetailResponse, Observable<UserDetailBean>>() {
                    @Override
                    public Observable<UserDetailBean> call(UserDetailResponse response) {
                        return Observable.just(createUserDetailBean(response));
                    }
                });
    }

    private UserDetailBean createUserDetailBean(UserDetailResponse bean) {
        UserDetailBean userDetailBean = new UserDetailBean();
        userDetailBean.blog = bean.getBlog();
        userDetailBean.company = bean.getCompany();
        userDetailBean.email = (bean.getEmail() == null ? "Not set" : bean.getEmail().toString());
        userDetailBean.followersCount = bean.getFollowers() + "";
        userDetailBean.followingCount = bean.getFollowing() + "";
        userDetailBean.gistsCount = bean.getPublic_gists() + "";
        userDetailBean.jointime = bean.getCreated_at();
        userDetailBean.location = bean.getLocation();
        userDetailBean.name = bean.getName();
        userDetailBean.repositorysCount = bean.getPublic_repos() + "";
        return userDetailBean;
    }

}
