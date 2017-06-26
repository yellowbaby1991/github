package app.yellow.github.data.db;

import app.yellow.github.bean.home.explore.SearchParams;

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




}
