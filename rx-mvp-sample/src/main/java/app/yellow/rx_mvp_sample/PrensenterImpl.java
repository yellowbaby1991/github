package app.yellow.rx_mvp_sample;

import java.util.List;

import app.yellow.rx_mvp_sample.data.Repository;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PrensenterImpl implements Contract.Presenter {

    private Contract.View mView;
    private Repository mRepository;

    public PrensenterImpl(Contract.View view, Repository repository) {
        mView = view;
        mRepository = repository;
        mView.setPresenter(this);
    }

    @Override
    public void loadList() {

        //UI 线程
        mView.showLoading();

        mRepository.loadList()
                .subscribeOn(Schedulers.io()) //指定上游在IO线程执行
                .observeOn(AndroidSchedulers.mainThread()) //指定下游在UI线程
                .subscribe(new Observer<List>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideLoading();
                        mView.showError();
                    }

                    @Override
                    public void onNext(List list) {
                        mView.hideLoading();
                        if (list.isEmpty()) {
                            mView.showEmpty();
                        } else {
                            mView.showList(list);
                        }
                    }
                });


    }
}
