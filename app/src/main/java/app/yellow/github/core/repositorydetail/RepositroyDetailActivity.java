package app.yellow.github.core.repositorydetail;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;

import app.yellow.github.R;
import app.yellow.github.base.BaseDetailActivity;
import app.yellow.github.bean.repositorydetail.RepositiryDetailBean;
import app.yellow.github.core.repositorylist.RepositoryListFragment;
import app.yellow.github.util.GlideUtil;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RepositroyDetailActivity extends BaseDetailActivity<RepositiryDetailBean> {

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

    @Override
    protected int getLayout() {
        return R.layout.activity_repositroy_detail;
    }

    protected void initData() {
        mOwnerTv.setText("Owner：" + mDetailBean.owener);
        mUpdatedTv.setText("Last Updated：" + mDetailBean.lastUpdated);
        mLanguageTv.setText("Language：" + mDetailBean.language);
        mAuthrityText.setText("Authority：" + mDetailBean.authority);
        mCapacityTv.setText("Capacity：" + mDetailBean.capacity);
        mIssuesTv.setText("Issues（" + mDetailBean.issuesCount + "）");
        mStargazersTv.setText("Stargazers（" + mDetailBean.stargazersCount + "）");
        mForksTv.setText("Forks（" + mDetailBean.forksCount + "）");
        mDescriptionTv.setText(mDetailBean.description);
        mCreatetimeTv.setText("Create at " + mDetailBean.createdAt);
        GlideUtil.loadImageWithProgressWheel(mDetailBean.avatarUrl,mAvatarUrlImg,mProgressWheel);
    }

    @Override
    protected RepositiryDetailBean createDetailBean() {
        return (RepositiryDetailBean) getIntent().getSerializableExtra(RepositoryListFragment.REPOSITORY_DETAIL);
    }


}
