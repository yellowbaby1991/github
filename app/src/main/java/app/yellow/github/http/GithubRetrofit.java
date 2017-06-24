package app.yellow.github.http;

import java.io.IOException;

import app.yellow.github.util.Constants;
import app.yellow.github.util.SPUtils;
import app.yellow.github.util.UIUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GithubRetrofit extends BaseRetrofit {

    protected static GithubRetrofit INSTANCE = null;

    GithubHttpClient mHttpClient;

    private GithubRetrofit() {
        this.mHttpClient = new GithubHttpClient();
    }

    public static GithubRetrofit getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GithubRetrofit();
        }
        return INSTANCE;
    }

    @Override
    public OkHttpClient getHttpClient() {
        return mHttpClient.get();
    }

    class GithubHttpClient extends CacheHttpClient {

        @Override
        public OkHttpClient.Builder customize(OkHttpClient.Builder builder) {
            builder = super.customize(builder);

            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request original = chain.request();

                    String token = SPUtils.getString(UIUtils.getContext(), Constants.SP_TOKEN, "");

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", "token " + token);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            return builder;
        }
    }
}