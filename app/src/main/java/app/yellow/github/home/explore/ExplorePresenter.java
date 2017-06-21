package app.yellow.github.home.explore;

import android.support.annotation.NonNull;

import java.util.List;

import app.yellow.github.data.home.explore.ExploreDataRepository;
import app.yellow.github.data.home.explore.RepositoryBean;
import rx.Observer;
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
    public void loadRepositoryList() {
        mSubscriptions.clear();
        Subscription subscription = mRepository
                .getRepositorys()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<RepositoryBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<RepositoryBean> repositoryList) {
                        mView.showRepositoryList(repositoryList);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void subscribe() {
        loadRepositoryList();
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
