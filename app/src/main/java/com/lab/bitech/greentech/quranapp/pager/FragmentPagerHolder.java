package com.lab.bitech.greentech.quranapp.pager;

import android.content.Context;
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
import com.lab.bitech.greentech.quranapp.PropagatePosition;
import com.lab.bitech.greentech.quranapp.R;
import com.lab.bitech.greentech.quranapp.utils.Commons;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentPagerHolder extends Fragment implements FragmentCommunicator, ViewPager.OnPageChangeListener {

    private PropagatePosition receivePosition;
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
        //suraViewPager.setOnPageChangeListener(this);
        //suraViewPager.setPageTransformer(true, new RotateUpTransformer());

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.receivePosition = (PropagatePosition) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement PropagatePosition interface");
        }
    }

    private static float sumPositionAndPositionOffset;
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("mobassira", "onPageScrolled");
        if (position + positionOffset > sumPositionAndPositionOffset) {
            receivePosition.sendPositionToNumberFragment(suraViewPager.getCurrentItem(), Commons.LEFT);
        } else {
            receivePosition.sendPositionToNumberFragment(suraViewPager.getCurrentItem(), Commons.RIGHT);
        }

        sumPositionAndPositionOffset = position + positionOffset;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
