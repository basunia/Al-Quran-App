package com.lab.bitech.greentech.quranapp.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.lab.bitech.greentech.quranapp.MainActivity;


public class BaseFragment extends Fragment {

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void setTitle(String title) {
       if (context instanceof MainActivity) {
           MainActivity mainActivity = (MainActivity) context;
           mainActivity.setAppTitle(title);
       }
    }
}
