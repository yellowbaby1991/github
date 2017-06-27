package app.yellow.github.core.userlist;

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
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubLocalDataSource;
import app.yellow.github.data.GithubRemoteDataSource;

public class UserFragment extends BaseListPageFragment<UserContract.Presenter> implements UserListFragment.UserListListener {

    private String mSeachType;

    private String mUrl;

    public static final String SEACH_STARGAZERS = "stargazers";

    public static final String SEACH_CONTRIBUTORS = "contributors";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter.searchUserByUrl(mUrl);
    }

    public void setSeachType(String seachType) {
        mSeachType = seachType;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    protected void setActionBarTitle(ActionBar actionBar) {
        if (mSeachType.equals(SEACH_STARGAZERS)) {
            actionBar.setTitle("Stargazers");
        }
        if (mSeachType.equals(SEACH_CONTRIBUTORS)) {
            actionBar.setTitle("Contributors");
        }
    }

    @Override
    protected UserContract.Presenter createPresenter() {
        return new UserPresenter(
                GithubDataRepository.getInstance(GithubRemoteDataSource.getInstance(), GithubLocalDataSource.getInstance()),
                this) {
        };
    }

    @Override
    protected int getLayout() {
        return R.layout.frag_repository_list;
    }

    @Override
    protected BaseListFragment getListFragment() {
        return new UserListFragment(this);
    }

    @Override
    public void loadMoreUser(int nextPage) {
        mPresenter.loadMoreUserByUrl(mUrl, nextPage);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.frag_menu, menu);
        MenuItem refreshItem = menu.findItem(R.id.action_refresh);
        refreshItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                LitePal.deleteDatabase("github_db");
                mPresenter.searchUserByUrl(mUrl);
                return false;
            }
        });
    }
}
