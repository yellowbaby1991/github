package app.yellow.github.http;

import java.io.IOException;

import app.yellow.github.util.Constants;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseRetrofit {


    GithubHttpClient mHttpClient;

    public Retrofit get() {
        Retrofit.Builder builder = new Retrofit.Builder();

        this.mHttpClient = new GithubHttpClient();

        builder.baseUrl(Constants.BASE_URL)
                .client(getHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }

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

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", createHeaderValue());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            return builder;
        }
    }

    protected abstract String createHeaderValue();

}