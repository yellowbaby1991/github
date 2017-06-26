package app.yellow.github.core.eventlist;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import app.yellow.github.R;
import app.yellow.github.base.BaseListFragment;
import app.yellow.github.bean.home.event.EventBean;

public class EventListFragment extends BaseListFragment<EventBean> {

    public EventListFragment(EventListListener listener) {
        mListener = listener;
    }

    @Override
    protected int getLayout() {
        return R.layout.fraglist_eventlist;
    }

    @Override
    protected void goToDetaiActivity(EventBean eventBean) {

    }

    @Override
    protected int getItemLayout() {
        return R.layout.eventlist_item;
    }

    @Override
    protected void convert(BaseViewHolder helper, EventBean bean) {
        Glide.with(getContext()).load(bean.getActor().getAvatar_url()).crossFade().into((ImageView) helper.getView(R.id.avatar_url_img));
        helper.setText(R.id.actor_name_tv, bean.getActor().getLogin() + " ");
        helper.setText(R.id.type_tv, bean.getType() + " ");
        helper.setText(R.id.repo_tv, bean.getRepo().getName());
        if (bean.getPayload() != null && bean.getPayload().getIssue() != null && bean.getPayload().getIssue().getBody() == null) {
            helper.setText(R.id.body_tv, bean.getPayload().getIssue().getBody());
        }
        helper.setText(R.id.update_data_tv, "最近更新：" + bean.getCreated_at());
    }

    @Override
    protected void loadMoreRequest() {
        if (mListener != null) {
            mCurrentPage++;
            mListener.loadMoreEvent(mCurrentPage);
        }
    }

    private EventListListener mListener;

    public interface EventListListener {
        void loadMoreEvent(int nextPage);
    }
}
