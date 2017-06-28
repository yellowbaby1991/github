package app.yellow.github.core.repositorydetail;

import app.yellow.github.base.BasePresenter;
import app.yellow.github.base.BaseView;
import app.yellow.github.bean.repositorydetail.ContentBean;
import app.yellow.github.bean.repositorydetail.RepositoryDetailBean;

public class RepositoryDetailContract {

    interface View extends BaseView<Presenter> {

        void showRep(RepositoryDetailBean detailBean);

        void showStar();

        void showNoStar();

        void lodingStarOrUnStar(String text);

        void finishStarOrUnStar(String text);

        void lodingFork(String text);

        void finishFork(String text);

        void showReadMe(ContentBean contentBean);

        void loadingReadMe();

        void finishLoadingReadMe();

    }

    interface Presenter extends BasePresenter {

        void loadRepByFullName(String name);

        void checkRepBeingStarred(String owner, String repo);

        void starRep(String owner, String repo);

        void unStarRep(String owner, String repo);

        void forkRep(String owner, String repo);

        void loadReadMe(String url);
    }

}
