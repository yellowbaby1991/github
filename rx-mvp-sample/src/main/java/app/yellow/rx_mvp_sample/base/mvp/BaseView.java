package app.yellow.rx_mvp_sample.base.mvp;

public interface BaseView<T> {

    void showEmpty();

    void showError();

    void showLoading();

    void hideLoading();

    void setPresenter(T presenter);
}
