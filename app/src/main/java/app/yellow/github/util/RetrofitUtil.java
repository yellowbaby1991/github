package app.yellow.github.util;

import app.yellow.github.api.AuthService;
import app.yellow.github.api.RepositoryService;
import app.yellow.github.api.UserService;
import app.yellow.github.http.GithubAuthRetrofit;
import app.yellow.github.http.GithubRetrofit;

public class RetrofitUtil {


    public static RepositoryService getRepositroyService() {
        return GithubRetrofit.getInstance().get().create(RepositoryService.class);
    }

    public static UserService getUserService() {
        return GithubRetrofit.getInstance().get().create(UserService.class);
    }

    public static AuthService getAutoService(String username, String password) {
        return GithubAuthRetrofit.getInstance(username, password).get().create(AuthService.class);
    }


}