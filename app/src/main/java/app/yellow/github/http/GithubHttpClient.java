package app.yellow.github.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GithubHttpClient extends CacheHttpClient {

    @Override
    public OkHttpClient.Builder customize(OkHttpClient.Builder builder) {
        builder = super.customize(builder);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "token 9b450d9d101574cbe378af2d10ea83f063daa3a2");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return builder;
    }
}