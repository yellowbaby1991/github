package app.yellow.github.base;


import rx.Observer;

public abstract class BaseDetailObserver<T> implements Observer<T> {

    private BaseView mView;

    public BaseDetailObserver(BaseView view) {
        mView = view;
    }

    @Override
    public void onCompleted() {
        mView.hideLoading();
    }

    @Override
    public void onError(Throwable e) {
        mView.hideLoading();
        mView.showError();
        e.printStackTrace();
    }

    @Override
    public void onNext(T bean) {
        if (bean == null) {
            mView.showEmpty();
        } else {
            showDetail(bean);
        }
    }

    protected abstract void showDetail(T bean);


}
