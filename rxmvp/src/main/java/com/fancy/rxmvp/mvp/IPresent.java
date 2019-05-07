package com.fancy.rxmvp.mvp;

/**
 * Present的接口
 *
 * @author fanlei
 * @version 1.0 2019/4/16 0016
 * @since JDK 1.8
 */
public interface IPresent<V> {
    void attachV(V view);

    void detachV();

    boolean hasV();
}
