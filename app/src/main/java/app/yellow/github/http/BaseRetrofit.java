package app.yellow.github.http;

import app.yellow.github.util.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseRetrofit {

    public Retrofit get() {
        Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl(Constants.BASE_URL)
                .client(getHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }

    public abstract OkHttpClient getHttpClient();

}