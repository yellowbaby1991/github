package app.yellow.rx_mvp_sample.data;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class LocalDataSource implements DataSource {
    @Override
    public Observable loadList() {
        return Observable.create(new Observable.OnSubscribe<List>() {

            @Override
            public void call(Subscriber<? super List> subscriber) {
                List list = new ArrayList();//从本地读取的缓存
                subscriber.onNext(list);
            }
        });//从本地读取缓存
    }
}
