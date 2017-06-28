package app.yellow.github.core.code;

import java.util.List;

import app.yellow.github.base.BasePresenter;
import app.yellow.github.base.BaseView;

public class CodeContract {

    interface View extends BaseView<Presenter> {

        void showContentList(List list);

    }

    interface Presenter extends BasePresenter {

        void loadContentListByUrl(String url);

    }


}
