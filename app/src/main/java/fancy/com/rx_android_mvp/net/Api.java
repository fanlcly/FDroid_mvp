package fancy.com.rx_android_mvp.net;

import fancy.com.rx_android_mvp.UrlConfig;
import fancy.com.rxmvp.net.HttpClient;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2019/4/19 0019
 * @since JDK 1.8
 */
public class Api {

    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    apiService = HttpClient.getInstance().getRetrofit(UrlConfig.BASE_URL, true).create(ApiService.class);
                }
            }
        }
        return apiService;
    }
}
