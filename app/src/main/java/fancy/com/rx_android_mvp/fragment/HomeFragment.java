package fancy.com.rx_android_mvp.fragment;

import android.os.Bundle;

import fancy.com.rx_android_mvp.R;
import fancy.com.rx_android_mvp.present.HomePresent;
import fancy.com.rxmvp.mvp.XBaseLazyFragment;

/**
 * 首页界面
 *
 * @author fanlei
 * @version 1.0 2019/4/18 0018
 * @since JDK 1.8
 */
public class HomeFragment extends XBaseLazyFragment<HomePresent> {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getP().LoadData(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomePresent newP() {
        return new HomePresent();
    }
}
