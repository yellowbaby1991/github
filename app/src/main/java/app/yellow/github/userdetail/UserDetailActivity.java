package app.yellow.github.userdetail;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pnikosis.materialishprogress.ProgressWheel;

import app.yellow.github.R;
import app.yellow.github.base.BaseDetailActivity;
import app.yellow.github.bean.userdetail.UserDetailBean;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubRemoteDataSource;
import app.yellow.github.home.explore.UserListFragment;
import app.yellow.github.util.ActivityUtils;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailActivity extends BaseDetailActivity<UserDetailBean> implements UserDetailContract.View {

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

    @Override
    protected int getLayout() {
        return R.layout.activity_user_detail;
    }

    @Override
    protected void initData() {

        mPresenter = new UserDetailPresenter(
                GithubDataRepository.getInstance(GithubRemoteDataSource.getInstance(), null),
                this);

        mPresenter.loadUserByName(mDetailBean.name);

        Glide.with(this).load(mDetailBean.avatarUrl).into(mAvatarUrlImg);
        mNameTv.setText(mDetailBean.name);
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

    }

}
