package app.yellow.github.core.code;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import app.yellow.github.R;
import app.yellow.github.base.BaseListFragment;
import app.yellow.github.base.BaseListPageFragment;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubLocalDataSource;
import app.yellow.github.data.GithubRemoteDataSource;
import butterknife.BindView;
import butterknife.Unbinder;

public class CodeFragment extends BaseListPageFragment<CodeContract.Presenter> implements CodeContract.View {

    @BindView(R.id.nag_hv)
    HorizontalScrollView mNagHv;
    Unbinder unbinder;
    private String mUrl;

    private Map<TextView, String> mMap;

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.loadContentListByUrl(mUrl);
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
        return new CodeListFragment();
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
