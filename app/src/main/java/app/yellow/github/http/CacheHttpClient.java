package app.yellow.github.http;

import java.io.File;
import java.io.IOException;

import app.yellow.github.util.NetworkUtil;
import app.yellow.github.util.UIUtils;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class CacheHttpClient extends BaseOkHttpClient {

    private static final long CACHE_SIZE = 1024 * 1024 * 50;

    @Override
    public OkHttpClient.Builder customize(OkHttpClient.Builder builder) {
        // set cache dir
        File cacheFile = new File(UIUtils.getContext().getCacheDir(), "github_repo");
        Cache cache = new Cache(cacheFile, CACHE_SIZE);
        builder.cache(cache);
        builder.addInterceptor(new HttpLoggingInterceptor());
        builder.addNetworkInterceptor(mCacheControlInterceptor);
        return builder;
    }

    private final Interceptor mCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            Response originalResponse = chain.proceed(request);

            if (NetworkUtil.isConnected(UIUtils.getContext())) {

                String cacheControl = request.cacheControl().toString();

                if (cacheControl.equals("")){
                    cacheControl = CacheControl.FORCE_NETWORK.toString();
                }

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