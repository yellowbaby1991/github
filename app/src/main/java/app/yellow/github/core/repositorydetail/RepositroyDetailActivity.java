package app.yellow.github.core.repositorydetail;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.mukesh.MarkdownView;
import com.pnikosis.materialishprogress.ProgressWheel;

import app.yellow.github.R;
import app.yellow.github.base.BaseDetailActivity;
import app.yellow.github.bean.repositorydetail.ContentBean;
import app.yellow.github.bean.repositorydetail.RepositoryDetailBean;
import app.yellow.github.core.code.CodeListActivity;
import app.yellow.github.core.eventlist.EventListActivity;
import app.yellow.github.core.home.event.EventFragment;
import app.yellow.github.core.home.repository.RepositoryFragment;
import app.yellow.github.core.repositorylist.RepositoryListActivity;
import app.yellow.github.core.repositorylist.RepositoryListFragment;
import app.yellow.github.core.userdetail.UserDetailActivity;
import app.yellow.github.core.userlist.UserFragment;
import app.yellow.github.core.userlist.UserListActivity;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubLocalDataSource;
import app.yellow.github.data.GithubRemoteDataSource;
import app.yellow.github.util.Constants;
import app.yellow.github.util.GlideUtil;
import app.yellow.github.util.NetworkUtil;
import app.yellow.github.util.UIUtils;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import pl.tajchert.waitingdots.DotsTextView;

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
    @BindView(R.id.markdown_view)
    MarkdownView mMarkdownView;
    @BindView(R.id.dots)
    DotsTextView mDots;
    @BindView(R.id.dots_tv)
    TextView mDotsTv;

    private RepositoryDetailContract.Presenter mPresenter;

    private FloatingActionButton mForkAction;
    private FloatingActionButton mStarAction;

    @Override
    protected int getLayout() {
        return R.layout.activity_repositroy_detail;
    }

    @Override
    protected void initView() {
        super.initView();

        mForkAction = new FloatingActionButton(this);
        mForkAction.setTitle("Fork");
        mForkAction.setIconDrawable(getResources().getDrawable(R.drawable.ic_fork));
        mForkAction.setColorNormalResId(R.color.yellow);
        mForkAction.setColorPressedResId(R.color.white_pressed);
        mForkAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtil.isConnected(UIUtils.getContext())) {
                    mPresenter.forkRep(mDetailBean.owener, mDetailBean.name);
                    mForkAction.setClickable(false);
                    mForkAction.setColorNormalResId(R.color.half_black);
                }
            }
        });

        mStarAction = new FloatingActionButton(this);
        mStarAction.setTitle("");
        mStarAction.setColorNormalResId(R.color.yellow);
        mStarAction.setColorPressedResId(R.color.white_pressed);
        mStarAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMultipleActions.collapse();
            }
        });

        mMultipleActions.addButton(mForkAction);
        mMultipleActions.addButton(mStarAction);

        mMultipleActions.setVisibility(View.GONE);

        mStarAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDetailBean != null) {
                    if (mStarAction.getTitle().equals("Star")) {
                        mPresenter.starRep(mDetailBean.owener, mDetailBean.name);
                    }
                    if (mStarAction.getTitle().equals("UnStar")) {
                        mPresenter.unStarRep(mDetailBean.owener, mDetailBean.name);
                    }
                }
            }
        });
    }

    protected void initData() {
        setPresenter(new RepositoryDetailPresenter(
                GithubDataRepository.getInstance(GithubRemoteDataSource.getInstance(), GithubLocalDataSource.getInstance()),
                this));
        if (mDetailBean == null) {
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

    @Override
    protected String getDetailUrl() {
        return "https://github.com/" + mDetailBean.owener + "/" + mDetailBean.name;
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

    public void showCodeList(View view) {
        if (mDetailBean != null && mDetailBean.contents_url != null) {
            Intent intent = new Intent(this, CodeListActivity.class);
            intent.putExtra(CodeListActivity.URL, mDetailBean.contents_url);
            startActivity(intent);
        }
    }

    public void showStargazerList(View view) {
        if (mDetailBean != null) {
            Intent intent = new Intent(this, UserListActivity.class);
            intent.putExtra(UserListActivity.URL, mDetailBean.stargazers_url);
            intent.putExtra(UserListActivity.SEARCHTYPE, UserFragment.SEACH_STARGAZERS);
            startActivity(intent);
        }
    }


    public void showContributorsList(View view) {
        if (mDetailBean != null) {
            Intent intent = new Intent(this, UserListActivity.class);
            intent.putExtra(UserListActivity.URL, mDetailBean.contributors_url);
            intent.putExtra(UserListActivity.SEARCHTYPE, UserFragment.SEACH_CONTRIBUTORS);
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
        if (NetworkUtil.isConnected(UIUtils.getContext())) {
            mPresenter.checkRepBeingStarred(detailBean.owener, detailBean.name);
        }

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

        mDetailBean.contents_url = mDetailBean.contents_url.replace("{+path}", "");

        ///repos/:owner/:repo/readme
        mPresenter.loadReadMe(Constants.BASE_URL + "/repos/" + mDetailBean.owener + "/" + mDetailBean.name + "/readme");

    }

    @Override
    public void showStar() {
        mMultipleActions.setVisibility(View.VISIBLE);
        mStarAction.setTitle("UnStar");
        mStarAction.setIconDrawable(getResources().getDrawable(R.drawable.ic_star));
    }

    @Override
    public void showNoStar() {
        mMultipleActions.setVisibility(View.VISIBLE);
        mStarAction.setTitle("Star");
        mStarAction.setIconDrawable(getResources().getDrawable(R.drawable.ic_star_white));
    }

    @Override
    public void lodingStarOrUnStar(String text) {
        mStarAction.setClickable(false);
        mStarAction.setColorNormalResId(R.color.half_black);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishStarOrUnStar(String text) {
        mStarAction.setClickable(true);
        mMultipleActions.collapse();
        mStarAction.setColorNormalResId(R.color.yellow);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        if (text.equals("staring finish")) {
            showStar();
        }
        if (text.equals("unstar finish")) {
            showNoStar();
        }
    }

    @Override
    public void lodingFork(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishFork(String text) {
        mForkAction.setColorNormalResId(R.color.yellow);
        mForkAction.setClickable(true);
        mMultipleActions.collapse();
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showReadMe(ContentBean contentBean) {

        String text = contentBean.getContent().replaceAll("\\\\n", "");

        byte[] b = Base64.decode(text, Base64.DEFAULT);// 解码后

        mMarkdownView.setMarkDownText(new String((b))); //Displays markdown text
    }

    @Override
    public void loadingReadMe() {
        mDots.showAndPlay();
        mDots.setVisibility(View.VISIBLE);
        mDotsTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishLoadingReadMe() {
        mDots.hideAndStop();
        mDots.setVisibility(View.GONE);
        mDotsTv.setVisibility(View.GONE);
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mMarkdownView.canGoBack()) {
            mMarkdownView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
