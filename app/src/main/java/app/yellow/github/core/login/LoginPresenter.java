package app.yellow.github.core.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;

import app.yellow.github.base.BaseDetailObserver;
import app.yellow.github.base.BasePresenterImpl;
import app.yellow.github.bean.userdetail.UserDetailBean;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.util.Constants;
import app.yellow.github.util.NetworkUtil;
import app.yellow.github.util.SPUtils;
import app.yellow.github.util.UIUtils;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {


    public LoginPresenter(@NonNull GithubDataRepository repository, LoginContract.View view) {
        super(repository, view);
    }

    @Override
    public void login(String username, String password) {

        mView.showLoading();

        String userCredentials = username + ":" + password;
        String basicAuth =
                "Basic " + new String(Base64.encode(userCredentials.getBytes(), Base64.DEFAULT));

        SPUtils.putString(UIUtils.getContext(), Constants.SP_BASEAUTH, basicAuth);

        loginWithAuth(basicAuth);

    }

    @Override
    public void loginWithAuth(final String basicAuth) {

        Subscription subscription = mRepository
                .loginWithAuth(basicAuth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseDetailObserver<UserDetailBean>(mView) {
                    @Override
                    protected void showDetail(UserDetailBean userDetailBean) {

                        SPUtils.putString(UIUtils.getContext(), Constants.LOGIN_AVATAR_URL, userDetailBean.avatarUrl);
                        mView.goToHome(userDetailBean);
                        mView.hideLoading();
                    }
                });
        mSubscriptions.add(subscription);
    }


    @Override
    public void checkToken() {
        if (!NetworkUtil.isConnected(UIUtils.getContext())){
            mView.showNoNet();
            mView.showLodingUi();
            return;
        }
        String baseAuth = SPUtils.getString(UIUtils.getContext(), Constants.SP_BASEAUTH, "");
        if (TextUtils.isEmpty(baseAuth)) {
            mView.showLoginUi();
        } else {
            mView.showLodingUi();
            loginWithAuth(baseAuth);
        }
    }
}
