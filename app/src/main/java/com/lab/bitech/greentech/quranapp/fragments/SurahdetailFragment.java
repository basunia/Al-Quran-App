package com.lab.bitech.greentech.quranapp.fragments;

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
import android.widget.TextView;

import com.lab.bitech.greentech.quranapp.R;
import com.lab.bitech.greentech.quranapp.adapters.SurahDetailAdapter;
import com.lab.bitech.greentech.quranapp.db.DataBank;
import com.lab.bitech.greentech.quranapp.models.SurahDetailModel;
import com.lab.bitech.greentech.quranapp.utils.Commons;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurahdetailFragment extends Fragment {

    @BindView(R.id.recyclerViewSurahDetail)
    RecyclerView recyclerView;
    @BindView(R.id.tvSurahName)
    TextView tvSurahName;
    List<SurahDetailModel> surahDetailModelList;
    SurahDetailAdapter adapter;
    private int pagerPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_surah_detail, container, false);
        ButterKnife.bind(this, view);

        surahDetailModelList = new ArrayList<>();

        adapter = new SurahDetailAdapter(getActivity(), surahDetailModelList, null, 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        if (getArguments() != null) {
            pagerPosition = getArguments().getInt(Commons.ITEM_POSITION, 1);
            if (1 <= pagerPosition && pagerPosition <= 6)
                fetchDatabase();
            Log.d("Position", "PagerPosition " + pagerPosition);
        }

        DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(itemDecor);

        //Toast.makeText(getActivity(), "AYAt number" + surahDetailModelList.size(), Toast.LENGTH_SHORT).show();

        return view;
    }

    private void fetchDatabase() {
        DataBank dataBank = new DataBank(getActivity());
        tvSurahName.setText(dataBank.getSurah(pagerPosition).getSurahName());
        if (Prefs.getString(Commons.LANGUAGES, Commons.ENGLISH).equals(Commons.ENGLISH))
            surahDetailModelList.addAll(dataBank.getSurahWithEnglishTranslation(pagerPosition));
        else
            surahDetailModelList.addAll(dataBank.getSurahWithBengaliTranslation(pagerPosition));
        adapter.notifyDataSetChanged();
    }
}
