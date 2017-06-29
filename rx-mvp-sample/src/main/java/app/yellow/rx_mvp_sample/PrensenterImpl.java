package app.yellow.rx_mvp_sample;

import java.util.List;

import app.yellow.rx_mvp_sample.data.Repository;

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
        mView.showError();

        try{
            //IO 线程
            List list = mRepository.loadList();

            //切回UI 线程
            if (list.isEmpty()){
                mView.showEmpty();
            }else {
                mView.showList(list);
            }

        }catch (Exception e){
            mView.showError();
        }

    }
}
