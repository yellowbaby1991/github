package app.yellow.github.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import com.pnikosis.materialishprogress.ProgressWheel;

import app.yellow.github.R;
import app.yellow.github.bean.userdetail.UserDetailBean;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubRemoteDataSource;
import app.yellow.github.home.HomeActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.progress_wheel)
    ProgressWheel mProgressWheel;
    @BindView(R.id.avatar_url_img)
    CircleImageView mAvatarUrlImg;
    @BindView(R.id.et_username)
    TextInputLayout mEtUsername;
    @BindView(R.id.et_password)
    TextInputLayout mEtPassword;
    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setPresenter(new LoginPresenter(GithubDataRepository.getInstance(GithubRemoteDataSource.getInstance(), null),
                this));
        mPresenter.login("yellowbaby1991", "woainima0");
    }

    @Override
    public void goToHome(UserDetailBean bean) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(HomeActivity.USER_DETAIL, bean);
        startActivity(intent);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {
        mProgressWheel.setProgress(1);
    }

    @Override
    public void hideLoading() {
        mProgressWheel.setProgress(0);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }


}
