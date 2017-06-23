package app.yellow.github.data;


import app.yellow.github.bean.home.explore.SearchParams;
import rx.Observable;

public interface GithubDataSource {

    Observable getRepositoryListByParams(SearchParams params);

    Observable getUserListByParams(SearchParams params);

    Observable getUserByName(String name);
}
