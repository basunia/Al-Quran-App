package com.lab.bitech.greentech.quranapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.GridLayout.HORIZONTAL;

public class SuraListFragment extends Fragment implements FragmentCommunicator {

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

        surahNameList = new ArrayList<>();

        adapter = new SurahListAdapter(getActivity(), surahNameList, mPropagatePosition, 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        DataBank dataBank = new DataBank(getActivity());
        surahNameList.addAll(dataBank.getSurahList());
        adapter.notifyDataSetChanged();

        DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity(), HORIZONTAL);
        recyclerView.addItemDecoration(itemDecor);

        //Toast.makeText(getActivity(), "AYAt number" + surahNameList.size(), Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPropagatePosition = (PropagatePosition) context;
    }

    @Override
    public void updateSuraListPostion(int position, String direction) {

    }

    @Override
    public void updatePagerPostion(int position) {

    }
}
