package app.yellow.github.home.explore;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import app.yellow.github.R;
import app.yellow.github.base.BaseListFragment;
import app.yellow.github.bean.home.explore.RepositoryBean;

public class RepositoryListFragment extends BaseListFragment<RepositoryBean> {

/*    private SearchParams mParams;*/

    private RepositoryListListener mListener;

    public RepositoryListFragment(RepositoryListListener listener) {
        mListener = listener;
    }

/*    public RepositoryListFragment(RepositoryListListener listener,SearchParams params) {
        mListener = listener;
        mParams = params;
    }*/

    @Override
    protected int getLayout() {
        return R.layout.frag_explore_repositorylist;
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
        int index = bean.getUpdated_at().indexOf("T");
        String updateDataString = bean.getUpdated_at().substring(0, index);
        return "Update at " + updateDataString;
    }

    private String getStatistic(RepositoryBean bean) {
        int stargazersCount = bean.getStargazers_count();
        int forkCount = bean.getForks_count();
        int watcherCount = bean.getWatchers_count();
        return "star:" + stargazersCount + "      fork:" + forkCount  + "      watcher:" + watcherCount;
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
