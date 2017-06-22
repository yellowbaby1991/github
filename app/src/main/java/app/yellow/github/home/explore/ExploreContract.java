package app.yellow.github.home.explore;

import app.yellow.github.base.BasePresenter;
import app.yellow.github.base.BaseView;

public class ExploreContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void loadRepositoryList(int pageIndex);

        void loadMoreRepository(int pageIndex);

        void loadUserList(int pageIndex);

        void loadMoreUser(int pageIndex);

    }

}
