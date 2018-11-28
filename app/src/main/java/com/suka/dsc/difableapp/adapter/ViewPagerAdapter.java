package com.suka.dsc.difableapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.suka.dsc.difableapp.fragment.AllBooks;
import com.suka.dsc.difableapp.fragment.MyBook;
import com.suka.dsc.difableapp.fragment.Request;

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
                fragment = new MyBook();
                break;
            case 2 :
                fragment = new AllBooks();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
