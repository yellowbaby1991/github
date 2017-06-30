package app.yellow.rx_mvp_sample.base.mvp;

import java.util.List;

public interface BaseListView<T> extends BaseView<T> {

    //展现第一次进入的时候展现的数据
    void showList(List list);

    //展现下拉加载后新刷新的数据
    void showMoreAdd(List moreData);

    //展现下拉加载的错误信息
    void showLoadMoreError();

    //展现下拉加载的错误信息
    void showLoadMoreEnd();
}