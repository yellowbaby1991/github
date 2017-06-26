package app.yellow.github.core.eventlist;

import com.chad.library.adapter.base.BaseViewHolder;

import app.yellow.github.base.BaseListFragment;
import app.yellow.github.bean.home.event.EventBean;

public class EventListFragment  extends BaseListFragment<EventBean> {

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void goToDetaiActivity(EventBean eventBean) {

    }

    @Override
    protected int getItemLayout() {
        return 0;
    }

    @Override
    protected void convert(BaseViewHolder helper, EventBean bean) {

    }

    @Override
    protected void loadMoreRequest() {

    }
}
