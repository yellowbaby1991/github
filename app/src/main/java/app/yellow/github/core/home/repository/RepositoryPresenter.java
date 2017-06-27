package app.yellow.github.core.home.repository;

import android.support.annotation.NonNull;

import app.yellow.github.base.BaseListObserver;
import app.yellow.github.base.BaseListView;
import app.yellow.github.base.BasePresenterImpl;
import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.data.GithubDataRepository;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RepositoryPresenter extends BasePresenterImpl<BaseListView> implements RepositoryContract.Presenter {

    public RepositoryPresenter(@NonNull GithubDataRepository repository, BaseListView view) {
        super(repository, view);
    }

    @Override
    public void searchUserRepository(String username, String seachType) {

        mView.showLoading();

        Subscription subscription = getSubscriptionbySeachType(username, seachType, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseListObserver<RepositoryBean>(mView));

        mSubscriptions.add(subscription);

    }

    private Observable getSubscriptionbySeachType(String username, String seachType, int page) {
        if (seachType.equals(RepositoryFragment.SEACH_FORK)) {
            return mRepository
                    .getRepostitoryByUrl(username, page);
        }
        return mRepository
                .getUsersRepositoryList(username, page, seachType);
    }

    @Override
    public void loadMoreRepository(String username, int nextPage, String seachType) {

        Subscription subscription = getSubscriptionbySeachType(username, seachType, nextPage)
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

}
