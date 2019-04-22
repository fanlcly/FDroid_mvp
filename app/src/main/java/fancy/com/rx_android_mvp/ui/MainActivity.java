package fancy.com.rx_android_mvp.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fancy.com.rx_android_mvp.R;
import fancy.com.rx_android_mvp.adapter.HomeFragmentAdapter;
import fancy.com.rx_android_mvp.fragment.HomeFragment;
import fancy.com.rx_android_mvp.fragment.SecondFragment;
import fancy.com.rx_android_mvp.fragment.ThirdFragment;
import fancy.com.rxmvp.mvp.XBaseActivity;

public class MainActivity extends XBaseActivity {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    List<Fragment> fragmentList = new ArrayList<>();
    String[] titles = {"首页", "第二个页面", "第三个页面"};
    private HomeFragmentAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(SecondFragment.newInstance());
        fragmentList.add(ThirdFragment.newInstance());
        if (adapter == null) {
            adapter = new HomeFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public Object newP() {
        return null;
    }
}
