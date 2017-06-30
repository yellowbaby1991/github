package app.yellow.rx_mvp_sample.userlist;

import app.yellow.rx_mvp_sample.base.mvp.BaseListView;
import app.yellow.rx_mvp_sample.base.mvp.BasePresenter;

public class UserListContract {

    interface View extends BaseListView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void searchUsersByUrl(String url);

        void loadMoreUsersByUrl(String url, int page);

    }

}
