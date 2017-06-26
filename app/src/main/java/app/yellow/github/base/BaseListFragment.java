package app.yellow.github.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import app.yellow.github.R;
import app.yellow.github.config.GithubConfig;
import app.yellow.github.util.DisplayUtil;
import app.yellow.github.util.FastScrollLinearLayoutManager;
import app.yellow.github.util.SpacesItemDecoration;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseListFragment<T> extends Fragment {

    protected List<T> mDataSet;

    protected int mCurrentPage = 1;

    protected Unbinder unbinder;

    @BindView(R.id.list_rv)
    protected RecyclerView mlistRv;

    protected ListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mlistRv.addItemDecoration(new SpacesItemDecoration(DisplayUtil.px2dip(30, 1)));
        mlistRv.setLayoutManager(new FastScrollLinearLayoutManager(getContext()));
    }

    protected abstract int getLayout();

    public void createList(List<T> list) {
        mDataSet = list;
        mAdapter = new ListAdapter();
        mlistRv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                goToDetaiActivity(mDataSet.get(position));
            }
        });
    }

    protected abstract void goToDetaiActivity(T t);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void updateList(List<T> list) {
        mAdapter.addData(list);
        mAdapter.loadMoreComplete();
    }

    public void hideLoadMore(){
        mlistRv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.loadMoreComplete();
            }
        }, 0);
    }

    public void showLoadMoreError() {
        mlistRv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.loadMoreFail();
            }
        }, 0);
    }

    public void showLoadMoreEnd() {
        mlistRv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.loadMoreEnd();
            }
        }, 0);
    }

    protected abstract int getItemLayout();

    protected abstract void convert(BaseViewHolder helper, T bean);

    protected abstract void loadMoreRequest();

    public void backToTop() {
        mlistRv.scrollToPosition(0);
    }

    public class ListAdapter extends BaseRecyclerViewAdapter<T> {

        public ListAdapter() {
            super(getItemLayout(), mDataSet);
        }

        @Override
        protected void loadMoreRequest() {
            mlistRv.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (getItemCount() < GithubConfig.PER_PAGE){
                        loadMoreEnd();
                    }else {
                        BaseListFragment.this.loadMoreRequest();
                    }
                }
            }, 0);
        }

        @Override
        protected void convert(BaseViewHolder helper, T bean) {
            BaseListFragment.this.convert(helper, bean);
        }
    }
}