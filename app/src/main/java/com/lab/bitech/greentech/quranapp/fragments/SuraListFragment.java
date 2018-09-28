package com.lab.bitech.greentech.quranapp.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lab.bitech.greentech.quranapp.FragmentCommunicator;
import com.lab.bitech.greentech.quranapp.MainActivity;
import com.lab.bitech.greentech.quranapp.PropagatePosition;
import com.lab.bitech.greentech.quranapp.R;
import com.lab.bitech.greentech.quranapp.adapters.SurahDetailAdapter;
import com.lab.bitech.greentech.quranapp.adapters.SurahListAdapter;
import com.lab.bitech.greentech.quranapp.db.DataBank;
import com.lab.bitech.greentech.quranapp.models.SurahDetailModel;
import com.lab.bitech.greentech.quranapp.models.SurahNameModel;
import com.lab.bitech.greentech.quranapp.utils.Commons;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuraListFragment extends BaseFragment implements FragmentCommunicator {

    private PropagatePosition mPropagatePosition;
    @BindView(R.id.recyclerViewSurahList)
    RecyclerView recyclerView;
    List<SurahNameModel> surahNameList;
    SurahListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_surah_list, container, false);
        ButterKnife.bind(this, view);
        setTitle("Al Quran");

        surahNameList = new ArrayList<>();

        adapter = new SurahListAdapter(getActivity(), surahNameList, mPropagatePosition, 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        DataBank dataBank = new DataBank(getActivity());
        surahNameList.addAll(dataBank.getSurahList());
        adapter.notifyDataSetChanged();

        DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(itemDecor);

        //Toast.makeText(getActivity(), "AYAt number" + surahNameList.size(), Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mPropagatePosition = (PropagatePosition) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement PropagatePosition interface");
        }
    }

    @Override
    public void updateSuraListPostion(int position, String direction) {
        //updatePosition(position, direction);
    }

    @Override
    public void updatePagerPostion(int position) {
        /*not applicable here*/
    }

    //Should override onDetach to avoid memory leak

    private void updatePosition (int position, String direction) {
        //Toast.makeText(getActivity(), "singlePagerPosition: " + position, Toast.LENGTH_SHORT).show();
        //this.singlePagerPosition = position;
        Log.d("singlePagerPosition:", "" + position);

        if(position >= 0 && position <= 5) {

            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            if (direction.equals(Commons.RIGHT)) {

                int totalVisibleItems = layoutManager.findFirstCompletelyVisibleItemPosition() - layoutManager.findLastCompletelyVisibleItemPosition();
                int centeredItemPosition = totalVisibleItems; // / 2;
                //recyclerView.smoothScrollToPosition(position);
                //recyclerView.setScrollY(centeredItemPosition);

                try{
                    if (recyclerView.findViewHolderForAdapterPosition(position + 1).itemView != null){
                        recyclerView.findViewHolderForAdapterPosition(position + 1).itemView.setBackgroundColor(getResources().getColor(R.color.colorBackground));
                        //recyclerView.findViewHolderForAdapterPosition(position + 1).itemView.setAlpha(1f);
                    }
                    if (recyclerView.findViewHolderForAdapterPosition(position).itemView != null){
                        recyclerView.findViewHolderForAdapterPosition(position).itemView.setBackgroundColor(getResources().getColor(R.color.yellow));
                        //recyclerView.findViewHolderForAdapterPosition(position).itemView.setAlpha(.8f);
                    }

                } catch (NullPointerException e) {
                    e.printStackTrace();

                    //must be uncomment for debugging
                    //Toast.makeText(getContext(), "View not found for position " + position + " Right swipe", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                recyclerView.scrollToPosition(position + ( /*centeredItemPosition =*/ 0 ));

            } else if (direction.equals(Commons.LEFT)){

                int totalVisibleItems = layoutManager.findLastCompletelyVisibleItemPosition() - layoutManager.findFirstCompletelyVisibleItemPosition();
                int centeredItemPosition = totalVisibleItems; // / 2;
                //recyclerView.smoothScrollToPosition(position);
                //recyclerView.setScrollY(centeredItemPosition);

                try {

                    if (recyclerView.findViewHolderForAdapterPosition(position).itemView != null){
                        recyclerView.findViewHolderForAdapterPosition(position).itemView.setBackgroundColor(getResources().getColor(R.color.yellow));
                        //recyclerView.findViewHolderForAdapterPosition(position).itemView.setAlpha(0.8f);
                    }
                    if (recyclerView.findViewHolderForAdapterPosition(position - 1).itemView != null){
                        recyclerView.findViewHolderForAdapterPosition(position - 1).itemView.setBackgroundColor(getResources().getColor(R.color.colorBackground));
                        //recyclerView.findViewHolderForAdapterPosition(position - 1).itemView.setAlpha(1f);
                    }

                } catch (NullPointerException e) {
                    //Toast.makeText(getContext(), "View not found for position " + position + " Left swipe", Toast.LENGTH_SHORT).show();
                }

                //recyclerView.scrollToPosition(position + ( centeredItemPosition ));
                recyclerView.scrollToPosition(position + ( 0 ));
            }

        }

    }
}
