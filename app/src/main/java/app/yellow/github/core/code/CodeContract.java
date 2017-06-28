package app.yellow.github.core.code;

import java.util.List;

import app.yellow.github.base.BasePresenter;
import app.yellow.github.base.BaseView;
import app.yellow.github.bean.repositorydetail.ContentBean;

public class CodeContract {

    interface View extends BaseView<Presenter> {

        void showContentList(List list);

        void showContent(ContentBean contentBean);

    }

    interface Presenter extends BasePresenter {

        void loadContentListByUrl(String url);

        void loadContentByUrl(String url);

    }


}
