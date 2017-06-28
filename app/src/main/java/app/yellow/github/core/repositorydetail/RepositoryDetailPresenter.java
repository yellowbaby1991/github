package app.yellow.github.core.repositorydetail;

import android.support.annotation.NonNull;

import app.yellow.github.base.BaseDetailObserver;
import app.yellow.github.base.BasePresenterImpl;
import app.yellow.github.bean.repositorydetail.ContentBean;
import app.yellow.github.bean.repositorydetail.RepositoryDetailBean;
import app.yellow.github.data.GithubDataRepository;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RepositoryDetailPresenter extends BasePresenterImpl<RepositoryDetailContract.View> implements RepositoryDetailContract.Presenter {

    public RepositoryDetailPresenter(@NonNull GithubDataRepository repository, RepositoryDetailContract.View view) {
        super(repository, view);
    }

    @Override
    public void loadRepByFullName(String name) {
        Subscription subscription
                = mRepository.getRepositoryByFullName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseDetailObserver<RepositoryDetailBean>(mView) {
                    @Override
                    protected void showDetail(RepositoryDetailBean bean) {
                        mView.showRep(bean);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void checkRepBeingStarred(String owner, String repo) {

        Subscription subscription
                = mRepository
                .checkRepBeingStarred(owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        int code = ((HttpException) e).code();
                        if (code == 404) {
                            mView.showNoStar();
                        }
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ResponseBody object) {
                        mView.showStar();
                    }
                });

        mSubscriptions.add(subscription);
    }

    @Override
    public void starRep(String owner, String repo) {

        mView.lodingStarOrUnStar("staring...");

        Subscription subscription
                = mRepository
                .starRepo(owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.finishStarOrUnStar("staring failed");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ResponseBody object) {
                        mView.finishStarOrUnStar("staring finish");
                    }
                });

        mSubscriptions.add(subscription);
    }

    @Override
    public void unStarRep(String owner, String repo) {

        mView.lodingStarOrUnStar("unstaring...");

        Subscription subscription
                = mRepository
                .unStarRepo(owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.finishStarOrUnStar("unstar failed");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ResponseBody object) {
                        mView.finishStarOrUnStar("unstar finish");
                    }
                });

        mSubscriptions.add(subscription);
    }

    @Override
    public void forkRep(String owner, String repo) {

        mView.lodingFork("forking...");

        Subscription subscription
                = mRepository
                .forkRepo(owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.finishFork("fork failed");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ResponseBody object) {
                        mView.finishFork("fork finish");
                    }
                });

        mSubscriptions.add(subscription);

    }

    @Override
    public void loadReadMe(String url) {

        mView.loadingReadMe();

        Subscription subscription
                = mRepository
                .loadContentByUrl(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContentBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.finishLoadingReadMe();
                    }

                    @Override
                    public void onNext(ContentBean contentBean) {
                        mView.showReadMe(contentBean);
                        mView.finishLoadingReadMe();
                    }
                });

        mSubscriptions.add(subscription);

    }


}
