package app.yellow.github.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.yellow.github.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void goToHome() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }
}
