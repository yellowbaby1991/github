package app.yellow.github.login;

import app.yellow.github.base.BasePresenter;
import app.yellow.github.base.BaseView;

public class LoginContract {

    interface View extends BaseView<Presenter> {
        void goToHome();
    }

    interface Presenter extends BasePresenter {
        void login(String username, String password);
    }

}
