package app.yellow.github.base;


import java.util.List;

import rx.Observer;

public class BaseListObserver<T> implements Observer<List<T>> {

    private BaseListView mView;

    public BaseListObserver(BaseListView view) {
        mView = view;
    }

    @Override
    public void onCompleted() {
        mView.hideLoading();
    }

    @Override
    public void onError(Throwable e) {
        if (isLoadMore()) {
            mView.showLoadMoreError();
        } else {
            mView.hideLoading();
            mView.showError();
        }
        e.printStackTrace();
    }

    @Override
    public void onNext(List<T> list) {
        if (list.isEmpty()) {
            if (isLoadMore()){
                mView.showLoadMoreEnd();
            }else {
                mView.showEmpty();
            }
        } else {
            if (isLoadMore()) {
                mView.showMoreAdd(list);
            } else {
                mView.showList(list);
            }
        }
    }

    protected boolean isLoadMore() {
        return false;
    }

}
