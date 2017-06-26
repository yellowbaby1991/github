package app.yellow.github.core.home.event;

import android.support.annotation.NonNull;

import app.yellow.github.base.BaseListObserver;
import app.yellow.github.base.BaseListView;
import app.yellow.github.base.BasePresenterImpl;
import app.yellow.github.bean.home.event.EventBean;
import app.yellow.github.data.GithubDataRepository;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EventPresenter extends BasePresenterImpl<BaseListView> implements EventContract.Presenter {

    public EventPresenter(@NonNull GithubDataRepository repository, BaseListView view) {
        super(repository, view);
    }

    @Override
    public void searchUserEvent(String username, String seachType, String reposname) {
        mView.showLoading();

        Subscription subscription = mRepository
                .getEvents(username, 1, seachType, reposname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseListObserver<EventBean>(mView));

        mSubscriptions.add(subscription);
    }

    @Override
    public void loadMoreEvent(String username, int nextPage, String seachType, String reposname) {

    }
}
