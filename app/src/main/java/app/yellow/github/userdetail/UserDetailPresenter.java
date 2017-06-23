package app.yellow.github.userdetail;

import android.support.annotation.NonNull;

import app.yellow.github.base.BaseDetailObserver;
import app.yellow.github.base.BasePresenterImpl;
import app.yellow.github.bean.userdetail.UserDetailBean;
import app.yellow.github.data.GithubDataRepository;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserDetailPresenter extends BasePresenterImpl<UserDetailContract.View> implements UserDetailContract.Presenter {

    public UserDetailPresenter(@NonNull GithubDataRepository repository, UserDetailContract.View view) {
        super(repository, view);
    }

    @Override
    public void loadUserByName(String name) {
        Subscription subscription
                = mRepository.getUserByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseDetailObserver<UserDetailBean>(mView) {
                    @Override
                    protected void showDetail(UserDetailBean bean) {
                        mView.showUser(bean);
                    }
                });
        mSubscriptions.add(subscription);
    }
}
