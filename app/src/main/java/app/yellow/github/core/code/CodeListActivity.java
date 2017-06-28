package app.yellow.github.core.code;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.yellow.github.R;
import app.yellow.github.util.ActivityUtils;

public class CodeListActivity extends AppCompatActivity{

    public static final String URL = "url";

    private String mUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);

        mUrl = getIntent().getStringExtra(URL);

        showPage(mUrl);

    }

    private void showPage(String url) {
        CodeFragment codeFragment = new CodeFragment();
        codeFragment.setUrl(url);
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), codeFragment, R.id.fragment_container);
    }

}
