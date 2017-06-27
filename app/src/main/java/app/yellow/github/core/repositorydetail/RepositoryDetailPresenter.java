package app.yellow.github.core.repositorydetail;

import android.support.annotation.NonNull;

import app.yellow.github.base.BaseDetailObserver;
import app.yellow.github.base.BasePresenterImpl;
import app.yellow.github.bean.repositorydetail.RepositoryDetailBean;
import app.yellow.github.data.GithubDataRepository;
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
}
