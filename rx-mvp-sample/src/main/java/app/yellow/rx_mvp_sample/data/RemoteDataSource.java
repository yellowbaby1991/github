package app.yellow.rx_mvp_sample.data;

import app.yellow.rx_mvp_sample.config.GithubConfig;
import app.yellow.rx_mvp_sample.util.RetrofitUtil;
import rx.Observable;

public class RemoteDataSource implements DataSource {

    @Override
    public Observable loadList() {
        return null;//从网络加载数据
    }

    @Override
    public Observable getUsersByUrl(String url, int page) {
        return RetrofitUtil.getUserService().getUsersByUrl(url, page, GithubConfig.PER_PAGE);
    }
}
