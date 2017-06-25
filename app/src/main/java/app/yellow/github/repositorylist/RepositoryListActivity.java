package app.yellow.github.repositorylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import app.yellow.github.R;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubRemoteDataSource;
import app.yellow.github.home.explore.RepositoryListFragment;
import app.yellow.github.util.ActivityUtils;

public class RepositoryListActivity extends AppCompatActivity implements RepositoryListContract.View, RepositoryListFragment.RepositoryListListener {

    private RepositoryListContract.Presenter mPresenter;
    private RepositoryListFragment mRepositoryListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);

        setPresenter(new RepositoryListPresenter(
                GithubDataRepository.getInstance(GithubRemoteDataSource.getInstance(), null),
                this));

        mPresenter.searchUserRepository("JakeWharton");

        mRepositoryListFragment = new RepositoryListFragment(this);

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mRepositoryListFragment, R.id.fragment_container);
    }

    @Override
    public void showList(List list) {
        mRepositoryListFragment.createList(list);
    }

    @Override
    public void showMoreAdd(List moreData) {
        mRepositoryListFragment.updateList(moreData);
    }

    @Override
    public void showLoadMoreError() {
        mRepositoryListFragment.showLoadMoreError();
    }

    @Override
    public void showLoadMoreEnd() {
        mRepositoryListFragment.showLoadMoreError();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setPresenter(RepositoryListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadMoreRepository(int nextPage) {
        mPresenter.loadMoreRepository("JakeWharton", nextPage);
    }
}
