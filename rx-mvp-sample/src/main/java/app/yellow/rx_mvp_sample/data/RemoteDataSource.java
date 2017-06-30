package app.yellow.rx_mvp_sample.data;

import rx.Observable;

public class RemoteDataSource implements DataSource{
    @Override
    public Observable loadList() {
        return null;//从网络加载数据
    }
}
