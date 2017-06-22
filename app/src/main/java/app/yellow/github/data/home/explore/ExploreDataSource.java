package app.yellow.github.data.home.explore;


import app.yellow.github.bean.home.explore.SearchParams;
import app.yellow.github.data.DataSource;
import rx.Observable;

public interface ExploreDataSource extends DataSource {

    Observable getRepositoryListByParams(SearchParams params);

    Observable getUserListByParams(SearchParams params);
}
