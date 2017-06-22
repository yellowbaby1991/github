package app.yellow.github.data.home.explore;


import java.util.List;

import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.UserBean;
import rx.Observable;

public interface ExploreDataSource {

    Observable<List<RepositoryBean>> getRepositorys(int pageIndex);

    Observable<List<UserBean>> getUsers(int pageIndex);
}
