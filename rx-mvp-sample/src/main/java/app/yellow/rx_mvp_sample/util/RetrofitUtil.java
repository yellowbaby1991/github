package app.yellow.rx_mvp_sample.util;

import app.yellow.rx_mvp_sample.api.UserService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private Retrofit mRetrofit;
    private static RetrofitUtil mInstance;

    private RetrofitUtil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static RetrofitUtil getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                mInstance = new RetrofitUtil();
            }
        }
        return mInstance;
    }

    public static UserService getUserService() {
        return getInstance().mRetrofit.create(UserService.class);
    }
}