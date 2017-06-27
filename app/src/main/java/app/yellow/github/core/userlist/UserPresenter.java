package app.yellow.github.core.userlist;

import android.support.annotation.NonNull;

import app.yellow.github.base.BaseListObserver;
import app.yellow.github.base.BaseListView;
import app.yellow.github.base.BasePresenterImpl;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.data.GithubDataRepository;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserPresenter extends BasePresenterImpl<BaseListView> implements UserContract.Presenter {

    public UserPresenter(@NonNull GithubDataRepository repository, BaseListView view) {
        super(repository, view);
    }

    @Override
    public void searchUserByUrl(String url) {
        mView.showLoading();

        Subscription subscription = mRepository.getUsersByUrl(url, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseListObserver<UserBean>(mView));

        mSubscriptions.add(subscription);
    }

    @Override
    public void loadMoreUserByUrl(String url, int page) {

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
