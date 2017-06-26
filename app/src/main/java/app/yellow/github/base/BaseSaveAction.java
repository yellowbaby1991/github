package app.yellow.github.base;

import com.alibaba.fastjson.JSON;

import app.yellow.github.data.db.KeyJsonBean;
import rx.functions.Action1;

public class BaseSaveAction<T> implements Action1<T> {

    private String mKey;

    public BaseSaveAction(String key) {
        mKey = key;
    }

    @Override
    public void call(T t) {
        KeyJsonBean bean = new KeyJsonBean();
        bean.setKey(mKey);
        bean.setJson(JSON.toJSONString(t));
        bean.setDate(String.valueOf(System.currentTimeMillis()));
        bean.save();
    }
}
