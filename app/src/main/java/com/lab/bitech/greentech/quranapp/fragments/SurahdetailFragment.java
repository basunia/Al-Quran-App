package com.lab.bitech.greentech.quranapp.fragments;

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

import com.lab.bitech.greentech.quranapp.R;
import com.lab.bitech.greentech.quranapp.adapters.SurahDetailAdapter;
import com.lab.bitech.greentech.quranapp.db.DataBank;
import com.lab.bitech.greentech.quranapp.models.SurahDetailModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.GridLayout.HORIZONTAL;

public class SurahdetailFragment extends Fragment {

    @BindView(R.id.recyclerViewSurahDetail)
    RecyclerView recyclerView;
    List<SurahDetailModel> surahDetailModelList;
    SurahDetailAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_surah_detail, container, false);
        ButterKnife.bind(this, view);

        surahDetailModelList = new ArrayList<>();

        adapter = new SurahDetailAdapter(getActivity(), surahDetailModelList, null, 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        DataBank dataBank = new DataBank(getActivity());
        surahDetailModelList.addAll(dataBank.getSurahWithEnglishTranslation(1));
        adapter.notifyDataSetChanged();

        DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity(), HORIZONTAL);
        recyclerView.addItemDecoration(itemDecor);

        Toast.makeText(getActivity(), "AYAt number" + surahDetailModelList.size(), Toast.LENGTH_SHORT).show();

        return view;
    }
}
