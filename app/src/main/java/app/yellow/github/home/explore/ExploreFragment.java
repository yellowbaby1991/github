package app.yellow.github.home.explore;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import app.yellow.github.R;
import app.yellow.github.base.BaseListFragment;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.data.home.explore.ExploreDataRepository;
import app.yellow.github.data.home.explore.ExploreRemoteDataSource;
import app.yellow.github.bean.home.explore.RepositoryBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ExploreFragment extends Fragment implements ExploreContract.View, RepositoryListFragment.RepositoryListListener, UserListFragment.UserListListener {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;
    @BindView(R.id.toolbar_container)
    FrameLayout mToolbarContainer;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    Unbinder unbinder;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.empty_tv)
    TextView mEmptyTv;
    @BindView(R.id.error_tv)
    TextView mErrorTv;

    private BaseListFragment[] mFragments;

    private ExploreContract.Presenter mPresenter;
    private ExploreFragmentPagerAdapter mPageAdapter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initEvents();
        mPresenter = new ExplorePresenter(
                ExploreDataRepository.getInstance(ExploreRemoteDataSource.getInstance(), null),
                this);
    }

    private void initEvents() {
        mErrorTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mErrorTv.setVisibility(View.GONE);
                mPresenter.loadRepositoryList(1);
                mPresenter.loadUserList(1);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    private void initView() {

        mToolbar.setTitleTextColor(Color.WHITE);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();//得到Toolbar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//打开开关
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);//设置图片
            actionBar.setTitle("Explore");
        }

        mPageAdapter = new ExploreFragmentPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mPageAdapter);
        mTabs.setupWithViewPager(mViewPager);
        mTabs.setTabTextColors(getResources().getColor(R.color.colorTranslucent), Color.WHITE);
        mTabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorIndicatorColor));


        mSearchView.setHintTextColor(getResources().getColor(R.color.colorTranslucent));
        mSearchView.setTextColor(Color.WHITE);
        mSearchView.setBackIcon(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.frag_explore, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView.setMenuItem(searchItem);
        MenuItem refreshItem = menu.findItem(R.id.action_refresh);
        refreshItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mPresenter.loadRepositoryList(0);
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(ExploreContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showList(List list) {
        mViewPager.setVisibility(View.VISIBLE);
        mEmptyTv.setVisibility(View.GONE);
        mErrorTv.setVisibility(View.GONE);
        BaseListFragment fragment = getCurrentBaseListFragment(list);
        fragment.createList(list);
    }

    private BaseListFragment getCurrentBaseListFragment(List list) {
        BaseListFragment fragment = null;
        if (list.get(0) instanceof RepositoryBean) {
            fragment = mFragments[0];
        } else if (list.get(0) instanceof UserBean) {
            fragment = mFragments[1];
        }
        return fragment;
    }

    @Override
    public void showMoreAdd(List moreData) {
        BaseListFragment fragment = getCurrentBaseListFragment(moreData);
        fragment.updateList(moreData);
    }

    @Override
    public void showLoadMoreError() {
        BaseListFragment fragment = mFragments[mViewPager.getCurrentItem()];
        fragment.showLoadMoreError();
    }

    @Override
    public void showEmpty() {
        mViewPager.setVisibility(View.GONE);
        mEmptyTv.setVisibility(View.VISIBLE);
        mErrorTv.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        mViewPager.setVisibility(View.GONE);
        mEmptyTv.setVisibility(View.GONE);
        mErrorTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void loadMoreRepository(int nextPage) {
        mPresenter.loadMoreRepository(nextPage);
    }

    @Override
    public void loadMoreUser(int nextPage) {
        mPresenter.loadMoreUser(nextPage);
    }


    class ExploreFragmentPagerAdapter extends FragmentPagerAdapter {

        private String[] mTitles = new String[]{"REPOSITORY", "USER"};

        public ExploreFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new BaseListFragment[2];
            mFragments[0] = new RepositoryListFragment(ExploreFragment.this);
            mFragments[1] = new UserListFragment(ExploreFragment.this);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
