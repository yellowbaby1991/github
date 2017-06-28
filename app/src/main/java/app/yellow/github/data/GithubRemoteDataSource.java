package app.yellow.github.data;

import android.text.format.Formatter;

import java.util.List;

import app.yellow.github.bean.repositorydetail.RepositoryDetailBean;
import app.yellow.github.bean.userdetail.UserDetailResponse;
import app.yellow.github.core.home.event.EventFragment;
import app.yellow.github.data.api.AuthService;
import app.yellow.github.data.api.RepositoryService;
import app.yellow.github.base.BaseResponse;
import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.SearchParams;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.bean.login.AuthorizationResponse;
import app.yellow.github.bean.login.CreateAuthorization;
import app.yellow.github.bean.userdetail.UserDetailBean;
import app.yellow.github.config.GithubConfig;
import app.yellow.github.core.home.repository.RepositoryFragment;
import app.yellow.github.util.ActivityUtils;
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
    public Observable getUsersRepositoryList(String username, int page, String seachType) {
        RepositoryService service = RetrofitUtil
                .getRepositroyService();
        switch (seachType) {
            case RepositoryFragment.SEACH_PUBLIC_REP:
                return service.getPublicRepositoryList(username, page, GithubConfig.PER_PAGE);
            case RepositoryFragment.SEACH_STARRED:
                return service.getStaredRepositoryList(username, page, GithubConfig.PER_PAGE);
            case RepositoryFragment.SEACH_ALL_REP:
                return service.getUserAllRepositoryList(page, GithubConfig.PER_PAGE);
        }
        return null;
    }

    @Override
    public Observable getUserListByParams(SearchParams params) {
        return RetrofitUtil
                .getUserService()
                .getUserListsByParams(params.type, params.key, params.page, params.pageSize, params.sort, params.order)
                .flatMap(new Func1<BaseResponse, Observable<List<UserBean>>>() {
                    @Override
                    public Observable<List<UserBean>> call(BaseResponse baseResponse) {
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
    public Observable getRepositoryByFullName(String name) {
        return RetrofitUtil
                .getRepositroyService()
                .getRepositoryByFullName(name)
                .flatMap(new Func1<RepositoryBean, Observable<RepositoryDetailBean>>() {
                    @Override
                    public Observable<RepositoryDetailBean> call(RepositoryBean bean) {
                        return Observable.just(createRepositoryDetailBean(bean));
                    }
                });
    }

    @Override
    public Observable checkRepBeingStarred(String owner, String repo) {
        return RetrofitUtil.getRepositroyService().checkRepBeingStarred(owner, repo);
    }

    @Override
    public Observable starRepo(String owner, String repo) {
        return RetrofitUtil.getRepositroyService().starRepo(owner, repo);
    }

    @Override
    public Observable unStarRepo(String owner, String repo) {
        return RetrofitUtil.getRepositroyService().unStarRepo(owner, repo);
    }

    @Override
    public Observable forkRepo(String owner, String repo) {
        return RetrofitUtil.getRepositroyService().forkRepo(owner, repo);
    }

    @Override
    public Observable checkUserBeingFollowed(String user) {
        return RetrofitUtil.getUserService().checkUserBeingFollowed(user);
    }

    @Override
    public Observable followUser(String user) {
        return RetrofitUtil.getUserService().followUser(user);
    }

    @Override
    public Observable unFollowUser(String user) {
        return RetrofitUtil.getUserService().unFollowUser(user);
    }

    @Override
    public Observable loadContentByUrl(String url) {
        return RetrofitUtil.getContentService().getContentByUrl(url);
    }

    @Override
    public Observable loginWithAuth(String basicAuth) {

        SPUtils.putString(UIUtils.getContext(), Constants.SP_BASEAUTH, basicAuth);

        final AuthService authService = RetrofitUtil.getAutoService(basicAuth);

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

    @Override
    public Observable getFollowing(String username, int page) {
        return RetrofitUtil
                .getUserService().getUserFollowing(username, page, GithubConfig.PER_PAGE);
    }

    @Override
    public Observable getFollowers(String username, int page) {
        return RetrofitUtil
                .getUserService().getUserFollowers(username, page, GithubConfig.PER_PAGE);
    }

    @Override
    public Observable getUsersByUrl(String url, int page) {
        return RetrofitUtil
                .getUserService().getUsersByUrl(url, page, GithubConfig.PER_PAGE);
    }

    @Override
    public Observable getEvents(String username, int page, String seachType, String reposname) {
        if (seachType.equals(EventFragment.SEACH_BY_USER)) {
            return RetrofitUtil
                    .getEventService()
                    .getUserEvents(username, page, GithubConfig.PER_PAGE);
        }
        return RetrofitUtil
                .getEventService()
                .getRepEvents(username, reposname, page, GithubConfig.PER_PAGE);
    }

    @Override
    public Observable getRepostitoryByUrl(String url, int page) {
        return RetrofitUtil.getRepositroyService().getRepositoryByUrl(url, page, GithubConfig.PER_PAGE);
    }


    protected RepositoryDetailBean createRepositoryDetailBean(RepositoryBean bean) {
        RepositoryDetailBean detailBean = new RepositoryDetailBean();
        detailBean.name = bean.getName();
        detailBean.authority = (bean.isPrivateX() ? "Private" : "Public");
        detailBean.avatarUrl = bean.getOwner().getAvatar_url();
        detailBean.capacity = Formatter.formatFileSize(UIUtils.getContext(), Long.valueOf(bean.getSize()));
        detailBean.createdAt = ActivityUtils.dealDataString(bean.getCreated_at());
        detailBean.lastUpdated = ActivityUtils.dealDataString(bean.getUpdated_at());
        detailBean.description = bean.getDescription();
        detailBean.forksCount = bean.getForks_count() + "";
        detailBean.stargazersCount = bean.getStargazers_count() + "";
        detailBean.issuesCount = bean.getOpen_issues_count() + "";
        detailBean.owener = bean.getOwner().getLogin();
        detailBean.language = bean.getLanguage();
        detailBean.forks_url = bean.getForks_url();
        detailBean.stargazers_url = bean.getStargazers_url();
        detailBean.contributors_url = bean.getContributors_url();
        detailBean.contents_url = bean.getContents_url();
        return detailBean;
    }

    private UserDetailBean createUserDetailBean(UserDetailResponse bean) {
        UserDetailBean userDetailBean = new UserDetailBean();
        userDetailBean.avatarUrl = bean.getAvatar_url();
        userDetailBean.blog = bean.getBlog();
        userDetailBean.company = bean.getCompany();
        userDetailBean.email = (bean.getEmail() == null ? "Not set" : bean.getEmail().toString());
        userDetailBean.followersCount = bean.getFollowers() + "";
        userDetailBean.followingCount = bean.getFollowing() + "";
        userDetailBean.gistsCount = bean.getPublic_gists() + "";
        userDetailBean.jointime = bean.getCreated_at();
        userDetailBean.location = bean.getLocation();
        userDetailBean.name = bean.getLogin();
        userDetailBean.repositorysCount = bean.getPublic_repos() + "";
        return userDetailBean;
    }

}
