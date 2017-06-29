package app.yellow.github.core.userdetail;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.pnikosis.materialishprogress.ProgressWheel;

import app.yellow.github.R;
import app.yellow.github.base.BaseDetailActivity;
import app.yellow.github.bean.userdetail.UserDetailBean;
import app.yellow.github.core.eventlist.EventListActivity;
import app.yellow.github.core.followlist.FollowListActivity;
import app.yellow.github.core.home.event.EventFragment;
import app.yellow.github.core.home.follow.FollowFragment;
import app.yellow.github.core.home.repository.RepositoryFragment;
import app.yellow.github.core.repositorylist.RepositoryListActivity;
import app.yellow.github.core.userlist.UserListFragment;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubLocalDataSource;
import app.yellow.github.data.GithubRemoteDataSource;
import app.yellow.github.util.ActivityUtils;
import app.yellow.github.util.GlideUtil;
import app.yellow.github.util.NetworkUtil;
import app.yellow.github.util.UIUtils;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailActivity extends BaseDetailActivity<UserDetailBean> implements UserDetailContract.View {

    public static final String USER_NAME = "user_name";

    public static final String SEACH_TYPE = "seach_type";

    public static final String REP_TYPE = "rep_type";

    public static final String FOLLOW_TYPE = "follow_type";

    @BindView(R.id.avatar_url_img)
    CircleImageView mAvatarUrlImg;
    @BindView(R.id.jointime_tv)
    TextView mJointimeTv;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.appBar)
    AppBarLayout mAppBar;
    @BindView(R.id.email_tv)
    TextView mEmailTv;
    @BindView(R.id.blog_tv)
    TextView mBlogTv;
    @BindView(R.id.location_tv)
    TextView mLocationTv;
    @BindView(R.id.company_text)
    TextView mCompanyText;
    @BindView(R.id.events_tv)
    TextView mEventsTv;
    @BindView(R.id.stargazers_tv)
    TextView mStargazersTv;
    @BindView(R.id.followers_tv)
    TextView mFollowersTv;
    @BindView(R.id.following_tv)
    TextView mFollowingTv;
    @BindView(R.id.repositorys_tv)
    TextView mRepositorysTv;
    @BindView(R.id.gists_tv)
    TextView mGistsTv;
    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindView(R.id.progress_wheel)
    ProgressWheel mProgressWheel;

    private UserDetailContract.Presenter mPresenter;

    private FloatingActionButton mFollowedAction;

    @Override
    protected int getLayout() {
        return R.layout.activity_user_detail;
    }


    @Override
    protected void initView() {
        super.initView();

        mFollowedAction = new FloatingActionButton(this);
        mFollowedAction.setTitle("");
        mFollowedAction.setIconDrawable(getResources().getDrawable(R.drawable.ic_fork));
        mFollowedAction.setColorNormalResId(R.color.yellow);
        mFollowedAction.setColorPressedResId(R.color.white_pressed);
        mFollowedAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFollowedAction.getTitle().equals("Followed")) {
                    mPresenter.followUser( mDetailBean.name);
                }
                if (mFollowedAction.getTitle().equals("UnFollowed")) {
                    mPresenter.unFollowUser(mDetailBean.name);
                }
                mFollowedAction.setClickable(false);
                mFollowedAction.setColorNormalResId(R.color.half_black);
            }
        });

        mMultipleActions.addButton(mFollowedAction);

        mMultipleActions.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        setPresenter(new UserDetailPresenter(
                GithubDataRepository.getInstance(GithubRemoteDataSource.getInstance(), GithubLocalDataSource.getInstance()),
                this));

        mPresenter.loadUserByName(mDetailBean.name);

        mNameTv.setText(mDetailBean.name);

        if (NetworkUtil.isConnected(UIUtils.getContext())){
            mPresenter.checkUserBeingFollowed(mDetailBean.name);
        }

    }

    public void showRepositoryList(View view) {
        Intent intent = new Intent(this, RepositoryListActivity.class);
        intent.putExtra(USER_NAME, mDetailBean.name);
        intent.putExtra(REP_TYPE, RepositoryFragment.SEACH_PUBLIC_REP);
        startActivity(intent);
    }

    public void showStarredRepositoryList(View view) {
        Intent intent = new Intent(this, RepositoryListActivity.class);
        intent.putExtra(USER_NAME, mDetailBean.name);
        intent.putExtra(REP_TYPE, RepositoryFragment.SEACH_STARRED);
        startActivity(intent);
    }

    public void showEventList(View view) {
        Intent intent = new Intent(this, EventListActivity.class);
        intent.putExtra(USER_NAME, mDetailBean.name);
        intent.putExtra(SEACH_TYPE, EventFragment.SEACH_BY_USER);
        startActivity(intent);
    }

    public void showFollowingList(View view){
        Intent intent = new Intent(this, FollowListActivity.class);
        intent.putExtra(USER_NAME, mDetailBean.name);
        intent.putExtra(FOLLOW_TYPE, FollowFragment.FOLLOWING);
        startActivity(intent);
    }

    public void showFollowerList(View view){
        Intent intent = new Intent(this, FollowListActivity.class);
        intent.putExtra(USER_NAME, mDetailBean.name);
        intent.putExtra(FOLLOW_TYPE, FollowFragment.FOLLOWER);
        startActivity(intent);
    }


    @Override
    protected UserDetailBean createDetailBean() {
        return (UserDetailBean) getIntent().getSerializableExtra(UserListFragment.USER_DETAIL);
    }

    @Override
    public void showUser(UserDetailBean bean) {
        mJointimeTv.setText("Join：" + ActivityUtils.dealDataString(bean.jointime));
        mEmailTv.setText("Email：" + bean.email);
        mBlogTv.setText("Blog：" + bean.blog);
        mLocationTv.setText("Location：" + bean.location);
        mCompanyText.setText("Company：" + bean.company);
        mFollowersTv.setText("Followers（" + bean.followersCount + "）");
        mFollowingTv.setText("Following（" + bean.followingCount + "）");
        mRepositorysTv.setText("Repositorys（" + bean.repositorysCount + "）");
        mGistsTv.setText("Gists（" + bean.gistsCount + "）");
        GlideUtil.loadImageWithProgressWheel(bean.avatarUrl, mAvatarUrlImg, mProgressWheel);

        ActionBar actionBar = getSupportActionBar();//得到Toolbar
        actionBar.setTitle(bean.name);
    }

    @Override
    public void showFollowed() {
        mMultipleActions.setVisibility(View.VISIBLE);
        mFollowedAction.setTitle("UnFollowed");
        mFollowedAction.setIconDrawable(getResources().getDrawable(R.drawable.ic_unfollow_white_48dp));
    }

    @Override
    public void showNoFollowed() {
        mMultipleActions.setVisibility(View.VISIBLE);
        mFollowedAction.setTitle("Followed");
        mFollowedAction.setIconDrawable(getResources().getDrawable(R.drawable.ic_follow_white_48dp));
    }

    @Override
    public void loadingFollowedOrUnFollowed(String text) {
        mFollowedAction.setClickable(false);
        mFollowedAction.setColorNormalResId(R.color.half_black);
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishFollowedOrUnFollowed(String text) {
        mFollowedAction.setClickable(true);
        mFollowedAction.setColorNormalResId(R.color.yellow);
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
        if (text.equals("following successful")){
            showFollowed();
        }
        if (text.equals("unfollow successful")){
            showNoFollowed();
        }
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {
        mProgressWheel.setProgress(1);
    }

    @Override
    public void hideLoading() {
        mProgressWheel.setProgress(0);
    }

    @Override
    public void setPresenter(UserDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
