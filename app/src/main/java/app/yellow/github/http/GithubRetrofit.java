package app.yellow.github.http;

import okhttp3.OkHttpClient;

public class GithubRetrofit extends BaseRetrofit {

    GithubHttpClient mHttpClient;

    public GithubRetrofit() {
        this.mHttpClient = new GithubHttpClient();
    }

    @Override
    public OkHttpClient getHttpClient() {
        return mHttpClient.get();
    }


}