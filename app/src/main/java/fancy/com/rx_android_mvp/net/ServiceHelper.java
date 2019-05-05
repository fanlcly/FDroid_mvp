package fancy.com.rx_android_mvp.net;

import android.support.annotation.StringDef;


import java.util.HashMap;
import java.util.Map;

import fancy.com.rx_android_mvp.UrlConfig;
import fancy.com.rxmvp.net.HttpClient;


/**
 * service的帮助类
 *
 * @author fanlei
 * @version 1.0 2019/4/24 0024
 * @since JDK 1.8
 */
public class ServiceHelper {
    public final static String M = "m";
    public final static String A = "a";
    public final static String V = "v";
    public final static String BASE = "base";
    private Object s;
    Map<String, Object> serviceMap = new HashMap();

    private ServiceHelper() {
    }

    private static ServiceHelper serviceHelper;

    public static synchronized ServiceHelper init() {
        if (null == serviceHelper) {
            serviceHelper = new ServiceHelper();
        }
        return serviceHelper;
    }

    @StringDef(value = {M, A, V, BASE})
    public @interface UrlType {
    }

    @SuppressWarnings("ALL")
    public <S> S getService(@UrlType String urlType, final Class<S> service) {
        String key = new StringBuilder(urlType).append(service.getSimpleName()).toString();
        if (serviceMap.get(key) != null) {
            return (S) serviceMap.get(key);
        }

        String baseUrl;
        switch (urlType) {
            case M:
                baseUrl = UrlConfig.M_URL;
                break;
            case A:
                baseUrl = UrlConfig.A_URL;
                break;
            case V:
                baseUrl = UrlConfig.V_URL;
                break;
            default:
                baseUrl = UrlConfig.BASE_URL;
        }
        s = HttpClient.getInstance().getRetrofit(baseUrl,true).create(service);
        serviceMap.put(key, s);
        return (S) s;
    }

    public static void clearCache() {
        init().serviceMap.clear();
    }


}
