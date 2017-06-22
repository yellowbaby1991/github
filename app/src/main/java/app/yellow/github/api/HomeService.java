package app.yellow.github.api;

import app.yellow.github.bean.home.explore.RepositoryResponse;
import app.yellow.github.bean.home.explore.UserResponse;
import app.yellow.github.util.Constants;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface HomeService {

    @GET("/search/repositories?q=language:java&per_page=" + Constants.PER_PAGE)
    Observable<RepositoryResponse> getExploreRepositoryData(@Query("page") int pageIndex);

    @GET("/search/users?q=language:java&per_page=" + Constants.PER_PAGE)
    Observable<UserResponse> getExploreUserData(@Query("page") int pageIndex);

}