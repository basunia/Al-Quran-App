package com.lab.bitech.greentech.quranapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lab.bitech.greentech.quranapp.PropagatePosition;
import com.lab.bitech.greentech.quranapp.R;
import com.lab.bitech.greentech.quranapp.models.SurahDetailModel;
import com.lab.bitech.greentech.quranapp.models.SurahNameModel;

import java.util.List;

public class SurahDetailAdapter extends RecyclerView.Adapter<SurahDetailAdapter.MyViewHolder> {

    private Context context;
    private List<SurahDetailModel> surahDetailList;
    private PropagatePosition mPropagatePosition;
    private int singlePagerPosition = 0;

    public SurahDetailAdapter(Context context, List<SurahDetailModel> list, PropagatePosition mPropagatePosition, int singlePagerPosition) {
        this.context = context;
        this.surahDetailList = list;
        this.mPropagatePosition = mPropagatePosition;
        this.singlePagerPosition = singlePagerPosition;

        Log.d("shira", "SurahDetailAdapter");
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvAyat;
        TextView tvTranslation;

        public MyViewHolder(View view) {
            super(view);
            tvAyat = view.findViewById(R.id.tvAyat);
            tvTranslation = view.findViewById(R.id.tvTranslation);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.surah_detail_single_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        SurahDetailModel surahDetail = surahDetailList.get(position);

        holder.tvAyat.setText(surahDetail.getAyaatName());
        holder.tvTranslation.setText(surahDetail.getTranslation());

        Typeface arabic_font = Typeface.createFromAsset(context.getAssets(), "fonts/trado.ttf");
        holder.tvAyat.setTypeface(arabic_font);

        /*holder.tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //propagate position to PagerHolder fragment thru activity
                mPropagatePosition.sendPositionToPagerHolder((position));

                //holder.tvItem.setBackgroundColor(Color.BLUE);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return surahDetailList.size();
    }

}

