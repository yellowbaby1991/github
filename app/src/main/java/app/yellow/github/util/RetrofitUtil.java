package app.yellow.github.util;

import app.yellow.github.data.api.AuthService;
import app.yellow.github.data.api.ContentService;
import app.yellow.github.data.api.EventService;
import app.yellow.github.data.api.RepositoryService;
import app.yellow.github.data.api.UserService;
import app.yellow.github.http.GithubAuthRetrofit;
import app.yellow.github.http.GithubRetrofit;

public class RetrofitUtil {

    public static RepositoryService getRepositroyService() {
        return GithubRetrofit.getInstance().get().create(RepositoryService.class);
    }

    public static EventService getEventService() {
        return GithubRetrofit.getInstance().get().create(EventService.class);
    }

    public static UserService getUserService() {
        return GithubRetrofit.getInstance().get().create(UserService.class);
    }

    public static ContentService getContentService() {
        return GithubRetrofit.getInstance().get().create(ContentService.class);
    }

    public static AuthService getAutoService(String basicAuth) {
        return GithubAuthRetrofit.getInstance(basicAuth).get().create(AuthService.class);
    }


}