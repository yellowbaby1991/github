package app.yellow.github.core.repositorylist;

import android.content.Intent;
import android.text.format.Formatter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import app.yellow.github.R;
import app.yellow.github.base.BaseListFragment;
import app.yellow.github.bean.home.explore.RepositoryBean;
import app.yellow.github.bean.repositorydetail.RepositoryDetailBean;
import app.yellow.github.core.repositorydetail.RepositroyDetailActivity;
import app.yellow.github.util.ActivityUtils;

public class RepositoryListFragment extends BaseListFragment<RepositoryBean> {

    public final static String REPOSITORY_DETAIL = "repository_detail";

    private RepositoryListListener mListener;

    public RepositoryListFragment(RepositoryListListener listener) {
        mListener = listener;
    }

    @Override
    protected int getLayout() {
        return R.layout.fraglist_repositorylist;
    }

    @Override
    protected void goToDetaiActivity(RepositoryBean bean) {
        Intent intent = new Intent(getContext(), RepositroyDetailActivity.class);
        RepositoryDetailBean detailBean = createDetailBean(bean);
        intent.putExtra(REPOSITORY_DETAIL, detailBean);
        startActivity(intent);
    }

    protected RepositoryDetailBean createDetailBean(RepositoryBean bean) {
        RepositoryDetailBean detailBean = new RepositoryDetailBean();
        detailBean.name = bean.getName();
        detailBean.authority = (bean.isPrivateX() ? "Private" : "Public");
        detailBean.avatarUrl = bean.getOwner().getAvatar_url();
        detailBean.capacity = Formatter.formatFileSize(getContext(), Long.valueOf(bean.getSize()));
        detailBean.createdAt = ActivityUtils.dealDataString(bean.getCreated_at());
        detailBean.lastUpdated = ActivityUtils.dealDataString(bean.getUpdated_at());
        detailBean.description = bean.getDescription();
        detailBean.forksCount = bean.getForks_count() + "";
        detailBean.stargazersCount = bean.getStargazers_count() + "";
        detailBean.issuesCount = bean.getOpen_issues_count() + "";
        detailBean.owener = bean.getOwner().getLogin();
        detailBean.language = bean.getLanguage();
        detailBean.forks_url = bean.getForks_url();
        detailBean.contributors_url = bean.getContributors_url();
        detailBean.stargazers_url = bean.getStargazers_url();
        return detailBean;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.explore_repositorylist_item;
    }

    @Override
    protected void convert(BaseViewHolder helper, RepositoryBean bean) {
        Glide.with(getContext()).load(bean.getOwner().getAvatar_url()).crossFade().into((ImageView) helper.getView(R.id.avatar_url_img));
        helper.setText(R.id.name_tv, bean.getName());
        helper.setText(R.id.language_tv, bean.getLanguage());
        helper.setText(R.id.description_tv, bean.getDescription());
        helper.setText(R.id.statistics_tv, getStatistic(bean));
        helper.setText(R.id.update_data_tv, getUpdateData(bean));
    }

    private String getUpdateData(RepositoryBean bean) {
        return "Update at " + ActivityUtils.dealDataString(bean.getUpdated_at());
    }

    private String getStatistic(RepositoryBean bean) {
        int stargazersCount = bean.getStargazers_count();
        int forkCount = bean.getForks_count();
        int watcherCount = bean.getWatchers_count();
        return "star:" + stargazersCount + "      fork:" + forkCount + "      watcher:" + watcherCount;
    }

    @Override
    protected void loadMoreRequest() {
        if (mListener != null) {
            mCurrentPage++;
            mListener.loadMoreRepository(mCurrentPage);
        }
    }

    public interface RepositoryListListener {
        void loadMoreRepository(int nextPage);
    }

}
