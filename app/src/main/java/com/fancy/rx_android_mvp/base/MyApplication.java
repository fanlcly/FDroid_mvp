package com.fancy.rx_android_mvp.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.fancy.rx_android_mvp.net.HeaderInterceptor;

import fancy.com.rxmvp.BuildConfig;

import com.fancy.rx_android_mvp.service.X5NetService;
import com.fancy.rxmvp.net.HttpClient;
import com.fancy.rxmvp.net.NetProvider;
import com.fancy.rxmvp.net.interceptor.RequestHeader;

import okhttp3.Interceptor;

/**
 * myapplication
 *
 * @author fanlei
 * @version 1.0 2019/4/19 0019
 * @since JDK 1.8
 */
public class MyApplication extends Application {

    public  static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        // 初始化X5WebView
        preInitX5Core();
        HttpClient.registerProvider(new NetProvider() {
            @Override
            public long configConnectTimeoutMills() {
                return 15;
            }

            @Override
            public long configReadTimeoutMills() {
                return 15;
            }

            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[0];
            }

            @Override
            public boolean configLogEnable() {
                return BuildConfig.DEBUG ? true : false;
            }

            @Override
            public RequestHeader configHeader() {
                return new HeaderInterceptor();
            }
        });
    }



    private void preInitX5Core() {
        //预加载x5内核
        Intent intent = new Intent(this, X5NetService.class);
        startService(intent);
    }


}
