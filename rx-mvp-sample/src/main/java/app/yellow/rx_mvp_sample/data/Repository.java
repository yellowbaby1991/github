package app.yellow.rx_mvp_sample.data;

import rx.Observable;

public class Repository implements DataSource {

    private DataSource mRemoteDataSource;
    private DataSource mLocalDataSource;

    public Repository(DataSource remoteDataSource, DataSource lemoteDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = lemoteDataSource;
    }


    @Override
    public Observable loadList() {

        Observable localTask = mLocalDataSource.loadList();
        Observable remoteTask = mRemoteDataSource.loadList();

        return Observable.concat(localTask, remoteTask).first();//让本地缓存先读取，网络拉去后执行，谁先拿到数据就返回谁
    }

    @Override
    public Observable getUsersByUrl(String url, int page) {
        return mRemoteDataSource.getUsersByUrl(url, page);
    }
}
