package app.yellow.github.core.followlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.yellow.github.R;
import app.yellow.github.core.home.follow.FollowFragment;
import app.yellow.github.core.userdetail.UserDetailActivity;
import app.yellow.github.util.ActivityUtils;

public class FollowListActivity extends AppCompatActivity {

    private String mUsername;

    private String mSearchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);

        mUsername = getIntent().getStringExtra(UserDetailActivity.USER_NAME);
        mSearchType = getIntent().getStringExtra(UserDetailActivity.FOLLOW_TYPE);

        FollowFragment followFragment = new FollowFragment();
        followFragment.setUsername(mUsername);
        followFragment.setSerchType(mSearchType);
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), followFragment, R.id.fragment_container);

    }


}
