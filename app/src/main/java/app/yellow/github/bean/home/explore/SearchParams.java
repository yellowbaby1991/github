package app.yellow.github.bean.home.explore;

import app.yellow.github.util.Constants;

public class SearchParams {

    public SearchParams() {
        pageSize = Constants.PER_PAGE;
        sort = "stars";
        order = "desc";
    }

    public String type;

    public int page;

    public int pageSize;

    public String key;

    public String language;

    public String sort;

    public String order;

}
