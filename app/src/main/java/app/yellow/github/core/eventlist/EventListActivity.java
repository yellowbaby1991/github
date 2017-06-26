package app.yellow.github.core.eventlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.yellow.github.R;
import app.yellow.github.core.home.event.EventFragment;
import app.yellow.github.util.ActivityUtils;

public class EventListActivity extends AppCompatActivity {

    public static final String USER_NAME = "user_name";

    public static final String SEACH_TYPE = "seach_type";

    public static final String REP_NAME = "rep_name";

    private String mUsername;

    private String mSeachType;

    private String mRepname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);

        mUsername = getIntent().getStringExtra(USER_NAME);
        mSeachType = getIntent().getStringExtra(SEACH_TYPE);
        mRepname = getIntent().getStringExtra(REP_NAME);

        EventFragment eventFragment = new EventFragment();
        eventFragment.setUsername(mUsername);
        eventFragment.setSeachType(mSeachType);
        eventFragment.setReponame(mRepname);
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), eventFragment, R.id.fragment_container);

    }


}
