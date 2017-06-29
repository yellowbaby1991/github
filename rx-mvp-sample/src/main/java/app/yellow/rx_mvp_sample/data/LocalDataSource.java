package app.yellow.rx_mvp_sample.data;

import java.util.ArrayList;
import java.util.List;

public class LocalDataSource implements DataSource{
    @Override
    public List loadList() {
        return new ArrayList();//从本地读取缓存
    }
}
