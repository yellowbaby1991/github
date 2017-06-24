package app.yellow.github.http;

import android.text.TextUtils;
import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GithubAuthRetrofit extends BaseRetrofit {

    private String username;
    private String password;

    public void setAuthInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public OkHttpClient getHttpClient() {
        return new AuthHttpClient(username, password).get();
    }

    private class AuthHttpClient extends BaseOkHttpClient {

        private String username;
        private String password;

        public AuthHttpClient(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public OkHttpClient.Builder customize(OkHttpClient.Builder builder) {

            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                builder.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        // https://developer.github.com/v3/auth/#basic-authentication
                        // https://developer.github.com/v3/oauth/#non-web-application-flow
                        String userCredentials = username + ":" + password;

                        String basicAuth =
                                "Basic " + new String(Base64.encode(userCredentials.getBytes(), Base64.DEFAULT));

                        Request original = chain.request();

                        Request.Builder requestBuilder = original.newBuilder()
                                .header("Authorization", basicAuth.trim());

                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                });
            }

            return builder;
        }
    }
}