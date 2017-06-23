package app.yellow.github.userdetail;

import app.yellow.github.base.BasePresenter;
import app.yellow.github.base.BaseView;
import app.yellow.github.bean.userdetail.UserDetailBean;

public class UserDetailContract {

    interface View extends BaseView<Presenter> {

        void showUser(UserDetailBean detailBean);
    }

    interface Presenter extends BasePresenter {

        void loadUserByName(String name);

    }

}
