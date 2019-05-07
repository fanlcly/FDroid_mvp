package com.fancy.rxmvp.net;

import android.content.Context;
import android.util.Log;

import io.reactivex.observers.DisposableObserver;
import retrofit2.Response;

/**
 * rx的回调
 *
 * @author fanlei
 * @version 1.0 2019/4/22 0022
 * @since JDK 1.8
 */
public abstract class RxBaseCallBack<T> extends DisposableObserver<Response<T>> implements LoadCancelListener {
    protected final int DEFAULT_FAIL_CODE = -1;
    protected final Context mContext;

    /**
     * @param context 上下文
     */
    public RxBaseCallBack(Context context) {
        this.mContext = context;
    }

    @Override
    protected void onStart() {
        Log.i("RxBaseCallBack", "onStart:");
    }

    @Override
    public void onNext(Response<T> response) {
        if (response.raw().code() == 200) {
            onSuc(response.body());
        } else {//失败响应
            onFail(NetError.buildError(response).getErrorMessage(), response.raw().code());
        }

    }

    @Override
    public void onError(Throwable t) {
        try {
            onFail(NetError.buildError(t).getErrorMessage(), DEFAULT_FAIL_CODE);
        } finally {
            onLoadCance();
        }

    }

    @Override
    public void onComplete() {
        onLoadCance();
    }

    @Override
    public void onLoadCance() {
        if (!this.isDisposed()) {
            this.dispose();
        }
    }

    public abstract void onSuc(T response);

    public abstract void onFail(String message, int failCode);

}
