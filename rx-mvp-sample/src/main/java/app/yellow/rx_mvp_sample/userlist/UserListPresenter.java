package app.yellow.rx_mvp_sample.userlist;

import android.support.annotation.NonNull;

import app.yellow.rx_mvp_sample.base.mvp.BasePresenterImpl;
import app.yellow.rx_mvp_sample.base.rx.BaseListObserver;
import app.yellow.rx_mvp_sample.bean.UserBean;
import app.yellow.rx_mvp_sample.data.Repository;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserListPresenter extends BasePresenterImpl<UserListContract.View> implements UserListContract.Presenter {

    public UserListPresenter(@NonNull Repository repository, UserListContract.View view) {
        super(repository, view);
    }

    @Override
    public void searchUsersByUrl(String url) {

        mView.showLoading();

        Subscription subscription = mRepository.getUsersByUrl(url, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseListObserver<UserBean>(mView));

        mSubscriptions.add(subscription);
    }

    @Override
    public void loadMoreUsersByUrl(String url, int page) {
        
        Subscription subscription = mRepository.getUsersByUrl(url, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseListObserver<UserBean>(mView) {
                    @Override
                    protected boolean isLoadMore() {
                        return true;
                    }
                });

        mSubscriptions.add(subscription);
    }
}
