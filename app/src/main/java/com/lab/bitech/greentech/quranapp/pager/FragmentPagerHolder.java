package com.lab.bitech.greentech.quranapp.pager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lab.bitech.greentech.quranapp.FragmentCommunicator;
import com.lab.bitech.greentech.quranapp.R;
import com.lab.bitech.greentech.quranapp.utils.Commons;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentPagerHolder extends Fragment implements FragmentCommunicator {

    @BindView(R.id.suraViewPager)
    ViewPager suraViewPager;
    private int positionNumber;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            positionNumber = getArguments().getInt(Commons.ITEM_POSITION, 6);
            Log.d("Position", "Position Number " + positionNumber);
        }

        suraViewPager.setAdapter(new SurahPagerAdapter(getActivity().getSupportFragmentManager()));
        suraViewPager.setCurrentItem(positionNumber);
        //duaViewPager.setPageTransformer(true, new RotateUpTransformer());

        return view;
    }

    @Override
    public void updateSuraListPostion(int position, String direction) {
        //not applicable here
    }

    @Override
    public void updatePagerPostion(int position) {
        suraViewPager.setCurrentItem(position);
    }
}
