package app.yellow.github.core.repositorydetail;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.mukesh.MarkdownView;
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
import app.yellow.github.core.userlist.UserFragment;
import app.yellow.github.core.userlist.UserListActivity;
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
                mPresenter.forkRep(mDetailBean.owener, mDetailBean.name);
                mForkAction.setClickable(false);
                mForkAction.setColorNormalResId(R.color.half_black);
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

    public void showEventList(View view) {
        if (mDetailBean != null) {
            Intent intent = new Intent(this, EventListActivity.class);
            intent.putExtra(EventListActivity.USER_NAME, mDetailBean.owener);
            intent.putExtra(EventListActivity.SEACH_TYPE, EventFragment.SEACH_BY_REPS);
            intent.putExtra(EventListActivity.REP_NAME, mDetailBean.name);
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
        mPresenter.checkRepBeingStarred(detailBean.owener, detailBean.name);
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

        String c = "UmV0cm9maXQKPT09PT09PT0KClR5cGUtc2FmZSBIVFRQIGNsaWVudCBmb3Ig\\nQW5kcm9pZCBhbmQgSmF2YSBieSBTcXVhcmUsIEluYy4KCkZvciBtb3JlIGlu\\nZm9ybWF0aW9uIHBsZWFzZSBzZWUgW3RoZSB3ZWJzaXRlXVsxXS4KCgpEb3du\\nbG9hZAotLS0tLS0tLQoKRG93bmxvYWQgW3RoZSBsYXRlc3QgSkFSXVsyXSBv\\nciBncmFiIHZpYSBNYXZlbjoKYGBgeG1sCjxkZXBlbmRlbmN5PgogIDxncm91\\ncElkPmNvbS5zcXVhcmV1cC5yZXRyb2ZpdDI8L2dyb3VwSWQ+CiAgPGFydGlm\\nYWN0SWQ+cmV0cm9maXQ8L2FydGlmYWN0SWQ+CiAgPHZlcnNpb24+Mi4zLjA8\\nL3ZlcnNpb24+CjwvZGVwZW5kZW5jeT4KYGBgCm9yIEdyYWRsZToKYGBgZ3Jv\\nb3Z5CmNvbXBpbGUgJ2NvbS5zcXVhcmV1cC5yZXRyb2ZpdDI6cmV0cm9maXQ6\\nMi4zLjAnCmBgYAoKU25hcHNob3RzIG9mIHRoZSBkZXZlbG9wbWVudCB2ZXJz\\naW9uIGFyZSBhdmFpbGFibGUgaW4gW1NvbmF0eXBlJ3MgYHNuYXBzaG90c2Ag\\ncmVwb3NpdG9yeV1bc25hcF0uCgpSZXRyb2ZpdCByZXF1aXJlcyBhdCBtaW5p\\nbXVtIEphdmEgNyBvciBBbmRyb2lkIDIuMy4KCgpQcm9HdWFyZAotLS0tLS0t\\nLQoKSWYgeW91IGFyZSB1c2luZyBQcm9HdWFyZCB5b3UgbWlnaHQgbmVlZCB0\\nbyBhZGQgdGhlIGZvbGxvd2luZyBvcHRpb25zOgpgYGAKLWRvbnR3YXJuIG9r\\naW8uKioKLWRvbnR3YXJuIGphdmF4LmFubm90YXRpb24uKioKYGBgCgoKTGlj\\nZW5zZQo9PT09PT09CgogICAgQ29weXJpZ2h0IDIwMTMgU3F1YXJlLCBJbmMu\\nCgogICAgTGljZW5zZWQgdW5kZXIgdGhlIEFwYWNoZSBMaWNlbnNlLCBWZXJz\\naW9uIDIuMCAodGhlICJMaWNlbnNlIik7CiAgICB5b3UgbWF5IG5vdCB1c2Ug\\ndGhpcyBmaWxlIGV4Y2VwdCBpbiBjb21wbGlhbmNlIHdpdGggdGhlIExpY2Vu\\nc2UuCiAgICBZb3UgbWF5IG9idGFpbiBhIGNvcHkgb2YgdGhlIExpY2Vuc2Ug\\nYXQKCiAgICAgICBodHRwOi8vd3d3LmFwYWNoZS5vcmcvbGljZW5zZXMvTElD\\nRU5TRS0yLjAKCiAgICBVbmxlc3MgcmVxdWlyZWQgYnkgYXBwbGljYWJsZSBs\\nYXcgb3IgYWdyZWVkIHRvIGluIHdyaXRpbmcsIHNvZnR3YXJlCiAgICBkaXN0\\ncmlidXRlZCB1bmRlciB0aGUgTGljZW5zZSBpcyBkaXN0cmlidXRlZCBvbiBh\\nbiAiQVMgSVMiIEJBU0lTLAogICAgV0lUSE9VVCBXQVJSQU5USUVTIE9SIENP\\nTkRJVElPTlMgT0YgQU5ZIEtJTkQsIGVpdGhlciBleHByZXNzIG9yIGltcGxp\\nZWQuCiAgICBTZWUgdGhlIExpY2Vuc2UgZm9yIHRoZSBzcGVjaWZpYyBsYW5n\\ndWFnZSBnb3Zlcm5pbmcgcGVybWlzc2lvbnMgYW5kCiAgICBsaW1pdGF0aW9u\\ncyB1bmRlciB0aGUgTGljZW5zZS4KCgogWzFdOiBodHRwOi8vc3F1YXJlLmdp\\ndGh1Yi5pby9yZXRyb2ZpdC8KIFsyXTogaHR0cHM6Ly9zZWFyY2gubWF2ZW4u\\nb3JnL3JlbW90ZV9jb250ZW50P2c9Y29tLnNxdWFyZXVwLnJldHJvZml0MiZh\\nPXJldHJvZml0JnY9TEFURVNUCiBbc25hcF06IGh0dHBzOi8vb3NzLnNvbmF0\\neXBlLm9yZy9jb250ZW50L3JlcG9zaXRvcmllcy9zbmFwc2hvdHMvCg==\\n";

        c = c.replaceAll("\\\\n","");

        byte[] m = Base64.decode(c,Base64.DEFAULT);// 解码后

        MarkdownView markdownView = (MarkdownView) findViewById(R.id.markdown_view);
        markdownView.setMarkDownText(new String((m))); //Displays markdown text
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
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishStarOrUnStar(String text) {
        mStarAction.setClickable(true);
        mMultipleActions.collapse();
        mStarAction.setColorNormalResId(R.color.yellow);
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
        if (text.equals("staring finish")){
            showStar();
        }
        if (text.equals("unstar finish")){
            showNoStar();
        }
    }

    @Override
    public void lodingFork(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishFork(String text) {
        mForkAction.setColorNormalResId(R.color.yellow);
        mForkAction.setClickable(true);
        mMultipleActions.collapse();
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
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
