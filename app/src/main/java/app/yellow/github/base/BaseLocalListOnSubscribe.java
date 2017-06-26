package app.yellow.github.base;

import org.litepal.crud.DataSupport;

import java.util.List;

import app.yellow.github.config.GithubConfig;
import app.yellow.github.data.db.KeyJsonBean;
import rx.Observable;
import rx.Subscriber;

public abstract class BaseLocalListOnSubscribe<T> implements Observable.OnSubscribe<List<T>> {

    private String mKey;

    public BaseLocalListOnSubscribe(String key) {
        mKey = key;
    }

    @Override
    public void call(Subscriber<? super List<T>> subscriber) {
        List<KeyJsonBean> beans = DataSupport.where("key =?", mKey).find(KeyJsonBean.class);
        if (beans == null || beans.isEmpty()) {
            subscriber.onCompleted();
        } else {
            String data = beans.get(0).getDate();
            if (System.currentTimeMillis() - Long.valueOf(data) > GithubConfig.CAHE_TIME) {
                beans.get(0).delete();
                subscriber.onCompleted();
                return;
            }
            String json = beans.get(0).getJson();
            List<T> list = paresToList(json);
            subscriber.onNext(list);
        }
    }

    protected  abstract List<T> paresToList(String json);
}
