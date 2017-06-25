package app.yellow.github.home.repository;

import app.yellow.github.base.BaseListView;
import app.yellow.github.base.BasePresenter;

public class RepositorContract {

    interface View extends BaseListView<Presenter> {


    }

    interface Presenter extends BasePresenter {

        void searchUserRepository(String username, String seachType);

        void loadMoreRepository(String username, int nextPage, String seachType);

    }

}
