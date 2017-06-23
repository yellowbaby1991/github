package app.yellow.github.home.explore;

import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import app.yellow.github.R;
import app.yellow.github.base.BaseListFragment;
import app.yellow.github.bean.home.explore.UserBean;
import app.yellow.github.bean.userdetail.UserDetailBean;
import app.yellow.github.userdetail.UserDetailActivity;

public class UserListFragment extends BaseListFragment<UserBean> {

    public final static String USER_DETAIL = "user_detail";

    private UserListFragment.UserListListener mListener;

    public UserListFragment(UserListFragment.UserListListener listener) {
        mListener = listener;
    }

    @Override
    protected int getLayout() {
        return R.layout.frag_explore_userlist;
    }

    @Override
    protected void goToDetaiActivity(UserBean bean) {
        Intent intent = new Intent(getContext(), UserDetailActivity.class);
        UserDetailBean detailBean = createDetailBean(bean);
        intent.putExtra(USER_DETAIL, detailBean);
        startActivity(intent);
    }

    protected UserDetailBean createDetailBean(UserBean bean) {
        UserDetailBean detailBean = new UserDetailBean();
        detailBean.avatarUrl = bean.getAvatar_url();
        detailBean.name = bean.getLogin();
        return detailBean;
    }


    @Override
    protected int getItemLayout() {
        return R.layout.explore_userlist_item;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserBean bean) {
        Glide.with(getContext()).load(bean.getAvatar_url()).crossFade().into((ImageView) helper.getView(R.id.avatar_url_img));
        helper.setText(R.id.name_tv, bean.getLogin());
    }

    @Override
    protected void loadMoreRequest() {
        if (mListener != null) {
            mCurrentPage++;
            mListener.loadMoreUser(mCurrentPage);
        }
    }

    public interface UserListListener {
        void loadMoreUser(int nextPage);
    }

}
