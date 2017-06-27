package app.yellow.github.core.userdetail;

import app.yellow.github.base.BasePresenter;
import app.yellow.github.base.BaseView;
import app.yellow.github.bean.userdetail.UserDetailBean;

public class UserDetailContract {

    interface View extends BaseView<Presenter> {

        void showUser(UserDetailBean detailBean);

        void showFollowed();

        void showNoFollowed();

        void loadingFollowedOrUnFollowed(String text);

        void finishFollowedOrUnFollowed(String text);

    }

    interface Presenter extends BasePresenter {

        void loadUserByName(String name);

        void checkUserBeingFollowed(String user);

        void followUser(String user);

        void unFollowUser(String user);

    }

}
