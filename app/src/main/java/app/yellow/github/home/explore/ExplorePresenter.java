package app.yellow.github.home.explore;

import android.support.annotation.NonNull;

import app.yellow.github.base.BaseListObserver;
import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.data.home.explore.ExploreDataRepository;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ExplorePresenter implements ExploreContract.Presenter {

    @NonNull
    private final ExploreDataRepository mRepository;

    @NonNull
    private final ExploreContract.View mView;

    @NonNull
    private CompositeSubscription mSubscriptions;

    public ExplorePresenter(@NonNull ExploreDataRepository repository, @NonNull ExploreContract.View view) {
        mRepository = repository;
        mView = view;
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }

    @Override
    public void loadRepositoryList(int pageIndex) {

        mView.showLoading();

        Subscription subscription =
                getRepositoryList(pageIndex)
                        .subscribe(new BaseListObserver<RepositoryBean>(mView));

        mSubscriptions.add(subscription);
    }

    @Override
    public void loadMoreRepository(int pageIndex) {
        Subscription subscription =
                getRepositoryList(pageIndex)
                        .subscribe(new BaseListObserver<RepositoryBean>(mView) {
                            @Override
                            protected boolean isLoadMore() {
                                return true;
                            }
                        });

        mSubscriptions.add(subscription);
    }

    @Override
    public void loadUserList(int pageIndex) {
        mView.showLoading();

        Subscription subscription =
                getUserList(pageIndex)
                        .subscribe(new BaseListObserver<UserBean>(mView));

        mSubscriptions.add(subscription);
    }

    @Override
    public void loadMoreUser(int pageIndex) {
        Subscription subscription =
                getUserList(pageIndex)
                        .subscribe(new BaseListObserver<UserBean>(mView) {
                            @Override
                            protected boolean isLoadMore() {
                                return true;
                            }
                        });

        mSubscriptions.add(subscription);
    }

    private Observable getRepositoryList(int pageIndex) {
        return mRepository
                .getRepositorys(pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable getUserList(int pageIndex) {
        return mRepository
                .getUsers(pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void subscribe() {
        loadRepositoryList(1);
        loadUserList(1);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
