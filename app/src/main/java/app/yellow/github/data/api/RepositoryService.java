package app.yellow.github.data.api;

import java.util.List;

import app.yellow.github.base.BaseResponse;
import app.yellow.github.bean.home.explore.RepositoryBean;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface RepositoryService {
    @Headers("Cache-Control: public, max-age=180")
    @GET("/search/{type}")
    Observable<BaseResponse<RepositoryBean>> getRepositoryListsByParams(@Path("type") String type,
                                                                        @Query("q") String q,
                                                                        @Query("page") int pageIndex,
                                                                        @Query("per_page") int pageSize,
                                                                        @Query("sort") String sort,
                                                                        @Query("order") String order);

    @Headers("Cache-Control: public, max-age=180")
    @GET("/users/{username}/repos")
    Observable<List<RepositoryBean>> getPublicRepositoryList(@Path("username") String username, @Query("page") int pageIndex,
                                                             @Query("per_page") int pageSize);

    @Headers("Cache-Control: public, max-age=180")
    @GET("/users/{username}/starred")
    Observable<List<RepositoryBean>> getStaredRepositoryList(@Path("username") String username, @Query("page") int pageIndex,
                                                             @Query("per_page") int pageSize);

    @Headers("Cache-Control: public, max-age=180")
    @GET("")
    Observable<List<RepositoryBean>> getRepositoryByUrl(@Url String url, @Query("page") int pageIndex,
                                                       @Query("per_page") int pageSize);

    @Headers("Cache-Control: public, max-age=180")
    @GET("")
    Observable<RepositoryBean> getRepositoryByFullName(@Url String url);

    @Headers("Cache-Control: public, max-age=180")
    @GET("/user/repos")
    Observable<List<RepositoryBean>> getUserAllRepositoryList(@Query("page") int pageIndex,
                                                              @Query("per_page") int pageSize);

    @Headers("Cache-Control: public, max-age=180")
    @GET("/user/starred/{owner}/{repo}")
    Observable<ResponseBody> checkRepBeingStarred(@Path("owner") String owner, @Path("repo") String repo);

    @Headers("Cache-Control: public, max-age=180")
    @PUT("/user/starred/{owner}/{repo}")
    Observable<ResponseBody> starRepo(@Path("owner") String owner, @Path("repo") String repo);


    @Headers("Cache-Control: public, max-age=180")
    @DELETE("/user/starred/{owner}/{repo}")
    Observable<ResponseBody> unStarRepo(@Path("owner") String owner, @Path("repo") String repo);

    @Headers("Cache-Control: public, max-age=180")
    @POST("/repos/{owner}/{repo}/forks")
    Observable<ResponseBody> forkRepo(@Path("owner") String owner, @Path("repo") String repo);
}