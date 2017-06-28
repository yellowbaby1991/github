package app.yellow.github.core.code;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import app.yellow.github.R;
import app.yellow.github.bean.repositorydetail.ContentBean;
import app.yellow.github.util.ActivityUtils;

public class CodeListActivity extends AppCompatActivity{

    public static final String URL = "url";

    private String mUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);

        EventBus.getDefault().register(this);

        mUrl = getIntent().getStringExtra(URL);

        showPage(mUrl);

    }

    @Subscribe
    public void goToNextPage(ContentBean contentBean) {
        if (contentBean.getType().equals("dir")) {
            showPage(contentBean.getUrl());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }

    private void showPage(String url) {
        CodeFragment codeFragment = new CodeFragment();
        codeFragment.setUrl(url);
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), codeFragment, R.id.fragment_container);
    }

}
