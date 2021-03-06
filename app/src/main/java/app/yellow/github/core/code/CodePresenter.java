package app.yellow.github.core.code;

import android.support.annotation.NonNull;

import java.util.List;

import app.yellow.github.base.BaseDetailObserver;
import app.yellow.github.base.BasePresenterImpl;
import app.yellow.github.bean.repositorydetail.ContentBean;
import app.yellow.github.data.GithubDataRepository;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CodePresenter extends BasePresenterImpl<CodeContract.View> implements CodeContract.Presenter {

    public CodePresenter(@NonNull GithubDataRepository repository, CodeContract.View view) {
        super(repository, view);
    }

    @Override
    public void loadContentListByUrl(String url) {

        mView.showLoading();

        Subscription subscription = mRepository.loadContentListByUrl(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ContentBean>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<ContentBean> list) {
                        mView.showContentList(list);
                        mView.hideLoading();
                    }
                });

        mSubscriptions.add(subscription);
    }

    @Override
    public void loadContentByUrl(String url) {

        mView.showLoading();

        Subscription subscription = mRepository.loadContentByUrl(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseDetailObserver<ContentBean>(mView) {
                    @Override
                    protected void showDetail(ContentBean bean) {
                        mView.showContent(bean);
                    }
                });

        mSubscriptions.add(subscription);

    }
}
