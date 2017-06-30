package app.yellow.rx_mvp_sample.data;

import rx.Observable;

public interface DataSource {
    Observable loadList();
}
