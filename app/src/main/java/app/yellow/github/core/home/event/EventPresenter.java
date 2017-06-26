package app.yellow.github.core.home.event;

import android.support.annotation.NonNull;

import app.yellow.github.base.BaseListView;
import app.yellow.github.base.BasePresenterImpl;
import app.yellow.github.data.GithubDataRepository;

public class EventPresenter extends BasePresenterImpl<BaseListView> implements EventContract.Presenter {

    public EventPresenter(@NonNull GithubDataRepository repository, BaseListView view) {
        super(repository, view);
    }

    @Override
    public void searchUserEvent(String username, String seachType,String reposname) {

    }

    @Override
    public void loadMoreEvent(String username, int nextPage, String seachType,String reposname) {

    }
}
