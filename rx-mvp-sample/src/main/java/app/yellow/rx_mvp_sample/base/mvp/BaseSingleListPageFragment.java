package app.yellow.rx_mvp_sample.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.yellow.rx_mvp_sample.R;
import app.yellow.rx_mvp_sample.data.LocalDataSource;
import app.yellow.rx_mvp_sample.data.RemoteDataSource;
import app.yellow.rx_mvp_sample.data.Repository;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseSingleListPageFragment<T extends BasePresenter> extends Fragment implements BaseListView<T>{

    protected T mPresenter;

    protected Unbinder unbinder;

    protected BaseListFragment mListFragment;

    @BindView(R.id.empty_tv)
    TextView mEmptyTv;
    @BindView(R.id.error_tv)
    TextView mErrorTv;

    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;

    protected Repository mRepository;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, root);

        initView();

        mRepository = new Repository(new RemoteDataSource(), new LocalDataSource());//得到数据仓库

        mListFragment = getListFragment();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, mListFragment);
        transaction.commit();

        return root;
    }

    protected void initView() {

    }

    protected abstract BaseListFragment getListFragment();

    protected abstract int getLayout();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showEmpty() {
        mEmptyTv.setVisibility(View.VISIBLE);
        mErrorTv.setVisibility(View.GONE);
        mFragmentContainer.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        mEmptyTv.setVisibility(View.GONE);
        mErrorTv.setVisibility(View.VISIBLE);
        mFragmentContainer.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        Toast.makeText(getContext(),"Loading",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(getContext(),"Loading Finish",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showList(List list) {
        mErrorTv.setVisibility(View.GONE);
        mEmptyTv.setVisibility(View.GONE);
        mFragmentContainer.setVisibility(View.VISIBLE);
        mListFragment.createList(list);
    }

    @Override
    public void showMoreAdd(List moreData) {
        mListFragment.updateList(moreData);
    }

    @Override
    public void showLoadMoreError() {
        mListFragment.showLoadMoreError();
    }

    @Override
    public void showLoadMoreEnd() {
        mListFragment.showLoadMoreError();
    }

    @Override
    public void setPresenter(T presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

}
