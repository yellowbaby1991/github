package app.yellow.github.core.userlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.yellow.github.R;
import app.yellow.github.util.ActivityUtils;

public class UserListActivity extends AppCompatActivity {

    public static final String URL = "url";

    public static final String SEARCHTYPE = "search_type";

    private String mUrl;

    private String mSearchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);

        mUrl = getIntent().getStringExtra(URL);
        mSearchType = getIntent().getStringExtra(SEARCHTYPE);

        UserFragment userFragment = new UserFragment();
        userFragment.setUrl(mUrl);
        userFragment.setSeachType(mSearchType);
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), userFragment, R.id.fragment_container);

    }


}
