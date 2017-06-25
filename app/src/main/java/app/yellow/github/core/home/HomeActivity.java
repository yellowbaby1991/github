package app.yellow.github.core.home;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;

import app.yellow.github.R;
import app.yellow.github.bean.userdetail.UserDetailBean;
import app.yellow.github.core.home.explore.ExploreFragment;
import app.yellow.github.core.home.follow.FollowFragment;
import app.yellow.github.core.home.repository.RepositoryFragment;
import app.yellow.github.util.ActivityUtils;
import app.yellow.github.util.GlideUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    public final static String USER_DETAIL = "user_detail";

    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    CircleImageView mAvatarUrlImg;
    ProgressWheel mProgressWheel;
    TextView mNameTv;
    TextView mCompanyTv;
    TextView mLocationTv;
    TextView mBlogTv;
    TextView mEmailTv;

    private UserDetailBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        mBean = (UserDetailBean) getIntent().getSerializableExtra(USER_DETAIL);
    }

    private void initView() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            mDrawerLayout.setFitsSystemWindows(true);
            mDrawerLayout.setClipToPadding(false);
        }

        View view = LayoutInflater.from(this).inflate(R.layout.drawer_header, null);
        mNavView.addHeaderView(view);

        mProgressWheel = (ProgressWheel) view.findViewById(R.id.progress_wheel);
        mAvatarUrlImg = (CircleImageView) view.findViewById(R.id.avatar_url_img);
        mNameTv = (TextView) view.findViewById(R.id.name_tv);
        mCompanyTv = (TextView) view.findViewById(R.id.company_tv);
        mLocationTv = (TextView) view.findViewById(R.id.location_tv);
        mBlogTv = (TextView) view.findViewById(R.id.blog_tv);
        mEmailTv = (TextView) view.findViewById(R.id.email_tv);


        if (mNavView != null) {
            setupDrawerContent(mNavView);
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), new ExploreFragment(), R.id.fragment_container);

        GlideUtil.loadImageWithProgressWheel(mBean.avatarUrl, mAvatarUrlImg, mProgressWheel);
        mNameTv.setText(mBean.name);
        setText(mLocationTv, mBean.location);
        setText(mBlogTv, mBean.blog);
        setText(mCompanyTv, mBean.company);
        setText(mEmailTv, mBean.email);
    }

    private void setText(TextView textView, String value) {
        if (TextUtils.isEmpty(value) || value.equals("null") || value.equals("Not set")) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(value);
        }
    }


    //Home的展开事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return true;
    }


    //菜单的点击事件
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.action_repository:
                        RepositoryFragment repositoryFragment = new RepositoryFragment();
                        repositoryFragment.setUsername(mBean.name);
                        repositoryFragment.setSerchType(RepositoryFragment.SEACH_ALL_REP);
                        repositoryFragment.setIsShowHome(true);
                        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), new RepositoryFragment(), R.id.fragment_container);
                        mDrawerLayout.closeDrawer(mNavView);
                        break;
                    case R.id.action_explore:
                        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), new ExploreFragment(), R.id.fragment_container);
                        mDrawerLayout.closeDrawer(mNavView);
                        break;
                    case R.id.action_follow:
                        FollowFragment followFragment = new FollowFragment();
                        followFragment.setUsername(mBean.name);
                        followFragment.setSerchType(FollowFragment.FOLLOWER);
                        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(),followFragment , R.id.fragment_container);
                        mDrawerLayout.closeDrawer(mNavView);
                        break;
                    case R.id.action_star:
                        RepositoryFragment starRepositoryFragment = new RepositoryFragment();
                        starRepositoryFragment.setUsername(mBean.name);
                        starRepositoryFragment.setSerchType(RepositoryFragment.SEACH_STARRED);
                        starRepositoryFragment.setIsShowHome(true);
                        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), starRepositoryFragment, R.id.fragment_container);
                        mDrawerLayout.closeDrawer(mNavView);
                        break;
                }
                return true;
            }
        });
    }


}
