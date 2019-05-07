package com.fancy.rx_android_mvp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import fancy.com.rx_android_mvp.R;
import com.fancy.rxmvp.mvp.XBaseLazyFragment;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2019/4/18 0018
 * @since JDK 1.8
 */
public class ThirdFragment extends XBaseLazyFragment {
    public static Fragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_thrid;
    }

    @Override
    public Object newP() {
        return null;
    }
}
