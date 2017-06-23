package app.yellow.github.base;

public interface BaseView<T> {

    void showEmpty();

    void showError();

    void showLoading();

    void hideLoading();

    void setPresenter(T presenter);
}
