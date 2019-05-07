package com.fancy.rxmvp.net;

import com.fancy.rxmvp.net.interceptor.RequestHeader;
import okhttp3.Interceptor;

/**
 * 网络配置提供者
 *
 * @author fanlei
 * @version 1.0 2019/4/17 0017
 * @since JDK 1.8
 */
public interface NetProvider {
    long configConnectTimeoutMills();

    long configReadTimeoutMills();

    Interceptor[] configInterceptors();

    boolean configLogEnable();

    RequestHeader configHeader();
}
