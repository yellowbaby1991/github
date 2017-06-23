package app.yellow.github.util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import app.yellow.github.api.RepositoryService;
import app.yellow.github.api.UserService;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private Retrofit mRetrofit;
    private static RetrofitUtil mInstance;
    private static OkHttpClient mHttpClient;

    private RetrofitUtil() {
        //缓存路径和大小
        File httpCacheDirectory = new File(UIUtils.getContext().getCacheDir(), "github_repo");
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        //日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时
                .readTimeout(10, TimeUnit.SECONDS)//读取超时
                .writeTimeout(10, TimeUnit.SECONDS)//写入超时
                .addInterceptor(interceptor)//添加日志拦截器
                .addNetworkInterceptor(mCacheControlInterceptor)
                .cache(cache)//把缓存添加进来
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(mHttpClient)
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

    public static RepositoryService getRepositroyService() {
        return getInstance().mRetrofit.create(RepositoryService.class);
    }

    public static UserService getUserService() {
        return getInstance().mRetrofit.create(UserService.class);
    }

    private static final int TIMEOUT_CONNECT = 5; //5秒
    private static final int TIMEOUT_DISCONNECT = 60 * 60 * 24 * 7; //7天


    private final Interceptor mCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            Response originalResponse = chain.proceed(request);

            if (NetworkUtil.isConnected(UIUtils.getContext())) {

                String cacheControl = request.cacheControl().toString();

                // Add cache control header for response same as request's while network is available.
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .build();
            } else {
                // Add cache control header for response to FORCE_CACHE while network is not available.
                return originalResponse.newBuilder()
                        .header("Cache-Control", CacheControl.FORCE_CACHE.toString())
                        .build();
            }
        }
    };

}