package app.yellow.rx_mvp_sample.base.rx;

import app.yellow.rx_mvp_sample.base.mvp.BaseListView;
import app.yellow.rx_mvp_sample.bean.UserBean;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SubscriptionCreator {

    public static void searchListSubscription(CompositeSubscription subscriptions, Observable observable, BaseListView view) {
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseListObserver<UserBean>(view));
        subscriptions.add(subscription);
    }

    public static void loadMoreSubscription(CompositeSubscription subscriptions, Observable observable, BaseListView view) {
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseListObserver<UserBean>(view) {
                    @Override
                    protected boolean isLoadMore() {
                        return true;
                    }
                });
        subscriptions.add(subscription);
    }
}
