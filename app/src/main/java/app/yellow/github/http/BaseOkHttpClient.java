package app.yellow.github.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public abstract class BaseOkHttpClient {

    private static final long TIMEOUT_CONNECT = 30 * 1000;

    public OkHttpClient get() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpLoggingInterceptor());

        builder = customize(builder);

        return builder.build();
    }

    public abstract OkHttpClient.Builder customize(OkHttpClient.Builder builder);

}