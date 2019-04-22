package fancy.com.rx_android_mvp;

import fancy.com.rxmvp.BuildConfig;

/**
 * 请求地址常量
 *
 * @author fanlei
 * @version 1.0 2019/4/19 0019
 * @since JDK 1.8
 */
public class UrlConfig {

    private static String TEST_URL = "http://api.laifudao.com/";


    //===============================================================================
    // 请勿动以下正式环境下的url

    private static String PRO_URL = "http://api.laifudao.com/";
    public static String BASE_URL;

    static {
        if (!BuildConfig.DEBUG) {
            // release
            BASE_URL = PRO_URL;
        } else {
            // debug
            BASE_URL = TEST_URL;
        }
    }
}
