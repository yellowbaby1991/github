package app.yellow.rx_mvp_sample.userlist;

import android.support.annotation.NonNull;

import app.yellow.rx_mvp_sample.base.mvp.BasePresenterImpl;
import app.yellow.rx_mvp_sample.base.rx.SubscriptionCreator;
import app.yellow.rx_mvp_sample.data.Repository;

public class UserListPresenter extends BasePresenterImpl<UserListContract.View> implements UserListContract.Presenter {

    public UserListPresenter(@NonNull Repository repository, UserListContract.View view) {
        super(repository, view);
    }

    @Override
    public void searchUsersByUrl(String url) {
        mView.showLoading();
        SubscriptionCreator.searchListSubscription(mSubscriptions, mRepository.getUsersByUrl(url, 1), mView);
    }

    @Override
    public void loadMoreUsersByUrl(String url, int page) {
        SubscriptionCreator.loadMoreSubscription(mSubscriptions, mRepository.getUsersByUrl(url, page), mView);
    }
}
