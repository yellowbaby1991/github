package app.yellow.github.util;

import app.yellow.github.bean.home.explore.SearchParams;

public class Constants {

    public static final String BASE_URL = "https://api.github.com";

    public static final int PER_PAGE = 30;

    public static final String SP_FILE_NAME = "github_sp";

    public static final String SP_TOKEN = "token";

    public static final String SP_BASEAUTH = "base_auth";

    public static final String LOGIN_AVATAR_URL = "login_avatar_url";

    //  https://api.github.com/search/repositories?q=language:java&page=1&per_page=10
    public static String getKeyByParams(SearchParams params) {
        StringBuilder url = new StringBuilder("");
        url.append("search/");
        url.append(params.type + "?");
        url.append("q=" + params.key + "?");
        url.append("page=" + params.page + "&");
        url.append("par_page=" + params.pageSize);
        return url.toString();
    }
}
