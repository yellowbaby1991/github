package app.yellow.github.home.explore;

import android.support.annotation.NonNull;

import app.yellow.github.base.BaseListObserver;
import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.SearchParams;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.data.home.explore.ExploreDataRepository;
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
    public void searchRepository(SearchParams params) {

        mView.showLoading();

        Subscription subscription =
                mRepository.getRepositoryListByParams(params)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseListObserver<RepositoryBean>(mView));

        mSubscriptions.add(subscription);
    }

    @Override
    public void searchUser(SearchParams params) {

        mView.showLoading();

        Subscription subscription =
                mRepository.getUserListByParams(params)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseListObserver<UserBean>(mView));

        mSubscriptions.add(subscription);
    }

    @Override
    public void loadMoreRepository(SearchParams params) {
        Subscription subscription =
                mRepository.getRepositoryListByParams(params)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseListObserver<RepositoryBean>(mView) {
                            @Override
                            protected boolean isLoadMore() {
                                return true;
                            }
                        });

        mSubscriptions.add(subscription);
    }

    @Override
    public void loadMoreUser(SearchParams params) {
        Subscription subscription =
                mRepository.getUserListByParams(params)
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

/*    private Observable getRepositoryListByParams(SearchParams params) {
        return mRepository
                .getRepositoryListByParams(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }*/


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
