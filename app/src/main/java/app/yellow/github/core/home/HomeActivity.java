package app.yellow.github.core.home;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pnikosis.materialishprogress.ProgressWheel;

import app.yellow.github.R;
import app.yellow.github.bean.userdetail.UserDetailBean;
import app.yellow.github.core.home.event.EventFragment;
import app.yellow.github.core.home.explore.ExploreFragment;
import app.yellow.github.core.home.follow.FollowFragment;
import app.yellow.github.core.home.repository.RepositoryFragment;
import app.yellow.github.core.home.setting.SettingActivity;
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


    public static HomeActivity INSTANCE = null;

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

        INSTANCE = this;

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
                        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), repositoryFragment, R.id.fragment_container);
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
                        followFragment.setIsShowHome(true);
                        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), followFragment, R.id.fragment_container);
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
                    case R.id.action_events:
                        EventFragment eventFragment = new EventFragment();
                        eventFragment.setUsername(mBean.name);
                        eventFragment.setSeachType(EventFragment.SEACH_BY_USER);
                        eventFragment.setReponame(null);
                        eventFragment.setIsShowHome(true);
                        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), eventFragment, R.id.fragment_container);
                        mDrawerLayout.closeDrawer(mNavView);
                        break;
                    case R.id.action_setting:
                        Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                        startActivity(intent);
                        mDrawerLayout.closeDrawer(mNavView);
                        break;
                }
                return true;
            }
        });
    }

    public static void finishHome(){
        if (INSTANCE != null){
            INSTANCE.finish();
        }
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {                                         //如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;//更新firstTime
                    return true;
                } else {                                                    //两次按键小于2秒时，退出应用
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

}
