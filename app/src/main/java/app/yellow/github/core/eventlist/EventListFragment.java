package app.yellow.github.core.eventlist;

import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import app.yellow.github.R;
import app.yellow.github.base.BaseListFragment;
import app.yellow.github.bean.home.event.EventBean;
import app.yellow.github.bean.userdetail.UserDetailBean;
import app.yellow.github.core.repositorydetail.RepositroyDetailActivity;
import app.yellow.github.core.userdetail.UserDetailActivity;
import app.yellow.github.core.userlist.UserListFragment;

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

        TextView detailView = helper.getView(R.id.eventdetail_tv);
        detailView.setClickable(true);
        detailView.setMovementMethod(LinkMovementMethod.getInstance());
        detailView.setText(getEventDetail(bean));

        helper.setText(R.id.eventdetail_tv, getEventDetail(bean));
        helper.setText(R.id.body_tv, getBody(bean));
        helper.setText(R.id.update_data_tv, "time：" + bean.getCreated_at());
    }

    private SpannableString getEventDetail(final EventBean bean) {
        View.OnClickListener showUserDetail = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserDetailActivity.class);
                UserDetailBean detailBean = new UserDetailBean();
                detailBean.name = bean.getActor().getLogin();
                intent.putExtra(UserListFragment.USER_DETAIL, detailBean);
                startActivity(intent);
            }
        };
        View.OnClickListener showRepDetail = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RepositroyDetailActivity.class);
                intent.putExtra(RepositroyDetailActivity.FULL_NAME, bean.getRepo().getUrl());
                startActivity(intent);
            }
        };

        String detail = bean.getActor().getLogin() + " "
                + getType(bean) + " "
                + bean.getRepo().getName();

        SpannableString spanableInfo = new SpannableString(detail);
        int end = spanableInfo.length();

        spanableInfo.setSpan(new Clickable(showUserDetail), 0, bean.getActor().getLogin().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanableInfo.setSpan(new Clickable(showRepDetail), bean.getActor().getLogin().length() + " ".length() * 2 + getType(bean).length(), end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spanableInfo;
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


    class Clickable extends ClickableSpan implements View.OnClickListener {
        private final View.OnClickListener mListener;

        public Clickable(View.OnClickListener l) {
            mListener = l;
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }
    }
}
