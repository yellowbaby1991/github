package app.yellow.github.core.code;

import android.text.format.Formatter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

import app.yellow.github.R;
import app.yellow.github.base.BaseListFragment;
import app.yellow.github.bean.repositorydetail.ContentBean;

public class CodeListFragment extends BaseListFragment<ContentBean> {

    private CodeListListener mListener;

    public CodeListFragment(CodeListListener listener){
        mListener = listener;
    }

    @Override
    protected int getLayout() {
        return R.layout.fraglist_userlist;
    }

    @Override
    protected void goToDetaiActivity(ContentBean contentBean) {
        if (mListener != null){
            mListener.goToNextPage(contentBean);
        }
    }

    @Override
    protected int getItemLayout() {
        return R.layout.codelist_item;
    }

    @Override
    protected void convert(BaseViewHolder helper, ContentBean bean) {
        helper.setText(R.id.filename_tv, bean.getName());
        ImageView imageView = helper.getView(R.id.file_icon);
        if (bean.getType().equals("file")) {
            helper.setText(R.id.filesize_tv, Formatter.formatFileSize(getContext(), Long.valueOf(bean.getSize())));
            Glide.with(getContext()).load(R.drawable.ic_file).crossFade().into(imageView);
        } else if (bean.getType().equals("dir")) {
            Glide.with(getContext()).load(R.drawable.ic_folder_2x).crossFade().into(imageView);
            helper.setText(R.id.filesize_tv, "");
        }
    }

    @Override
    protected void loadMoreRequest() {

    }

    public interface CodeListListener {
        void goToNextPage(ContentBean contentBean);
    }

    @Override
    protected boolean isNeedLoadMore() {
        return false;
    }
}
