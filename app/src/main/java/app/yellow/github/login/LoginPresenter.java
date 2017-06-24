package app.yellow.github.login;

import android.support.annotation.NonNull;

import app.yellow.github.base.BaseDetailObserver;
import app.yellow.github.base.BasePresenterImpl;
import app.yellow.github.bean.userdetail.UserDetailBean;
import app.yellow.github.data.GithubDataRepository;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {


    public LoginPresenter(@NonNull GithubDataRepository repository, LoginContract.View view) {
        super(repository, view);
    }

    @Override
    public void login(String username, String password) {
        Subscription subscription = mRepository
                .login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseDetailObserver<UserDetailBean>(mView) {
                    @Override
                    protected void showDetail(UserDetailBean userDetailBean) {
                        mView.goToHome(userDetailBean);
                    }
                });
        mSubscriptions.add(subscription);

    }
}
