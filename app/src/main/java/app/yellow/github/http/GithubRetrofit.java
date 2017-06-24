package app.yellow.github.http;

import app.yellow.github.util.Constants;
import app.yellow.github.util.SPUtils;
import app.yellow.github.util.UIUtils;

public class GithubRetrofit extends BaseRetrofit {

    private GithubRetrofit() {
        this.mHttpClient = new GithubHttpClient();
    }

    protected static GithubRetrofit INSTANCE = null;

    public static GithubRetrofit getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GithubRetrofit();
        }
        return INSTANCE;
    }


    @Override
    protected String createHeaderValue() {
        String token = SPUtils.getString(UIUtils.getContext(), Constants.SP_TOKEN, "");
        return "token " + token;
    }
}