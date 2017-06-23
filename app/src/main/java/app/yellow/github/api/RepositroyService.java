package app.yellow.github.api;

import app.yellow.github.bean.home.explore.BaseResponse;
import app.yellow.github.bean.home.explore.RepositoryBean;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RepositroyService {
    @Headers("Cache-Control: public, max-age=180")
    @GET("/search/{type}?access_token=af83a0399a3d5a4af5499abd142acda66178d90b")
    Observable<BaseResponse<RepositoryBean>> loadData(@Path("type") String type,
                                                      @Query("q") String q,
                                                      @Query("page") int pageIndex,
                                                      @Query("per_page") int pageSize,
                                                      @Query("sort") String sort,
                                                      @Query("order") String order);
}