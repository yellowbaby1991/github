package app.yellow.github.data.home.explore;


import java.util.List;

import rx.Observable;

public interface ExploreDataSource {

    Observable<List<RepositoryBean>> getRepositorys();
}
