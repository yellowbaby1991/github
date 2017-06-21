package app.yellow.github.api.home;

import app.yellow.github.data.home.explore.RepositoryResponse;
import retrofit2.http.GET;
import rx.Observable;

public interface HomeService {

    @GET("/search/repositories?q=language:java")
    Observable<RepositoryResponse> getExploreRepositoryData();

}