package app.yellow.github.api;

import java.util.List;

import app.yellow.github.base.BaseResponse;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.bean.userdetail.UserDetailResponse;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface UserService {
    @Headers("Cache-Control: public, max-age=180")
    @GET("/search/{type}")
    Observable<BaseResponse<UserBean>> getUserListsByParams(@Path("type") String type,
                                                            @Query("q") String q,
                                                            @Query("page") int pageIndex,
                                                            @Query("per_page") int pageSize,
                                                            @Query("sort") String sort,
                                                            @Query("order") String order);

    @Headers("Cache-Control: public, max-age=180")
    @GET("/users/{name}")
    Observable<UserDetailResponse> getUserByName(@Path("name") String name);


    @Headers("Cache-Control: public, max-age=180")
    @GET("/users/{name}/following")
    Observable<List<UserBean>> getUserFollowing(@Path("name") String name, @Query("page") int pageIndex,
                                               @Query("per_page") int pageSize);


    @Headers("Cache-Control: public, max-age=180")
    @GET("/users/{name}/followers")
    Observable<List<UserBean>> getUserFollowers(@Path("name") String name, @Query("page") int pageIndex,
                                      @Query("per_page") int pageSize);

}