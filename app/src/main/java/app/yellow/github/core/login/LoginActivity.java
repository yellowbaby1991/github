package app.yellow.github.core.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pnikosis.materialishprogress.ProgressWheel;

import app.yellow.github.R;
import app.yellow.github.bean.userdetail.UserDetailBean;
import app.yellow.github.core.home.HomeActivity;
import app.yellow.github.data.GithubDataRepository;
import app.yellow.github.data.GithubRemoteDataSource;
import app.yellow.github.util.Constants;
import app.yellow.github.util.SPUtils;
import app.yellow.github.util.UIUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.progress_wheel)
    ProgressWheel mProgressWheel;
    @BindView(R.id.et_username)
    TextInputLayout mEtUsername;
    @BindView(R.id.et_password)
    TextInputLayout mEtPassword;
    @BindView(R.id.nologn_ui)
    RelativeLayout mNolognUi;
    @BindView(R.id.login_ui)
    RelativeLayout mLoginUi;
    @BindView(R.id.no_login_avatar_url_img)
    CircleImageView mNoLoginAvatarUrlImg;
    @BindView(R.id.github_tv)
    TextView mGithubTv;
    private SpotsDialog mLodingDialog;

    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLodingDialog = new SpotsDialog(this);

        setPresenter(new LoginPresenter(GithubDataRepository.getInstance(GithubRemoteDataSource.getInstance(), null),
                this));
        mPresenter.checkToken();

    }

    @Override
    public void goToHome(UserDetailBean bean) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(HomeActivity.USER_DETAIL, bean);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoginUi() {
        mGithubTv.setVisibility(View.GONE);
        mLoginUi.setVisibility(View.VISIBLE);
        mNolognUi.setVisibility(View.GONE);
    }

    @OnClick(R.id.login_bt)
    public void login() {
        mPresenter.login("yellowbaby1991", "woainima0");
    }

    @Override
    public void showLodingUi() {
        mGithubTv.setVisibility(View.VISIBLE);
        mLoginUi.setVisibility(View.GONE);
        mNolognUi.setVisibility(View.VISIBLE);
        String avatarUrl = SPUtils.getString(UIUtils.getContext(), Constants.LOGIN_AVATAR_URL, "");

        Glide.with(UIUtils.getContext())
                .load(avatarUrl)
                .error(R.mipmap.icon_login)
                .into(mNoLoginAvatarUrlImg);

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {
        mLodingDialog.show();
    }

    @Override
    public void hideLoading() {
        mLodingDialog.dismiss();
        mProgressWheel.setProgress(0);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }


}
