package app.yellow.github.repositorylist;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;

import app.yellow.github.R;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubRemoteDataSource;
import app.yellow.github.userdetail.UserDetailActivity;
import app.yellow.github.util.ActivityUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

public class RepositoryListActivity extends AppCompatActivity implements RepositoryListContract.View, RepositoryListFragment.RepositoryListListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.appBar)
    AppBarLayout mAppBar;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.backtotp_fa)
    FloatingActionButton mBackToTopButon;
    private RepositoryListContract.Presenter mPresenter;
    private RepositoryListFragment mRepositoryListFragment;

    private SpotsDialog mLodingDialog;

    private String mUsername;

    private String mSeachType = "all_rep";

    public static final String SEACH_ALL_REP = "all_rep";

    public static final String SEACH_STARRED = "starred";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);
        ButterKnife.bind(this);
        initView();

        setPresenter(new RepositoryListPresenter(
                GithubDataRepository.getInstance(GithubRemoteDataSource.getInstance(), null),
                this));

        mPresenter.searchUserRepository(mUsername, mSeachType);

    }

    private void initView() {

        mUsername = getIntent().getStringExtra(UserDetailActivity.USER_NAME);
        mSeachType = getIntent().getStringExtra(UserDetailActivity.REP_TYPE);

        mLodingDialog = new SpotsDialog(this);

        mRepositoryListFragment = new RepositoryListFragment(this);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mRepositoryListFragment, R.id.fragment_container);

        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();//得到Toolbar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//打开开关
            actionBar.setTitle(mUsername);
        }

        mBackToTopButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepositoryListFragment.backToTop();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);//加载菜单
        return true;
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
        mLodingDialog.show();
    }

    @Override
    public void hideLoading() {
        mLodingDialog.dismiss();
    }

    @Override
    public void setPresenter(RepositoryListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadMoreRepository(int nextPage) {
        mPresenter.loadMoreRepository("JakeWharton", nextPage, mSeachType);
    }

    //Home的展开事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
