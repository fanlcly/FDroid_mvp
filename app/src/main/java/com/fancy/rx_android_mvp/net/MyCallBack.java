package com.fancy.rx_android_mvp.net;

import android.content.Context;

import com.fancy.rxmvp.net.RxBaseCallBack;

/**
 * 业务层的网络回调
 *
 * @author fanlei
 * @version 1.0 2019/5/5 0005
 * @since JDK 1.8
 */
public abstract class MyCallBack<T> extends RxBaseCallBack<BaseModle<T>> {
    /**
     * @param context 上下文
     */
    public MyCallBack(Context context) {
        super(context);
    }


    @Override
    public void onSuc(BaseModle<T> t) {
        if (t.getCode() == 0) {
            if (t.getBody() != null) {
                onSuccess(t.getBody());
            } else {
                onFail(t.getMsg(), t.getCode());
            }
        } else {
            onFail(t.getMsg(), t.getCode());
        }
    }

    protected abstract void onSuccess(T t);
}

