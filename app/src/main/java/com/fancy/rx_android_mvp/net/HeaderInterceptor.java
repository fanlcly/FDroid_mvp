package com.fancy.rx_android_mvp.net;

import java.io.IOException;

import com.fancy.rxmvp.net.interceptor.RequestHeader;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 关于请求头的拦截器
 *
 * @author fanlei
 * @version 1.0 2019/4/23 0023
 * @since JDK 1.8
 */
public class HeaderInterceptor implements RequestHeader {
    @Override
    public Request onProceedRequest(Request request, Interceptor.Chain chain) throws IOException {
        Request authorised = request.newBuilder()
                //.header(SIGNATURE,signature)
                .addHeader("Accept", "application/json")
                .addHeader("token", "***")// 约定好的请求头参数
                .addHeader("Authorization", "***")// 约定好的请求头参数
                .build();

        return authorised;
    }

    @Override
    public Response onProceedResponse(Response response, Interceptor.Chain chain) throws IOException {
        return null;
    }
}
