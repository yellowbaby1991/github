package app.yellow.github.core.userlist;

import app.yellow.github.base.BaseListView;
import app.yellow.github.base.BasePresenter;

public class UserContract {

    interface View extends BaseListView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void searchUserByUrl(String url);

        void loadMoreUserByUrl(String url, int page);

    }

}
