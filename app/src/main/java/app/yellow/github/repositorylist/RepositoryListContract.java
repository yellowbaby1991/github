package app.yellow.github.repositorylist;

import app.yellow.github.base.BaseListView;
import app.yellow.github.base.BasePresenter;

public class RepositoryListContract {

    interface View extends BaseListView<Presenter> {


    }

    interface Presenter extends BasePresenter {

        void searchUserRepository(String username);

        void loadMoreRepository(String username, int nextPage);

    }

}
