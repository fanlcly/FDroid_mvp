package com.fancy.rx_android_mvp;


import com.bigkoo.pickerview.BuildConfig;

/**
 * 请求地址常量
 *
 * @author fanlei
 * @version 1.0 2019/4/19 0019
 * @since JDK 1.8
 */
public class UrlConfig {
    private static String TEST_URL = "http://api.laifudao.com/";
    private static String TEST_M_URL = "http://api.laifudao.com/";
    private static String TEST_A_URL = "http://api.laifudao.com/";
    private static String TEST_V_URL = "http://api.laifudao.com/";


    //===============================================================================
    // 请勿动以下正式环境下的url

    private static String PRO_URL = "http://api.laifudao.com/";
    private static String PRO_M_URL = "http://api.laifudao.com/";
    private static String PRO_A_URL = "http://api.laifudao.com/";
    private static String PRO_V_URL = "http://api.laifudao.com/";

    public static final String BASE_URL;
    public static final String M_URL;
    public static final String A_URL;
    public static final String V_URL;

    static {
        if (!BuildConfig.DEBUG) {
            // release
            BASE_URL = PRO_URL;
            M_URL = PRO_M_URL;
            A_URL = PRO_A_URL;
            V_URL = PRO_V_URL;
        } else {
            // debug
            BASE_URL = TEST_URL;
            M_URL = TEST_M_URL;
            A_URL = TEST_A_URL;
            V_URL = TEST_V_URL;
        }
    }
}
