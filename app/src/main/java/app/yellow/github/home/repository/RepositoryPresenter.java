package app.yellow.github.home.repository;

import android.support.annotation.NonNull;

import app.yellow.github.base.BaseListObserver;
import app.yellow.github.base.BasePresenterImpl;
import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.data.GithubDataRepository;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RepositoryPresenter extends BasePresenterImpl<RepositorContract.View> implements RepositorContract.Presenter {

    public RepositoryPresenter(@NonNull GithubDataRepository repository, RepositorContract.View view) {
        super(repository, view);
    }

    @Override
    public void searchUserRepository(String username, String seachType) {

        mView.showLoading();

        Subscription subscription = mRepository
                .getUsersRepositoryList(username, 1, seachType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseListObserver<RepositoryBean>(mView));

        mSubscriptions.add(subscription);

    }

    @Override
    public void loadMoreRepository(String username, int nextPage, String seachType) {

        Subscription subscription = mRepository
                .getUsersRepositoryList(username, 1, seachType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseListObserver<RepositoryBean>(mView) {
                               @Override
                               protected boolean isLoadMore() {
                                   return true;
                               }
                           }
                );

        mSubscriptions.add(subscription);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
