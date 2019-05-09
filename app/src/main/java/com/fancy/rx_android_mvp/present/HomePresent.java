package com.fancy.rx_android_mvp.present;

import android.content.Context;
import android.content.Entity;
import android.util.Log;

import com.fancy.rx_android_mvp.fragment.JokeFragment;
import com.fancy.rx_android_mvp.net.Api;
import com.fancy.rxmvp.mvp.XBasePresent;
import com.fancy.rxmvp.net.HttpClient;
import com.fancy.rxmvp.net.RxBaseCallBack;

import java.util.List;

import retrofit2.Response;

/**
 * HomePresent
 *
 * @author fanlei
 * @version 1.0 2019/4/19 0019
 * @since JDK 1.8
 */
public class HomePresent extends XBasePresent<JokeFragment> {
        public void LoadData(Context context) {
            HttpClient.getInstance().getObservable(Api.getApiService().totalEmployInfo())
                    .compose(getV().<Response<List<Entity>>>bindToLifecycle())
                    .subscribe(new RxBaseCallBack<List<Entity>>(context) {
                        @Override
                        public void onSuc(List<Entity> response) {
                            Log.i("fanlcly", "onSuc: 成功了");
                        }



                        @Override
                        public void onFail(String message, int failCode) {
                            Log.i("fanlcly", "onFail: 失败了");
                        }

                    });


        }
}
