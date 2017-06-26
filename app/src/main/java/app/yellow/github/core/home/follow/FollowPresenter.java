package app.yellow.github.core.home.follow;

import android.support.annotation.NonNull;

import app.yellow.github.base.BaseListObserver;
import app.yellow.github.base.BasePresenterImpl;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.data.GithubDataRepository;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FollowPresenter extends BasePresenterImpl<FollowContract.View> implements FollowContract.Presenter {

    public FollowPresenter(@NonNull GithubDataRepository repository, FollowContract.View view) {
        super(repository, view);
    }

    @Override
    public void searchFollowing(String username) {
        mView.showLoading();

        Subscription subscription =
                mRepository.getFollowing(username, 0)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseListObserver<UserBean>(mView));

        mSubscriptions.add(subscription);
    }

    @Override
    public void searchFollowers(String username) {
        mView.showLoading();
        Subscription subscription =
                mRepository.getFollowers(username, 0)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseListObserver<UserBean>(mView));

        mSubscriptions.add(subscription);
    }

    @Override
    public void loadMoreFollowing(String username, int page) {
        Subscription subscription =
                mRepository.getFollowing(username,page)
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

    @Override
    public void loadMoreFollowers(String username, int page) {
        Subscription subscription =
                mRepository.getFollowers(username,page)
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
