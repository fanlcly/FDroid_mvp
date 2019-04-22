package fancy.com.rx_android_mvp.fragment;

import android.content.Entity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.List;

import fancy.com.rx_android_mvp.R;
import fancy.com.rx_android_mvp.net.Api;
import fancy.com.rxmvp.mvp.XBaseLazyFragment;
import fancy.com.rxmvp.net.HttpClient;
import fancy.com.rxmvp.net.RxBaseCallBack;
import retrofit2.Response;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2019/4/18 0018
 * @since JDK 1.8
 */
public class SecondFragment extends XBaseLazyFragment {
    public static Fragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getHomeModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_second;
    }


    /**
     * 请求数据
     */
    public void getHomeModel() {

        HttpClient.getInstance().getObservable(Api.getApiService().totalEmployInfo())
                .compose(this.<Response<List<Entity>>>bindToLifecycle())
                .subscribe(new RxBaseCallBack<List<Entity>>(context) {
                    @Override
                    public void onSuc(Response<List<Entity>> response) {
                        Log.i("fanlcly", "onSuc: 成功了");
                    }

                    @Override
                    public void onFail(String message, int failCode) {
                        Log.i("fanlcly", "onFail: 失败了");
                    }

                });

    }


    @Override
    public Object newP() {
        return null;
    }
}
