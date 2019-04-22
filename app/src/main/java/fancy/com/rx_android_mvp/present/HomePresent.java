package fancy.com.rx_android_mvp.present;

import android.content.Context;

import fancy.com.rx_android_mvp.fragment.HomeFragment;
import fancy.com.rx_android_mvp.model.HomeModel;
import fancy.com.rxmvp.mvp.XBasePresent;

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
