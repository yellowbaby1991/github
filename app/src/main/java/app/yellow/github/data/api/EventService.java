package app.yellow.github.data.api;

import java.util.List;

import app.yellow.github.bean.home.event.EventBean;
import app.yellow.github.config.GithubConfig;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface EventService {
    
    @Headers(GithubConfig.CACHE_HEADER)
    @GET("/users/{username}/events")
    Observable<List<EventBean>> getUserEvents(@Path("username") String username, @Query("page") int pageIndex,
                                             @Query("per_page") int pageSize);

    @Headers(GithubConfig.CACHE_HEADER)
    @GET("/repos/{username}/{repos}/events")
    Observable<List<EventBean>> getRepEvents(@Path("username") String username, @Path("repos") String repos, @Query("page") int pageIndex,
                                             @Query("per_page") int pageSize);
}