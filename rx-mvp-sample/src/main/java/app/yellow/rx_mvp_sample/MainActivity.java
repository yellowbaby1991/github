package app.yellow.rx_mvp_sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import app.yellow.rx_mvp_sample.data.LocalDataSource;
import app.yellow.rx_mvp_sample.data.RemoteDataSource;
import app.yellow.rx_mvp_sample.data.Repository;

public class MainActivity extends AppCompatActivity implements Contract.View {

    private Contract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = new Repository(new RemoteDataSource(), new LocalDataSource());//得到数据仓库
        mPresenter = new PrensenterImpl(this, repository);

        mPresenter.loadList();

    }

    public void setPresenter(Contract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        // 展示进度条
    }

    @Override
    public void hideLoading() {
        // 加载成功隐藏进度条
    }

    @Override
    public void showError() {
        // 加载失败
    }

    @Override
    public void showEmpty() {
        // 数据是空的
    }

    @Override
    public void showList(List list) {
        // 加载成功并且有数据耶，展示起来
    }

}
