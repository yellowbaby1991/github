package app.yellow.github.home.explore;

import app.yellow.github.base.BasePresenter;
import app.yellow.github.base.BaseView;
import app.yellow.github.bean.home.explore.SearchParams;

public class ExploreContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void searchRepository(SearchParams params);

        void searchUser(SearchParams params);

        void loadMoreRepository(SearchParams params);

        void loadMoreUser(SearchParams params);




    }

}
