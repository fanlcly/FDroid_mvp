package com.fancy.rxmvp.net;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.fancy.rxmvp.net.interceptor.LoggingInterceptor;
import com.fancy.rxmvp.net.interceptor.RequestHeader;
import com.fancy.rxmvp.net.interceptor.XInterceptor;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Http客户端
 *
 * @author fanlei
 * @version 1.0 2019/4/17 0017
 * @since JDK 1.8
 */
public class HttpClient {

    private static HttpClient instance;

    public static final long connectTimeoutMills = 30;
    public static final long readTimeoutMills = 30;
    private static NetProvider sProvider = null;


    private Map<String, Retrofit> retrofitMap = new HashMap<>();

    private HttpClient() {

    }

    public static HttpClient getInstance() {
        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    instance = new HttpClient();
                }
            }
        }
        return instance;
    }


    public static <S> S get(String baseUrl, Class<S> service) {
        return getInstance().getRetrofit(baseUrl, true).create(service);
    }


    public static void registerProvider(NetProvider provider) {
        HttpClient.sProvider = provider;
    }


    public static NetProvider getCommonProvider() {
        return sProvider;
    }

    public Retrofit getRetrofit(String baseUrl, boolean useRx) {
        return getRetrofit(baseUrl, sProvider, useRx);
    }

    public Retrofit getRetrofit(String baseUrl, NetProvider provider, boolean useRx) {
        if (retrofitMap.get(baseUrl) != null) return retrofitMap.get(baseUrl);

        checkProvider(provider);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getClient(provider))
                .addConverterFactory(GsonConverterFactory.create());
        if (useRx) {
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }
        Retrofit retrofit = builder.build();
        retrofitMap.put(baseUrl, retrofit);
        return retrofit;
    }


    private OkHttpClient getClient(NetProvider provider) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(provider.configConnectTimeoutMills() != 0
                ? provider.configConnectTimeoutMills() : connectTimeoutMills, TimeUnit.SECONDS);
        builder.readTimeout(provider.configReadTimeoutMills() != 0
                ? provider.configReadTimeoutMills() : readTimeoutMills, TimeUnit.SECONDS);

        RequestHeader header = provider.configHeader();
        if (header != null) {
            builder.addNetworkInterceptor(new XInterceptor(header));
        }

        Interceptor[] interceptors = provider.configInterceptors();
        if (interceptors != null && interceptors.length > 0) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        if (provider.configLogEnable()) {
            LoggingInterceptor logInterceptor = new LoggingInterceptor();
            logInterceptor.setLevel(provider.configLogEnable()
                    ? (LoggingInterceptor.Level.BODY)
                    : (LoggingInterceptor.Level.NONE));
            builder.addInterceptor(logInterceptor);
        }

        OkHttpClient client = builder.build();

        return client;
    }

    public static void clearCache() {
        getInstance().retrofitMap.clear();
    }


    /**
     * 检查provider配置
     *
     * @param provider
     */
    private void checkProvider(NetProvider provider) {
        if (provider == null) {
            throw new IllegalStateException("must register provider first");
        }
    }

    /**
     * 设置订阅 和 所在的线程环境
     */
    public <W> Observable getObservable(Observable<W> o) {
        return o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
