package app.yellow.github.data.api;

import app.yellow.github.bean.login.AuthorizationResponse;
import app.yellow.github.bean.login.CreateAuthorization;
import app.yellow.github.bean.userdetail.UserDetailResponse;
import app.yellow.github.config.GithubConfig;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface AuthService {

    @POST("/authorizations")
    Observable<AuthorizationResponse> createAuthorization(
            @Body CreateAuthorization createAuthorization);

    @Headers(GithubConfig.CACHE_HEADER)
    @GET("/user")
    Observable<UserDetailResponse> getUserInfo(@Query("access_token") String accessToken);
}
