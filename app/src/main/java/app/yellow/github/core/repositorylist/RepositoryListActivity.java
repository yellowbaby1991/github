package app.yellow.github.core.repositorylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.yellow.github.R;
import app.yellow.github.core.home.repository.RepositoryFragment;
import app.yellow.github.core.userdetail.UserDetailActivity;
import app.yellow.github.util.ActivityUtils;

public class RepositoryListActivity extends AppCompatActivity {

    private String mUsername;

    private String mSearchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);

        mUsername = getIntent().getStringExtra(UserDetailActivity.USER_NAME);
        mSearchType = getIntent().getStringExtra(UserDetailActivity.REP_TYPE);

        RepositoryFragment repositoryFragment = new RepositoryFragment();
        repositoryFragment.setUsername(mUsername);
        repositoryFragment.setSerchType(mSearchType);
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), repositoryFragment, R.id.fragment_container);

    }


}
