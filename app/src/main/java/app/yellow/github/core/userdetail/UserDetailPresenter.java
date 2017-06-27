package app.yellow.github.core.userdetail;

import android.support.annotation.NonNull;

import app.yellow.github.base.BaseDetailObserver;
import app.yellow.github.base.BasePresenterImpl;
import app.yellow.github.bean.userdetail.UserDetailBean;
import app.yellow.github.data.GithubDataRepository;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserDetailPresenter extends BasePresenterImpl<UserDetailContract.View> implements UserDetailContract.Presenter {

    public UserDetailPresenter(@NonNull GithubDataRepository repository, UserDetailContract.View view) {
        super(repository, view);
    }

    @Override
    public void loadUserByName(String name) {
        Subscription subscription
                = mRepository.getUserByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseDetailObserver<UserDetailBean>(mView) {
                    @Override
                    protected void showDetail(UserDetailBean bean) {
                        mView.showUser(bean);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void checkUserBeingFollowed(String user) {
        Subscription subscription
                = mRepository
                .checkUserBeingFollowed(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        int code = ((HttpException) e).code();
                        if (code == 404){
                            mView.showNoFollowed();
                        }
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ResponseBody object) {
                        mView.showFollowed();
                    }
                });

        mSubscriptions.add(subscription);
    }

    @Override
    public void followUser(String user) {
        mView.loadingFollowedOrUnFollowed("following...");

        Subscription subscription
                = mRepository
                .followUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.finishFollowedOrUnFollowed("following failed");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ResponseBody object) {
                        mView.finishFollowedOrUnFollowed("following successful");
                    }
                });

        mSubscriptions.add(subscription);
    }

    @Override
    public void unFollowUser(String user) {

        mView.loadingFollowedOrUnFollowed("unfollow...");

        Subscription subscription
                = mRepository
                .unFollowUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.finishFollowedOrUnFollowed("unfollow failed");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ResponseBody object) {
                        mView.finishFollowedOrUnFollowed("unfollow successful");
                    }
                });

        mSubscriptions.add(subscription);
    }
}
