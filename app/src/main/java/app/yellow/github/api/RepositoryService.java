package app.yellow.github.api;

import app.yellow.github.base.BaseResponse;
import app.yellow.github.bean.home.explore.RepositoryBean;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
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
}