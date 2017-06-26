package app.yellow.github.core.home.event;

import app.yellow.github.base.BaseListView;
import app.yellow.github.base.BasePresenter;

public class EventContract {

    interface View extends BaseListView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void searchUserEvent(String username, String seachType, String reposname);

        void loadMoreEvent(String username, int nextPage, String seachType, String reposname);

    }

}
