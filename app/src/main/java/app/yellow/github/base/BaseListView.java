package app.yellow.github.base;

import java.util.List;

public interface BaseListView<T> extends BaseView<T> {

    void showList(List list);

    void showMoreAdd(List moreData);

    void showLoadMoreError();

    void showLoadMoreEnd();
}