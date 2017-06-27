package app.yellow.github.core.repositorydetail;

import app.yellow.github.base.BasePresenter;
import app.yellow.github.base.BaseView;
import app.yellow.github.bean.repositorydetail.RepositoryDetailBean;

public class RepositoryDetailContract {

    interface View extends BaseView<Presenter> {

        void showRep(RepositoryDetailBean detailBean);
    }

    interface Presenter extends BasePresenter {

        void loadRepByFullName(String name);

    }

}
