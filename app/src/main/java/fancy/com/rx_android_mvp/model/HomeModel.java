package fancy.com.rx_android_mvp.model;

import android.content.Context;
import android.content.Entity;
import android.util.Log;

import java.util.List;

import fancy.com.rx_android_mvp.fragment.HomeFragment;
import fancy.com.rx_android_mvp.net.Api;
import fancy.com.rxmvp.net.HttpClient;
import fancy.com.rxmvp.net.RxBaseCallBack;
import retrofit2.Response;

/**
 * homefragment的Model类
 *
 * @author fanlei
 * @version 1.0 2019/4/19 0019
 * @since JDK 1.8
 */
public class HomeModel {
    private final Context context;
    HomeFragment view;

    public HomeModel(HomeFragment view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void getHomeModel() {

        HttpClient.getInstance().getObservable(Api.getApiService().totalEmployInfo())
                .compose(view.<Response<List<Entity>>>bindToLifecycle())
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
