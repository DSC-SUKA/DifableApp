package com.suka.dsc.difableapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.suka.dsc.difableapp.ui.dashboard.allbooks.AllBooksFragment;
import com.suka.dsc.difableapp.ui.dashboard.mybook.MyBookFragment;
import com.suka.dsc.difableapp.ui.dashboard.request.Request;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0 :
                fragment = new Request();
                break;
            case 1 :
                fragment = new MyBookFragment();
                break;
            case 2 :
                fragment = new AllBooksFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
