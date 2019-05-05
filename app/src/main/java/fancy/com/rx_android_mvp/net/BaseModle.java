package fancy.com.rx_android_mvp.net;

import com.google.gson.annotations.SerializedName;

/**
 * 业务层的数据封装类
 *
 * @author fanlei
 * @version 1.0 2019/5/5 0005
 * @since JDK 1.8
 */
public class BaseModle<T> {

    @SerializedName("code")
    private int code;

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private T body;


    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }


    public T getBody() {
        return body;
    }


}
