package app.yellow.github.core.home.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.litepal.LitePal;

import app.yellow.github.R;
import app.yellow.github.base.BaseListFragment;
import app.yellow.github.base.BaseListPageFragment;
import app.yellow.github.core.eventlist.EventListFragment;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubLocalDataSource;
import app.yellow.github.data.GithubRemoteDataSource;
import app.yellow.github.util.ActivityUtils;

public class EventFragment extends BaseListPageFragment<EventContract.Presenter> implements EventListFragment.EventListListener {

    public static final String SEACH_BY_USER = "users";

    public static final String SEACH_BY_REPS= "reps";

    private String mUsername;

    private String mSeachType;

    private String mReponame;

    @Override
    protected void setActionBarTitle(ActionBar actionBar) {
        actionBar.setTitle("Events");
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public void setSeachType(String seachType) {
        mSeachType = seachType;
    }

    public void setReponame(String reponame) {
        mReponame = reponame;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.searchUserEvent(mUsername, mSeachType, mReponame);
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
        mPresenter.loadMoreEvent(mUsername, nextPage, mSeachType, mReponame);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem refreshItem = menu.findItem(R.id.action_refresh);
        refreshItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                LitePal.deleteDatabase("github_db");
                ActivityUtils.clearCache();
                mPresenter.searchUserEvent(mUsername, mSeachType, mReponame);
                return false;
            }
        });
    }
}
