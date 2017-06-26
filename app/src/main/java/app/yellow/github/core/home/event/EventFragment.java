package app.yellow.github.core.home.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import app.yellow.github.R;
import app.yellow.github.base.BaseListFragment;
import app.yellow.github.base.BaseListPageFragment;
import app.yellow.github.core.eventlist.EventListFragment;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubLocalDataSource;
import app.yellow.github.data.GithubRemoteDataSource;

public class EventFragment extends BaseListPageFragment<EventContract.Presenter> implements EventListFragment.EventListListener {

    @Override
    protected void setActionBarTitle(ActionBar actionBar) {
        actionBar.setTitle("Events");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.searchUserEvent("JakeWharton", "repos", "butterknife");
    }

    @Override
    protected EventContract.Presenter createPresenter() {
        return new EventPresenter(
                GithubDataRepository.getInstance(GithubRemoteDataSource.getInstance(), GithubLocalDataSource.getInstance()),
                this);
    }

    @Override
    protected BaseListFragment getListFragment() {
        return new EventListFragment(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.frag_repository_list;
    }

    @Override
    public void loadMoreEvent(int nextPage) {

    }
}
