package app.yellow.rx_mvp_sample.data;

import java.util.ArrayList;
import java.util.List;

public class RemoteDataSource implements DataSource{
    @Override
    public List loadList() {
        return new ArrayList();//从网络加载数据
    }
}
