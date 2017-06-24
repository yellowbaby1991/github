package app.yellow.github.login;

import app.yellow.github.base.BasePresenter;
import app.yellow.github.base.BaseView;
import app.yellow.github.bean.userdetail.UserDetailBean;

public class LoginContract {

    interface View extends BaseView<Presenter> {
        void goToHome(UserDetailBean bean);
    }

    interface Presenter extends BasePresenter {
        void login(String username, String password);
    }

}
