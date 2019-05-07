package com.fancy.rxmvp.net.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 拦截器基类
 *
 * @author fanlei
 * @version 1.0 2019/4/17 0017
 * @since JDK 1.8
 */
public class XInterceptor implements Interceptor {

    RequestHeader header;

    public XInterceptor(RequestHeader header) {
        this.header = header;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (header != null) {
            request = header.onProceedRequest(request, chain);
        }
        Response response = chain.proceed(request);
        if (header != null) {
            Response tmp = header.onProceedResponse(response, chain);
            if (tmp != null) {
                return tmp;
            }

        }
        return response;
    }
}
