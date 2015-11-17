package com.example.zhangweijin.coorappbar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhangweijin on 15-11-14.
 */
public class PagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;
    private List<CharSequence> titles;
    private FragmentTransaction ft;
    private FragmentManager fm;
    public PagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<CharSequence> titles) {
        super(fm);
        this.fm = fm;
        this.fragments = fragmentList;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles.get(position);
    }


}
