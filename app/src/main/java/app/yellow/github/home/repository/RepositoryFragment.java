package app.yellow.github.home.repository;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;

import app.yellow.github.R;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubRemoteDataSource;
import app.yellow.github.repositorylist.RepositoryListFragment;
import app.yellow.github.util.ActivityUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;

public class RepositoryFragment extends Fragment implements RepositorContract.View, RepositoryListFragment.RepositoryListListener {

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
    private RepositorContract.Presenter mPresenter;
    private RepositoryListFragment mRepositoryListFragment;

    private SpotsDialog mLodingDialog;

    private String mUsername;

    private String mSeachType = "all_rep";

    public static final String SEACH_PUBLIC_REP = "all_public_rep";

    public static final String SEACH_STARRED = "starred";

    public static final String SEACH_ALL_REP = "all_rep";

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.frag_repository_list, container, false);
        unbinder = ButterKnife.bind(this, root);
        initView();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setPresenter(new RepositoryPresenter(
                GithubDataRepository.getInstance(GithubRemoteDataSource.getInstance(), null),
                this));

        mPresenter.searchUserRepository(mUsername, mSeachType);
    }

    public void setUsername(String username){
        mUsername = username;
    }

    public void setSerchType(String seachType){
        mSeachType = seachType;
    }

    private void initView() {

        mLodingDialog = new SpotsDialog(getContext());

        mRepositoryListFragment = new RepositoryListFragment(this);
        ActivityUtils.addFragmentToActivity(getChildFragmentManager(), mRepositoryListFragment, R.id.fragment_container);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();//得到Toolbar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//打开开关
            if (mSeachType.equals(SEACH_PUBLIC_REP)){
                actionBar.setHomeAsUpIndicator(R.drawable.ic_action_navigation_arrow_back);
                actionBar.setTitle(mUsername);
            }
            if (mSeachType.equals(SEACH_STARRED)){
                actionBar.setHomeAsUpIndicator(R.drawable.ic_action_navigation_arrow_back);
                actionBar.setTitle("STARRED");
            }
            if (mSeachType.equals(SEACH_ALL_REP)){
                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
                actionBar.setTitle("Repository");
            }
        }

        mBackToTopButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepositoryListFragment.backToTop();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
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
    public void setPresenter(RepositorContract.Presenter presenter) {
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
                getActivity().finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
