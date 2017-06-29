package app.yellow.github.data.api;

import java.util.List;

import app.yellow.github.base.BaseResponse;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.bean.userdetail.UserDetailResponse;
import app.yellow.github.config.GithubConfig;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface UserService {
    @Headers(GithubConfig.CACHE_HEADER)
    @GET("/search/{type}")
    Observable<BaseResponse<UserBean>> getUserListsByParams(@Path("type") String type,
                                                            @Query("q") String q,
                                                            @Query("page") int pageIndex,
                                                            @Query("per_page") int pageSize,
                                                            @Query("sort") String sort,
                                                            @Query("order") String order);

    @Headers(GithubConfig.CACHE_HEADER)
    @GET("/users/{name}")
    Observable<UserDetailResponse> getUserByName(@Path("name") String name);


    @Headers(GithubConfig.CACHE_HEADER)
    @GET("/users/{name}/following")
    Observable<List<UserBean>> getUserFollowing(@Path("name") String name, @Query("page") int pageIndex,
                                                @Query("per_page") int pageSize);


    @Headers(GithubConfig.CACHE_HEADER)
    @GET("/users/{name}/followers")
    Observable<List<UserBean>> getUserFollowers(@Path("name") String name, @Query("page") int pageIndex,
                                                @Query("per_page") int pageSize);


    @Headers(GithubConfig.CACHE_HEADER)
    @GET("")
    Observable<List<UserBean>> getUsersByUrl(@Url String url, @Query("page") int pageIndex,
                                             @Query("per_page") int pageSize);


    @Headers(GithubConfig.CACHE_HEADER)
    @GET("/user/following/{username}")
    Observable<ResponseBody> checkUserBeingFollowed(@Path("username") String username);

    @Headers(GithubConfig.CACHE_HEADER)
    @PUT("/user/following/{username}")
    Observable<ResponseBody> followUser(@Path("username") String username);

    @Headers(GithubConfig.CACHE_HEADER)
    @DELETE("/user/following/{username}")
    Observable<ResponseBody> unFollowUser(@Path("username") String username);

}