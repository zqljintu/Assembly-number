package com.zql.app_ji.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


import java.util.List;

public class MovieFragmentsAdapter extends FragmentPagerAdapter {
    private List<Fragment>fragments;
    private String[] titles;

    public MovieFragmentsAdapter(FragmentManager fragmentManager,List<Fragment>mfragments,String[] mtitles){
        super(fragmentManager);
        this.fragments=mfragments;
        this.titles=mtitles;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
