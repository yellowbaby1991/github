package app.yellow.github.core.home.repository;

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
import app.yellow.github.core.repositorylist.RepositoryListFragment;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubLocalDataSource;
import app.yellow.github.data.GithubRemoteDataSource;
import app.yellow.github.util.ActivityUtils;
import butterknife.Unbinder;

public class RepositoryFragment extends BaseListPageFragment<RepositoryContract.Presenter> implements RepositoryListFragment.RepositoryListListener {

    Unbinder unbinder;
    private String mUsername;

    private String mSeachType = "all_rep";

    public static final String SEACH_PUBLIC_REP = "all_public_rep";

    public static final String SEACH_STARRED = "starred";

    public static final String SEACH_ALL_REP = "all_rep";

    public static final String SEACH_FORK = "fork";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter.searchUserRepository(mUsername, mSeachType);
    }

    @Override
    protected void setActionBarTitle(ActionBar actionBar) {
        if (mSeachType.equals(SEACH_PUBLIC_REP)) {
            actionBar.setTitle(mUsername);
        }
        if (mSeachType.equals(SEACH_STARRED)) {
            actionBar.setTitle("STARRED");
        }
        if (mSeachType.equals(SEACH_ALL_REP)) {
            actionBar.setTitle("Repository");
        }
        if (mSeachType.equals(SEACH_FORK)) {
            actionBar.setTitle("Forks");
        }
    }

    @Override
    protected RepositoryContract.Presenter createPresenter() {
        return new RepositoryPresenter(
                GithubDataRepository.getInstance(GithubRemoteDataSource.getInstance(), GithubLocalDataSource.getInstance()),
                this);
    }

    @Override
    protected BaseListFragment getListFragment() {
        return new RepositoryListFragment(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.frag_repository_list;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public void setSerchType(String seachType) {
        mSeachType = seachType;
    }

    @Override
    public void loadMoreRepository(int nextPage) {
        mPresenter.loadMoreRepository(mUsername, nextPage, mSeachType);
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
                mPresenter.searchUserRepository(mUsername, mSeachType);
                return false;
            }
        });
    }


}
