package app.yellow.github.base;

import java.util.List;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showList(List list);

    void showMoreAdd(List moreData);

    void showLoadMoreError();

    void showEmpty();

    void showError();

    void showLoading();

    void hideLoading();



}