package app.yellow.github.core.repositorydetail;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;

import app.yellow.github.R;
import app.yellow.github.base.BaseDetailActivity;
import app.yellow.github.bean.repositorydetail.RepositoryDetailBean;
import app.yellow.github.core.eventlist.EventListActivity;
import app.yellow.github.core.home.event.EventFragment;
import app.yellow.github.core.home.repository.RepositoryFragment;
import app.yellow.github.core.repositorylist.RepositoryListActivity;
import app.yellow.github.core.repositorylist.RepositoryListFragment;
import app.yellow.github.core.userdetail.UserDetailActivity;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubLocalDataSource;
import app.yellow.github.data.GithubRemoteDataSource;
import app.yellow.github.util.GlideUtil;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RepositroyDetailActivity extends BaseDetailActivity<RepositoryDetailBean> implements RepositoryDetailContract.View {

    public static final String FULL_NAME = "full_name";

    @BindView(R.id.description_tv)
    TextView mDescriptionTv;
    @BindView(R.id.createtime_tv)
    TextView mCreatetimeTv;
    @BindView(R.id.avatar_url_img)
    CircleImageView mAvatarUrlImg;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.appBar)
    AppBarLayout mAppBar;
    @BindView(R.id.owner_tv)
    TextView mOwnerTv;
    @BindView(R.id.updated_tv)
    TextView mUpdatedTv;
    @BindView(R.id.language_tv)
    TextView mLanguageTv;
    @BindView(R.id.authrity_text)
    TextView mAuthrityText;
    @BindView(R.id.capacity_tv)
    TextView mCapacityTv;
    @BindView(R.id.events_tv)
    TextView mEventsTv;
    @BindView(R.id.issues_tv)
    TextView mIssuesTv;
    @BindView(R.id.stargazers_tv)
    TextView mStargazersTv;
    @BindView(R.id.contributors_tv)
    TextView mContributorsTv;
    @BindView(R.id.forks_tv)
    TextView mForksTv;
    @BindView(R.id.code_tv)
    TextView mCodeTv;
    @BindView(R.id.progress_wheel)
    ProgressWheel mProgressWheel;

    private RepositoryDetailContract.Presenter mPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_repositroy_detail;
    }

    protected void initData() {
        if (mDetailBean == null) {
            setPresenter(new RepositoryDetailPresenter(
                    GithubDataRepository.getInstance(GithubRemoteDataSource.getInstance(), GithubLocalDataSource.getInstance()),
                    this));
            String fullname = getIntent().getStringExtra(FULL_NAME);
            mPresenter.loadRepByFullName(fullname);
        } else {
            showRep(mDetailBean);
        }

    }

    @Override
    protected RepositoryDetailBean createDetailBean() {
        return (RepositoryDetailBean) getIntent().getSerializableExtra(RepositoryListFragment.REPOSITORY_DETAIL);
    }

    public void showEventList(View view) {
        if (mDetailBean != null) {
            Intent intent = new Intent(this, EventListActivity.class);
            intent.putExtra(EventListActivity.USER_NAME, mDetailBean.owener);
            intent.putExtra(EventListActivity.SEACH_TYPE, EventFragment.SEACH_BY_REPS);
            intent.putExtra(EventListActivity.REP_NAME, mDetailBean.name);
            startActivity(intent);
        }
    }

    public void showForkList(View view) {
        if (mDetailBean != null && mDetailBean.forks_url != null) {
            Intent intent = new Intent(this, RepositoryListActivity.class);
            intent.putExtra(UserDetailActivity.REP_TYPE, RepositoryFragment.SEACH_FORK);
            intent.putExtra(UserDetailActivity.USER_NAME, mDetailBean.forks_url);
            startActivity(intent);
        }
    }

    @Override
    public void showRep(RepositoryDetailBean detailBean) {
        mDetailBean = detailBean;
        mOwnerTv.setText("Owner：" + detailBean.owener);
        mUpdatedTv.setText("Last Updated：" + detailBean.lastUpdated);
        mLanguageTv.setText("Language：" + detailBean.language);
        mAuthrityText.setText("Authority：" + detailBean.authority);
        mCapacityTv.setText("Capacity：" + detailBean.capacity);
        mIssuesTv.setText("Issues（" + detailBean.issuesCount + "）");
        mStargazersTv.setText("Stargazers（" + detailBean.stargazersCount + "）");
        mForksTv.setText("Forks（" + detailBean.forksCount + "）");
        mDescriptionTv.setText(detailBean.description);
        mCreatetimeTv.setText("Create at " + detailBean.createdAt);
        GlideUtil.loadImageWithProgressWheel(detailBean.avatarUrl, mAvatarUrlImg, mProgressWheel);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(detailBean.name);
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
    public void setPresenter(RepositoryDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
