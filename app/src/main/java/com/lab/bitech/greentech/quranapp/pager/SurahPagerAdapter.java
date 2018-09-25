package com.lab.bitech.greentech.quranapp.pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lab.bitech.greentech.quranapp.fragments.SurahdetailFragment;
import com.lab.bitech.greentech.quranapp.utils.Commons;

public class SurahPagerAdapter extends FragmentStatePagerAdapter {

    public SurahPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putInt(Commons.ITEM_POSITION, position + 1);
        Fragment fragment = new SurahdetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 36;
    }
}