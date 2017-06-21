package app.yellow.github.util;

import app.yellow.github.api.home.HomeService;
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
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static RetrofitUtil getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtil();
                }
            }
        }
        return mInstance;
    }

    public static HomeService getHomeService() {
        return getInstance().mRetrofit.create(HomeService.class);
    }

}