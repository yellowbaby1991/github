package app.yellow.github.login;

import android.support.annotation.NonNull;

import app.yellow.github.base.BasePresenterImpl;
import app.yellow.github.data.GithubDataRepository;

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter(@NonNull GithubDataRepository repository, LoginContract.View view) {
        super(repository, view);
    }

    @Override
    public void login(String username, String password) {

    }
}
