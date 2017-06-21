package app.yellow.github.home.explore;

import java.util.List;

import app.yellow.github.base.BasePresenter;
import app.yellow.github.base.BaseView;
import app.yellow.github.data.home.explore.RepositoryBean;

public class ExploreContract {

    interface View extends BaseView<Presenter> {

        void showRepositoryList(List<RepositoryBean> repositoryList);

    }

    interface Presenter extends BasePresenter {

        void loadRepositoryList();
    }

}
