package com.knight.myapplication.mFragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by knight on 6/18/2017.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> title=new ArrayList<>();


    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    //Adding the pages

    public void addFragment(Fragment f,String ftitle) {

        fragments.add(f);
        title.add(ftitle);
    }

    //Set the title


    @Override
    public CharSequence getPageTitle(int position) {


        return title.get(position);
    }
}
