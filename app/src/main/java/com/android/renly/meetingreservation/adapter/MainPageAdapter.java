package com.android.renly.meetingreservation.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.renly.meetingreservation.module.base.BaseFragment;

import java.util.List;

/**
 * 主页切换Adapter
 */

public class MainPageAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> fragments;
    private static final String[] mTitles = {"部门排序", "拼音排序"};

    public MainPageAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}