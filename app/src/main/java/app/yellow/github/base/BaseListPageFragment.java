package app.yellow.github.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;

import app.yellow.github.R;
import app.yellow.github.util.ActivityUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;

public abstract class BaseListPageFragment<T extends BasePresenter> extends Fragment implements BaseListView<T>{

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @BindView(R.id.backtotp_fa)
    protected FloatingActionButton mBackToTopButon;

    protected SpotsDialog mLodingDialog;

    protected Unbinder unbinder;

    protected T mPresenter;

    protected BaseListFragment mListFragment;

    protected boolean mShowHome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View root = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, root);

        initView();

        setPresenter(createPresenter());
        mListFragment = getListFragment();
        ActivityUtils.addFragmentToActivity(getChildFragmentManager(), mListFragment, R.id.fragment_container);

        return root;
    }

    protected void initView() {

        mLodingDialog = new SpotsDialog(getContext());

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();//得到Toolbar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//打开开关
            if (!mShowHome) {
                actionBar.setHomeAsUpIndicator(R.drawable.ic_action_navigation_arrow_back_inverted);
            } else {
                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            }
            setActionBarTitle(actionBar);
        }

        mBackToTopButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListFragment.backToTop();
            }
        });
    }

    public void setIsShowHome(boolean showHome) {
        mShowHome = showHome;
    }

    protected abstract void setActionBarTitle(ActionBar actionBar);

    protected abstract T createPresenter();

    protected abstract BaseListFragment getListFragment();

    protected abstract int getLayout();

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
    }

    public void setPresenter(T presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showEmpty() {
        mLodingDialog.dismiss();
    }

    @Override
    public void showLoading() {
        mLodingDialog.show();
    }

    @Override
    public void hideLoading() {
        mLodingDialog.dismiss();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showList(List list) {
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




}
