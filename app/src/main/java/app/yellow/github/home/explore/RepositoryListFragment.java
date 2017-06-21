package app.yellow.github.home.explore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

import app.yellow.github.R;
import app.yellow.github.data.home.explore.RepositoryBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RepositoryListFragment extends Fragment {

    @BindView(R.id.repositorylist_lv)
    ListView mRepositorylistLv;
    Unbinder unbinder;

    private QuickAdapter<RepositoryBean> mQuickAdapter;
    private List<RepositoryBean> repositoryList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_explore_repositorylist, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        repositoryList = new ArrayList<>();
        mQuickAdapter = new QuickAdapter<RepositoryBean>(getActivity(), R.layout.explore_repositorylist_item, repositoryList) {
            @Override
            protected void convert(BaseAdapterHelper helper, RepositoryBean bean) {
                helper.setImageUrl(R.id.avatar_url_img, bean.getOwner().getAvatar_url());
                helper.setText(R.id.name_tv, bean.getName());
                helper.setText(R.id.language_tv, bean.getLanguage());
                helper.setText(R.id.description_tv, bean.getDescription());
            }
        };
        mRepositorylistLv.setAdapter(mQuickAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void updateRepositoryList(List<RepositoryBean> repositoryList) {
        mQuickAdapter.replaceAll(repositoryList);
        mQuickAdapter.notifyDataSetChanged();
    }
}
