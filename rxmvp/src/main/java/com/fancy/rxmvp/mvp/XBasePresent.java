package com.fancy.rxmvp.mvp;

import java.lang.ref.WeakReference;

/**
 * Present的基类
 *
 * @author fanlei
 * @version 1.0 2019/4/16 0016
 * @since JDK 1.8
 */
public class XBasePresent<V extends IView> implements IPresent<V> {
    private WeakReference<V> v;

    @Override
    public void attachV(V view) {
        v = new WeakReference<>(view);
    }

    @Override
    public void detachV() {
        if (v.get() != null) {
            v.clear();
        }
        v = null;
    }

    protected V getV() {
        if (v == null || v.get() == null) {
            throw new IllegalStateException("v can not be null");
        }
        return v.get();
    }


    @Override
    public boolean hasV() {
        return v != null && v.get() != null;
    }
}
