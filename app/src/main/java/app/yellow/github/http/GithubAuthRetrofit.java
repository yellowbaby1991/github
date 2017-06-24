package app.yellow.github.http;

public class GithubAuthRetrofit extends BaseRetrofit {

    protected static GithubAuthRetrofit INSTANCE = null;

    private String mBasicAuth;

    public static GithubAuthRetrofit getInstance(String basicAuth) {
        if (INSTANCE == null) {
            INSTANCE = new GithubAuthRetrofit(basicAuth);
        }
        return INSTANCE;
    }

    private GithubAuthRetrofit(String basicAuth) {
        mBasicAuth = basicAuth;
    }

    @Override
    protected String createHeaderValue() {
        return mBasicAuth.trim();
    }

}