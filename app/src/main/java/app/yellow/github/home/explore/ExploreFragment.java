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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import app.yellow.github.R;
import app.yellow.github.base.BaseFragment;
import app.yellow.github.base.BaseListFragment;
import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.home.explore.SearchParams;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubRemoteDataSource;
import app.yellow.github.repositorylist.RepositoryListFragment;
import app.yellow.github.userlist.UserListFragment;
import butterknife.BindView;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;

public class ExploreFragment extends BaseFragment<ExploreContract.Presenter> implements ExploreContract.View, RepositoryListFragment.RepositoryListListener, UserListFragment.UserListListener {

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
    @BindView(R.id.empty_tv)
    TextView mEmptyTv;
    @BindView(R.id.error_tv)
    TextView mErrorTv;
    @BindView(R.id.multiple_actions)
    FloatingActionsMenu mMultipleActions;

    private BaseListFragment[] mFragments;

    private ExploreContract.Presenter mPresenter;
    private ExploreFragmentPagerAdapter mPageAdapter;

    private SearchParams mRepositoryParams;
    private SearchParams mUserParams;

    private String mLanguageTitle[] = new String[]{"Java", "JavaScript", "C++", "C"};
    private String mSortTypeTitle[] = new String[]{"Most stars", "Best match", "Most forks", "Recently updated"};
    private int mSortType = 0;

    private SpotsDialog mLodingDialog;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvents();
        initView();
        mPresenter = new ExplorePresenter(
                GithubDataRepository.getInstance(GithubRemoteDataSource.getInstance(), null),
                this);

        mRepositoryParams = new SearchParams();
        mRepositoryParams.page = 1;
        mRepositoryParams.language = "language:java";
        mRepositoryParams.key = mRepositoryParams.language;
        mRepositoryParams.type = "repositories";
        mPresenter.searchRepository(mRepositoryParams);


    }


    @Override
    protected int getLayout() {
        return R.layout.frag_explore;
    }

    private void initEvents() {
        mErrorTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mErrorTv.setVisibility(View.GONE);
                mPresenter.searchRepository(mRepositoryParams);
                mPresenter.searchUser(mUserParams);
            }
        });

    }

    protected void initView() {

        mLodingDialog = new SpotsDialog(getContext());

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
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mUserParams == null) {
                    mUserParams = new SearchParams();
                    mUserParams.page = 1;
                    mUserParams.language = "language:java";
                    mUserParams.key = mUserParams.language;
                    mUserParams.type = "users";
                    mPresenter.searchUser(mUserParams);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabs.setupWithViewPager(mViewPager);
        mTabs.setTabTextColors(getResources().getColor(R.color.colorTranslucent), Color.WHITE);
        mTabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorIndicatorColor));


        mSearchView.setHintTextColor(getResources().getColor(R.color.colorTranslucent));
        mSearchView.setTextColor(Color.WHITE);
        mSearchView.setEllipsize(true);
        mSearchView.setBackIcon(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchByInput(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        final FloatingActionButton sortTypeAction = new FloatingActionButton(getContext());
        sortTypeAction.setTitle(mSortTypeTitle[mSortType]);
        sortTypeAction.setColorNormalResId(R.color.pink);
        sortTypeAction.setColorPressedResId(R.color.white_pressed);
        sortTypeAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSortType++;
                if (mSortType == mSortTypeTitle.length) {
                    mSortType = 0;
                }
                sortTypeAction.setTitle(mSortTypeTitle[mSortType]);
                switch (mSortTypeTitle[mSortType]) {
                    case "Most stars":
                        setSortType(mRepositoryParams,"stars");
                        setSortType(mUserParams,"stars");
                        break;
                    case "Best match":
                        setSortType(mRepositoryParams,null);
                        setSortType(mUserParams,null);
                        break;
                    case "Most forks":
                        setSortType(mRepositoryParams,"forks");
                        setSortType(mUserParams,"forks");
                        break;
                    case "Recently updated":
                        setSortType(mRepositoryParams,"updated");
                        setSortType(mUserParams,"updated");
                        break;
                }
                mPresenter.searchRepository(mRepositoryParams);
                mPresenter.searchUser(mUserParams);
            }
        });

        mMultipleActions.addButton(sortTypeAction);

        for (String title : mLanguageTitle) {
            final FloatingActionButton action = new FloatingActionButton(getContext());
            action.setTitle(title);
            action.setColorNormalResId(R.color.yellow);
            action.setColorPressedResId(R.color.white_pressed);
            action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchByLanguage(action.getTitle());
                    mMultipleActions.collapse();
                }
            });
            mMultipleActions.addButton(action);
        }

    }

    private void setSortType(SearchParams params, String type) {
        if (params != null) {
            params.sort = type;
        }
    }

    private void searchByLanguage(String title) {
        if (mViewPager.getCurrentItem() == 0) {
            mRepositoryParams.language = "language:" + title;
            mRepositoryParams.key = mRepositoryParams.language;
            mRepositoryParams.page = 1;
            mPresenter.searchRepository(mRepositoryParams);
        }

        if (mViewPager.getCurrentItem() == 1) {
            mUserParams.language = "language:" + title;
            mUserParams.key = mUserParams.language;
            mUserParams.page = 1;
            mPresenter.searchUser(mUserParams);
        }
    }

    private void searchByInput(String query) {
        if (mViewPager.getCurrentItem() == 0) {
            mRepositoryParams.key = query;
            mRepositoryParams.page = 1;
            mPresenter.searchRepository(mRepositoryParams);
        }
        if (mViewPager.getCurrentItem() == 1) {
            mUserParams.key = query;
            mUserParams.page = 1;
            mPresenter.searchUser(mUserParams);
        }
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
                mPresenter.searchRepository(mRepositoryParams);
                mPresenter.searchUser(mUserParams);
                return false;
            }
        });
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
    public void showLoadMoreEnd() {
        BaseListFragment fragment = mFragments[mViewPager.getCurrentItem()];
        fragment.showLoadMoreEnd();
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
        mLodingDialog.show();
    }

    @Override
    public void hideLoading() {
        mLodingDialog.dismiss();
        BaseListFragment fragment = mFragments[mViewPager.getCurrentItem()];
        fragment.hideLoadMore();
    }

    @Override
    public void loadMoreRepository(int nextPage) {
        mRepositoryParams.page = nextPage;
        mPresenter.loadMoreRepository(mRepositoryParams);
    }

    @Override
    public void loadMoreUser(int nextPage) {
        mUserParams.page = nextPage;
        mPresenter.loadMoreUser(mUserParams);
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
