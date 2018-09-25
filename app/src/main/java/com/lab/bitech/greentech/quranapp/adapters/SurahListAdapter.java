package com.lab.bitech.greentech.quranapp.adapters;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.lab.bitech.greentech.quranapp.MainActivity;
import com.lab.bitech.greentech.quranapp.PropagatePosition;
import com.lab.bitech.greentech.quranapp.R;
import com.lab.bitech.greentech.quranapp.fragments.SurahdetailFragment;
import com.lab.bitech.greentech.quranapp.models.SurahNameModel;
import com.lab.bitech.greentech.quranapp.pager.FragmentPagerHolder;

import java.util.List;

import static android.content.Context.WINDOW_SERVICE;

public class SurahListAdapter extends RecyclerView.Adapter<SurahListAdapter.MyViewHolder> {

    private MainActivity mainActivity;
    private List<SurahNameModel> surahNameList;
    //private PropagatePosition mPropagatePosition;
    //private int singlePagerPosition = 0;

    public SurahListAdapter(Context context, List<SurahNameModel> list, PropagatePosition mPropagatePosition, int singlePagerPosition) {
        this.mainActivity = (MainActivity) context;
        this.surahNameList = list;
        //this.mPropagatePosition = mPropagatePosition;
        //this.singlePagerPosition = singlePagerPosition;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvSurahName;

        public MyViewHolder(View view) {
            super(view);
            tvSurahName = (TextView) view.findViewById(R.id.tvSurah);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.surah_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        SurahNameModel surahName = surahNameList.get(position);
        holder.tvSurahName.setText(surahName.getSurahName());

        holder.tvSurahName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surahDetailFragment();
            }
        });

    }

    @Override
    public int getItemCount() {
        return surahNameList.size();
    }

    private void surahDetailFragment() {
        Display display = ((WindowManager) mainActivity.getSystemService(WINDOW_SERVICE))
                .getDefaultDisplay();

        int orientation = display.getRotation();

        if (orientation == Surface.ROTATION_90
                || orientation == Surface.ROTATION_270) {
            // TODO: add logic for landscape mode here
            mainActivity.getSupportFragmentManager().beginTransaction().add(R.id.fragmentHolderDetail, new FragmentPagerHolder()).addToBackStack(null).commit();
        } else {
            mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHolder, new FragmentPagerHolder()).addToBackStack(null).commit();
        }
    }

}

