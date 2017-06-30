package app.yellow.rx_mvp_sample.userlist;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import app.yellow.rx_mvp_sample.R;
import app.yellow.rx_mvp_sample.base.mvp.BaseListFragment;
import app.yellow.rx_mvp_sample.bean.UserBean;

public class UserListFragment extends BaseListFragment<UserBean> {

    @Override
    protected int getLayout() {
        return R.layout.user_list_fragment;
    }

    @Override
    protected void itemClick(UserBean userBean) {

    }

    @Override
    protected int getItemLayout() {
        return R.layout.user_list_item;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserBean bean) {
        Glide.with(getContext()).load(bean.getAvatar_url()).crossFade().into((ImageView) helper.getView(R.id.avatar_url_img));
        helper.setText(R.id.name_tv, bean.getLogin());
    }

    private UserListFragment.UserListListener mListener;

    public UserListFragment(UserListFragment.UserListListener listener) {
        mListener = listener;
    }

    protected int mCurrentPage = 1;

    public interface UserListListener {
        void loadMoreUser(int nextPage);
    }

    @Override
    protected void loadMoreRequest() {
        if (mListener != null) {
            mCurrentPage++;
            mListener.loadMoreUser(mCurrentPage);
        }
    }
}
