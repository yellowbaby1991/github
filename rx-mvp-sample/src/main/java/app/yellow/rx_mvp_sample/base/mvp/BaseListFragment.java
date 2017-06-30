package app.yellow.rx_mvp_sample.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import app.yellow.rx_mvp_sample.R;
import app.yellow.rx_mvp_sample.config.GithubConfig;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseListFragment<T> extends Fragment {

    protected List<T> mDataSet;

    @BindView(R.id.list_rv)
    protected RecyclerView mlistRv;

    protected Unbinder unbinder;

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
        mlistRv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    protected abstract int getLayout();

    //第一次展现数据
    public void createList(List<T> list) {
        mDataSet = list;
        mAdapter = new ListAdapter();
        mlistRv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                itemClick(mDataSet.get(position));
            }
        });
    }

    protected abstract void itemClick(T t);

    //下拉加载更多显示数据
    public void updateList(List<T> list) {
        mAdapter.addData(list);
        mAdapter.loadMoreComplete();
    }


    public void hideLoadMore() {
        mlistRv.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAdapter != null) {
                    mAdapter.loadMoreComplete();
                }
            }
        }, 0);
    }

    public void showLoadMoreError() {
        mlistRv.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAdapter != null){
                    mAdapter.loadMoreFail();
                }
            }
        }, 0);
    }

    public void showLoadMoreEnd() {
        mlistRv.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAdapter != null) {
                    mAdapter.loadMoreEnd();
                }
            }
        }, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected abstract int getItemLayout();

    protected abstract void convert(BaseViewHolder helper, T bean);

    protected abstract void loadMoreRequest();

    protected boolean isNeedLoadMore() {
        return true;
    }

    public class ListAdapter extends BaseQuickAdapter<T, BaseViewHolder> {

        public ListAdapter() {

            super(getItemLayout(), mDataSet);

            setOnLoadMoreListener(
                    new RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            loadMoreRequest();
                        }
                    }
            );
        }

        protected void loadMoreRequest() {
            mlistRv.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (getItemCount() < GithubConfig.PER_PAGE) {//PageSize
                        loadMoreEnd();
                    } else {
                        if (isNeedLoadMore()) {
                            BaseListFragment.this.loadMoreRequest();
                        } else {
                            loadMoreEnd();
                        }
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