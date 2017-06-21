package app.yellow.github.home.explore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import app.yellow.github.R;
import app.yellow.github.data.home.explore.RepositoryBean;
import app.yellow.github.util.DisplayUtil;
import app.yellow.github.util.SpacesItemDecoration;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RepositoryListFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.repositorylist_rv)
    RecyclerView mRepositorylistRv;

    private RepositoryListAdapter mAdapter;
    private List<RepositoryBean> mRepositoryList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_explore_repositorylist, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRepositorylistRv.addItemDecoration(new SpacesItemDecoration(DisplayUtil.px2dip(30, 1)));
        mRepositorylistRv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void updateRepositoryList(List<RepositoryBean> repositoryList) {
        mRepositoryList = repositoryList;
        mAdapter = new RepositoryListAdapter();
        mRepositorylistRv.setAdapter(mAdapter);
    }

    class RepositoryListAdapter extends BaseQuickAdapter<RepositoryBean, BaseViewHolder> {
        public RepositoryListAdapter() {
            super(R.layout.explore_repositorylist_item, mRepositoryList);
        }

        @Override
        protected void convert(BaseViewHolder helper, RepositoryBean bean) {
            Glide.with(mContext).load(bean.getOwner().getAvatar_url()).crossFade().into((ImageView) helper.getView(R.id.avatar_url_img));
            helper.setText(R.id.name_tv, bean.getName());
            helper.setText(R.id.language_tv, bean.getLanguage());
            helper.setText(R.id.description_tv, bean.getDescription());
        }
    }
}
