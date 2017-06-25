package app.yellow.github.core.home.follow;

import app.yellow.github.base.BaseListView;
import app.yellow.github.base.BasePresenter;

public class FollowContract {

    interface View extends BaseListView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void searchFollowing(String username);

        void searchFollowers(String username);

        void loadMoreFollowing(String username,int page);

        void loadMoreFollowers(String username,int page);

    }

}