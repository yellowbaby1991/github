package app.yellow.github.core.code;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
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
import app.yellow.github.util.ActivityUtils;
import butterknife.BindView;
import butterknife.Unbinder;
import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;

public class CodeFragment extends BaseListPageFragment<CodeContract.Presenter> implements CodeContract.View, CodeListFragment.CodeListListener {

    @BindView(R.id.nag_ll)
    LinearLayout mNagLL;
    @BindView(R.id.root_tv)
    TextView mRootTv;
    @BindView(R.id.codeview)
    CodeView mCodeview;
    Unbinder unbinder;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    Unbinder unbinder1;
    private String mUrl;

    private TextView mCurrentView;

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mCurrentView = mRootTv;

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
        mFragmentContainer.setVisibility(View.VISIBLE);
        mCodeview.setVisibility(View.GONE);
        mListFragment.createList(list);
    }

    @Override
    public void showContent(ContentBean contentBean) {

        mFragmentContainer.setVisibility(View.GONE);
        mCodeview.setVisibility(View.VISIBLE);

        String text = contentBean.getContent().replaceAll("\\\\n", "");

        byte[] b = Base64.decode(text, Base64.DEFAULT);// 解码后

        mCodeview.setTheme(CodeViewTheme.GITHUB).fillColor();
        //这里的CODE 为需要显示的代码，类型为String，使用的时候自己替换下。
        mCodeview.showCode(new String(b));
    }


    @Override
    public void goToNextPage(final ContentBean contentBean) {
        TextView textView = new TextView(getContext());
        textView.setClickable(true);
        textView.setTextColor(Color.WHITE);
        textView.setText(">  " + contentBean.getName() + "   ");

        if (contentBean.getType().equals("dir")) {
            mPresenter.loadContentListByUrl(contentBean.getUrl());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearTop((TextView) v);
                    mCurrentView = (TextView) v;
                    mPresenter.loadContentListByUrl(contentBean.getUrl());
                }
            });
        }

        if (contentBean.getType().equals("file")) {
            mPresenter.loadContentByUrl(contentBean.getUrl());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearTop((TextView) v);
                    mCurrentView = (TextView) v;
                    mPresenter.loadContentByUrl(contentBean.getUrl());
                }
            });
        }
        mCurrentView = textView;
        mNagLL.addView(textView);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.frag_menu, menu);
        MenuItem refreshItem = menu.findItem(R.id.action_refresh);
        refreshItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ActivityUtils.clearCache();
                mCurrentView.performClick();
                return false;
            }
        });
    }

}
