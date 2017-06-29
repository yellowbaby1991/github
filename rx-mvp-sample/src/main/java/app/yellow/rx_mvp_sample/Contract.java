package app.yellow.rx_mvp_sample;


import java.util.List;

public interface Contract {

    interface View {

        void showLoading();

        void hideLoading();

        void showError();

        void showEmpty();

        void showList(List list);

        void setPresenter(Contract.Presenter presenter);
    }

    interface Presenter {

        void loadList();

    }
}
