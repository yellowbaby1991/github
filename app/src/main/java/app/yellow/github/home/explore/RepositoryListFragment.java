package app.yellow.github.home.explore;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import app.yellow.github.R;
import app.yellow.github.base.BaseListFragment;
import app.yellow.github.bean.home.explore.RepositoryBean;

public class RepositoryListFragment extends BaseListFragment<RepositoryBean> {

    private RepositoryListListener mListener;

    public RepositoryListFragment(RepositoryListListener listener) {
        mListener = listener;
    }

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
