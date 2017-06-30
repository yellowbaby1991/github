package app.yellow.rx_mvp_sample.userlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.yellow.rx_mvp_sample.R;
import app.yellow.rx_mvp_sample.util.ActivityUtils;


public class UserListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        UserListPageFragment fragment = new UserListPageFragment();
        fragment.setUrl("https://api.github.com/repos/ReactiveX/RxJava/stargazers");
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);

    }


}
