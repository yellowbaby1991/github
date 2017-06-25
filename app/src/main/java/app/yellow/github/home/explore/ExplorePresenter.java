package app.yellow.github.home.explore;

import android.support.annotation.NonNull;

import app.yellow.github.base.BaseListObserver;
import app.yellow.github.base.BasePresenterImpl;
import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.SearchParams;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.data.GithubDataRepository;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ExplorePresenter extends BasePresenterImpl<ExploreContract.View> implements ExploreContract.Presenter {

    public ExplorePresenter(@NonNull GithubDataRepository repository, @NonNull ExploreContract.View view) {
        super(repository,view);
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



}
