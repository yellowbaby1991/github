package app.yellow.github.data.db;

import app.yellow.github.bean.home.explore.SearchParams;
import app.yellow.github.config.GithubConfig;

public class KeyFactory {

    //  https://api.github.com/search/repositories?q=language:java&page=1&per_page=10
    public static String getKeyByParams(SearchParams params) {
        StringBuilder url = new StringBuilder("");
        url.append("search/");
        url.append(params.type + "?");
        url.append("q=" + params.key + "&");
        url.append("sort=" + params.sort + "&");
        url.append("order=" + params.order + "&");
        url.append("page=" + params.page + "&");
        url.append("par_page=" + params.pageSize);
        return url.toString();
    }

    // https://api.github.com/users/JakeWharton/repos&page=1&per_page=10
    public static String getRepositoryKey(String username, int page, String seachType) {
        StringBuilder url = new StringBuilder("");
        url.append("users/");
        url.append(username + "?");
        url.append("seachType=" + seachType + "&");
        url.append("page=" + page + "&");
        url.append("par_page=" + GithubConfig.PER_PAGE);
        return url.toString();
    }

    public static String getUserKey(String name) {
        StringBuilder url = new StringBuilder("");
        url.append("users/");
        url.append(name);
        return url.toString();
    }

    public static String getFollowingKey(String name, int page) {
        StringBuilder url = new StringBuilder("");
        url.append("users/");
        url.append(name + "/");
        url.append("following" + "&");
        url.append("page=" + page + "&");
        url.append("par_page=" + GithubConfig.PER_PAGE);
        return url.toString();
    }

    public static String getFollowerKey(String name, int page) {
        StringBuilder url = new StringBuilder("");
        url.append("users/");
        url.append(name + "/");
        url.append("followers" + "&");
        url.append("page=" + page + "&");
        url.append("par_page=" + GithubConfig.PER_PAGE);
        return url.toString();
    }

    public static String getEventKey(String username, int page, String seachType, String reposname) {
        StringBuilder url = new StringBuilder("");
        url.append("events/");
        url.append(username + "/");
        url.append(reposname + "/");
        url.append("seachType=" + seachType + "&");
        url.append("page=" + page + "&");
        url.append("par_page=" + GithubConfig.PER_PAGE);
        return url.toString();
    }

    public static String getRepositoryUrlKey(String name, int page) {
        StringBuilder url = new StringBuilder("");
        url.append(name);
        url.append("page=" + page + "&");
        url.append("par_page=" + GithubConfig.PER_PAGE);
        return url.toString();
    }
}
