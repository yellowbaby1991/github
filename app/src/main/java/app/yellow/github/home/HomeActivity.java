package app.yellow.github.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import app.yellow.github.R;
import app.yellow.github.home.explore.ExploreFragment;
import app.yellow.github.util.ActivityUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);

        if (mNavView != null) {
            setupDrawerContent(mNavView);
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),new ExploreFragment(),R.id.fragment_container);
    }

    //Home的展开事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //菜单的点击事件
    private void setupDrawerContent(NavigationView navigationView) {
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                }
                return false;
            }
        });
    }
}
