package com.fancy.rx_android_mvp.present;

import android.content.Context;

import com.fancy.rx_android_mvp.fragment.HomeFragment;
import com.fancy.rx_android_mvp.model.HomeModel;
import com.fancy.rxmvp.mvp.XBasePresent;

/**
 * HomePresent
 *
 * @author fanlei
 * @version 1.0 2019/4/19 0019
 * @since JDK 1.8
 */
public class HomePresent extends XBasePresent<HomeFragment> {
        public void LoadData(Context context) {
            new HomeModel(getV(),context).getHomeModel();
        }
}
