package app.yellow.github.core.code;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.yellow.github.R;
import app.yellow.github.base.BaseListFragment;
import app.yellow.github.base.BaseListPageFragment;
import app.yellow.github.bean.repositorydetail.ContentBean;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubLocalDataSource;
import app.yellow.github.data.GithubRemoteDataSource;
import butterknife.BindView;

public class CodeFragment extends BaseListPageFragment<CodeContract.Presenter> implements CodeContract.View, CodeListFragment.CodeListListener {

    @BindView(R.id.nag_ll)
    LinearLayout mNagLL;
    @BindView(R.id.root_tv)
    TextView mRootTv;
    private String mUrl;

    private int mCurrent = 0;

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRootTv.setClickable(true);
        mRootTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTop(mRootTv);
                mPresenter.loadContentListByUrl(mUrl);
            }
        });
        mPresenter.loadContentListByUrl(mUrl);

    }

    private void clearTop(TextView rootTv) {
        for (int index = mNagLL.getChildCount() - 1; index > 0; index--) {
            if (mNagLL.getChildAt(index) == rootTv) {
                break;
            }
            mNagLL.removeViewAt(index);
        }
    }


    @Override
    protected void setActionBarTitle(ActionBar actionBar) {
        actionBar.setTitle("");
    }

    @Override
    protected CodeContract.Presenter createPresenter() {
        return new CodePresenter(
                GithubDataRepository.getInstance(GithubRemoteDataSource.getInstance(), GithubLocalDataSource.getInstance()),
                this) {
        };
    }

    @Override
    protected BaseListFragment getListFragment() {
        return new CodeListFragment(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.frag_code_list;
    }

    @Override
    public void showContentList(List list) {
        mListFragment.createList(list);
    }


    @Override
    public void goToNextPage(final ContentBean contentBean) {
        TextView textView = new TextView(getContext());
        textView.setClickable(true);
        textView.setTextColor(Color.WHITE);
        textView.setText(">  " + contentBean.getName() + "   ");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTop((TextView) v);
                mPresenter.loadContentListByUrl(contentBean.getUrl());
            }
        });
        mNagLL.addView(textView);
        mPresenter.loadContentListByUrl(contentBean.getUrl());
    }
}
