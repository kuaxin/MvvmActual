package com.youjiaoyule.mvvmactual.net;

import android.text.TextUtils;
import androidx.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.youjiaoyule.mvvmactual.BuildConfig;
import com.youjiaoyule.mvvmactual.common.Constant;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpManager {

    private final static class OkHttpHolder{
        static OkHttpManager okHttpManager = new OkHttpManager();
    }

    public static OkHttpManager getInstance(){
        return OkHttpHolder.okHttpManager;
    }


    OkHttpClient providerOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new HttpHeadersInterceptor())
                .addInterceptor(new AuthInterceptor())
//                .addNetworkInterceptor(new RetryInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 具有自动缓存OkHttpClient
     *
     * @return OkHttpManager
     */
    public OkHttpClient providerAutoCacheOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new HttpHeadersInterceptor())
                .addInterceptor(new AuthInterceptor())
                .addNetworkInterceptor(new CacheInterceptor())
//                .addNetworkInterceptor(new RetryInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 网络拦截器：用于重试
     */
    private static class RetryInterceptor implements Interceptor {

        @NotNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            int tryCount = 0;
            //这里设置重试为2次
            while (!response.isSuccessful() && tryCount < 2) {
                tryCount++;
                response = chain.proceed(request);
            }
            return response;
        }
    }

    /**
     * 统一设置网络请求头
     */
    private class HttpHeadersInterceptor implements Interceptor {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            //  请求体定制：统一添加Token认证信息,一般在登录后时使用
            final String token = Constant.INSTANCE.getTOKEN();

            Request request;
            if (TextUtils.isEmpty(token)) {
                request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                        .addHeader("Accept", "application/json;charset=UTF-8")
                        .addHeader("charset", "UTF-8")
                        .addHeader("timestamp",System.currentTimeMillis() + "")
                        .build();
            } else {
                request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                        .addHeader("Accept", "application/json;charset=UTF-8")
                        .addHeader("payuserauth", token)
                        .addHeader("charset", "UTF-8")
                        .addHeader("timestamp",System.currentTimeMillis() + "")
                        .build();
            }

            return chain.proceed(request);
        }
    }

    /**
     * 添加缓存，服务器不支持缓存时，支持缓存
     */
    private class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            // 有网络时 设置缓存超时时间2分钟
            int maxAge = 2 * 60;
            // 无网络时，设置超时为1小时
            int maxStale = 60 * 60;

            Request request = chain.request();
            Response response = chain.proceed(request);
            String cacheControl = request.cacheControl().toString();
            if (TextUtils.isEmpty(cacheControl)) {
                cacheControl = "public, max-age=" + maxAge + " ,max-stale=" + maxStale;
            }
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", cacheControl)
                    .build();
        }
    }

    /**
     * 添加认证信息(token,目前为登录后返回,永久使用)
     */
    private static class AuthInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request original = chain.request();
            //  请求体定制：统一添加Token认证信息,一般在登录后时使用
            final String token = Constant.INSTANCE.getTOKEN();

            if (!TextUtils.isEmpty(token)) {
                Request request = original.newBuilder()
                        .url(original.url())
                        .addHeader("payuserauth", token)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
            return chain.proceed(original);
        }
    }


}
