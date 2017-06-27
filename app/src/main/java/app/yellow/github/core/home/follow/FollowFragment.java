package app.yellow.github.core.home.follow;

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
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import app.yellow.github.R;
import app.yellow.github.base.BaseFragment;
import app.yellow.github.base.BaseListFragment;
import app.yellow.github.core.userlist.UserListFragment;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubLocalDataSource;
import app.yellow.github.data.GithubRemoteDataSource;
import butterknife.BindView;
import dmax.dialog.SpotsDialog;

public class FollowFragment extends BaseFragment<FollowContract.Presenter> implements FollowContract.View, UserListFragment.UserListListener {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_container)
    FrameLayout mToolbarContainer;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.empty_tv)
    TextView mEmptyTv;
    @BindView(R.id.error_tv)
    TextView mErrorTv;

    public static final String FOLLOWER = "follower";
    public static final String FOLLOWING = "following";

    private FollowerFragmentPagerAdapter mPageAdapter;

    private BaseListFragment[] mFragments;

    private SpotsDialog mLodingDialog;

    private String mSearchType;

    private boolean isOnce = false;

    private String mUsername;

    private boolean mShowHome;

    @Override
    protected int getLayout() {
        return R.layout.frag_follow;
    }

    public void setIsShowHome(boolean showHome){
        mShowHome = showHome;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        mPresenter = new FollowPresenter(
                GithubDataRepository.getInstance(GithubRemoteDataSource.getInstance(), GithubLocalDataSource.getInstance()),
                this);

        if (mSearchType.equals(FOLLOWER)) {
            mViewPager.setCurrentItem(0);
            mPresenter.searchFollowers(mUsername);
        }
        if (mSearchType.equals(FOLLOWING)) {
            mViewPager.setCurrentItem(1);
            mPresenter.searchFollowing(mUsername);
        }


    }

    private void initView() {

        mLodingDialog = new SpotsDialog(getContext());

        mToolbar.setTitleTextColor(Color.WHITE);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();//得到Toolbar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//打开开关
            actionBar.setTitle("Follow");
            if (!mShowHome){
                actionBar.setHomeAsUpIndicator(R.drawable.ic_action_navigation_arrow_back_inverted);
            }else {
                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            }
        }

        mPageAdapter = new FollowerFragmentPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mPageAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (!isOnce && mSearchType.equals(FOLLOWER) && position == 1) {
                    mPresenter.searchFollowing(mUsername);
                    isOnce = true;
                }
                if (!isOnce && mSearchType.equals(FOLLOWING) && position == 0) {
                    mPresenter.searchFollowers(mUsername);
                    isOnce = true;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabs.setupWithViewPager(mViewPager);
        mTabs.setTabTextColors(getResources().getColor(R.color.colorTranslucent), Color.WHITE);
        mTabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorIndicatorColor));
    }

    @Override
    public void showList(List list) {
        mViewPager.setVisibility(View.VISIBLE);
        mEmptyTv.setVisibility(View.GONE);
        mErrorTv.setVisibility(View.GONE);
        BaseListFragment fragment = mFragments[mViewPager.getCurrentItem()];
        fragment.createList(list);
    }


    @Override
    public void showMoreAdd(List moreData) {
        BaseListFragment fragment = mFragments[mViewPager.getCurrentItem()];
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
        mLodingDialog.dismiss();
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
    public void loadMoreUser(int nextPage) {
        if (mViewPager.getCurrentItem() == 0) {
            mPresenter.loadMoreFollowers(mUsername, nextPage);
        }
        if (mViewPager.getCurrentItem() == 1) {
            mPresenter.loadMoreFollowing(mUsername, nextPage);
        }
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public void setSerchType(String searchType) {
        mSearchType = searchType;
    }

    class FollowerFragmentPagerAdapter extends FragmentPagerAdapter {

        private String[] mTitles = new String[]{"FOLLOWERS", "FOLLOWINGS"};

        public FollowerFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new BaseListFragment[2];
            mFragments[0] = new UserListFragment(FollowFragment.this);
            mFragments[1] = new UserListFragment(FollowFragment.this);
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
