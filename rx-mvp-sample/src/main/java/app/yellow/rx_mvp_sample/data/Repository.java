package app.yellow.rx_mvp_sample.data;

import java.util.List;

public class Repository implements DataSource{

    private DataSource mRemoteDataSource;
    private DataSource mLocalDataSource;

    public Repository(DataSource remoteDataSource,DataSource lemoteDataSource){
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = lemoteDataSource;
    }


    @Override
    public List loadList() {
        List remote = mRemoteDataSource.loadList();
        List local = mLocalDataSource.loadList();
        // ....让两个数据源同时去加载数据，谁先加载完成就返回谁的
        return null;
    }
}
