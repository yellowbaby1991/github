package app.yellow.rx_mvp_sample.userlist;

import android.os.Bundle;
import android.support.annotation.Nullable;

import app.yellow.rx_mvp_sample.R;
import app.yellow.rx_mvp_sample.base.mvp.BaseListFragment;
import app.yellow.rx_mvp_sample.base.mvp.BaseSingleListPageFragment;

public class UserListPageFragment extends BaseSingleListPageFragment<UserListContract.Presenter> implements UserListContract.View,UserListFragment.UserListListener {

    private String mUrl;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter = new UserListPresenter(mRepository, this);

        mPresenter.searchUsersByUrl(mUrl);
    }

    @Override
    protected BaseListFragment getListFragment() {
        return new UserListFragment(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.user_list_page;
    }

    @Override
    public void loadMoreUser(int nextPage) {
        mPresenter.loadMoreUsersByUrl(mUrl, nextPage);
    }

    public void setUrl(String url) {
        mUrl = url;
    }

}
