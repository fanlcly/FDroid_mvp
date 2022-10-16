package com.fancy.rx_android_mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fancy.com.rx_android_mvp.R;
import com.fancy.rx_android_mvp.adapter.HomeFragmentAdapter;
import com.fancy.rx_android_mvp.fragment.JokeFragment;
import com.fancy.rx_android_mvp.fragment.NewsFragment;
import com.fancy.rx_android_mvp.fragment.GirlFragment;
import com.fancy.rxmvp.mvp.XBaseActivity;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends XBaseActivity {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_home)
    TextView tvHome;

    List<Fragment> fragmentList = new ArrayList<>();
    String[] titles = {"笑话", "新闻", "美女"};
    private HomeFragmentAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public ViewGroup getLayoutView() {
        return null;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        fragmentList.add(JokeFragment.newInstance());
        fragmentList.add(NewsFragment.newInstance());
        fragmentList.add(GirlFragment.newInstance());
        if (adapter == null) {
            adapter = new HomeFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);

        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity,RecyclerActivity.class));
//                startActivity(new Intent(mActivity,TestActivity.class));
            }
        });
    }


    @Override
    public Object newP() {
        return null;
    }
}
