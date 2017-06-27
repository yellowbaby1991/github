package app.yellow.github.core.eventlist;

import android.text.Html;
import android.text.Spanned;
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
        helper.setText(R.id.eventdetail_tv, getEventDetail(bean));
        helper.setText(R.id.body_tv, getBody(bean));
        helper.setText(R.id.update_data_tv, "time：" + bean.getCreated_at());
    }

    private Spanned getEventDetail(EventBean bean) {
        String detail = "<font color='#3BB8E6'>" + bean.getActor().getLogin() + "</font>" + " "
                        + getType(bean) + " "
                        + "<font color='#3BB8E6'>" + bean.getRepo().getName() + "</font>";
        return Html.fromHtml(detail);
    }

    private String getType(EventBean bean) {
        switch (bean.getType()) {
            case "PushEvent":
                return "pushed to";
            case "WatchEvent":
                return "starred";
            case "ForkEvent":
                return "forked";
            case "IssuesEvent":
                return "open issue";
            case "PullRequestEvent":
                return "chosed pull request";
            case "IssueCommentEvent":
                return "commited on issue";
            case "PullRequestReviewEvent":
                return "commented on pull request in";
            case "PullRequestReviewCommentEvent":
                return "commented on pull request in";
        }
        return bean.getType();
    }

    private String getBody(EventBean bean) {
        String body = "";
        if (bean.getPayload() != null && bean.getPayload().getCommits() != null) {
            body += bean.getPayload().getCommits().get(0).getMessage();
        }
        if (bean.getPayload() != null && bean.getPayload().getIssue() != null && bean.getPayload().getIssue().getBody() != null) {
            body += bean.getPayload().getIssue().getBody();
        }
        return body;
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
