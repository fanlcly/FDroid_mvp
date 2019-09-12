package com.fancy.rxmvp.mvp;

import android.os.Bundle;
import android.view.View;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2019/4/16 0016
 * @since JDK 1.8
 */
public interface IView<P> {
    void bindUI(View rootView);

    void bindEvent();

    int getLayoutId();

    void initData(Bundle savedInstanceState);

    int getOptionsMenuId();


    boolean useEventBus();

    P newP();
}
