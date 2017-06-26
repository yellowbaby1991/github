package app.yellow.github.core.home.event;

import android.support.v7.app.ActionBar;

import app.yellow.github.base.BaseListFragment;
import app.yellow.github.base.BaseListPageFragment;

public class EventFragment extends BaseListPageFragment<EventContract.Presenter> {

    @Override
    protected void setActionBarTitle(ActionBar actionBar) {

    }

    @Override
    protected EventContract.Presenter createPresenter() {
        return null;
    }

    @Override
    protected BaseListFragment getListFragment() {
        return null;
    }

    @Override
    protected int getLayout() {
        return 0;
    }
}
