package com.fancy.rxmvp.net.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求头的接口
 *
 * @author fanlei
 * @version 1.0 2019/4/17 0017
 * @since JDK 1.8
 */
public interface RequestHeader {
    Request onProceedRequest(Request request, Interceptor.Chain chain) throws IOException;

    Response onProceedResponse(Response response, Interceptor.Chain chain) throws IOException;
}
