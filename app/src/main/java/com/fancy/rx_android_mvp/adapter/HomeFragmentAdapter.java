package com.fancy.rx_android_mvp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2019/4/18 0018
 * @since JDK 1.8
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {
    private final String[] titles;
    private List<Fragment> fragmentList = new ArrayList<>();;

    public HomeFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] titles) {
        super(fm);
        this.fragmentList.addAll(fragmentList);
        this.titles = titles;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.length > position) {
            return titles[position];
        }
        return "";

    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
