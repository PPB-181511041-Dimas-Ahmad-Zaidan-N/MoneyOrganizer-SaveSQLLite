package com.dimas.moneyorganizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //---Inflate the layout for this fragment---
        View view = inflater.inflate(R.layout.fragment1, container, false);
        TextView tvPendapatan = view.findViewById(R.id.tv_total_pendapatan);
        TextView tvPengeluaran = view.findViewById(R.id.tv_total_pengeluaran);
        TextView tvBalancing = view.findViewById(R.id.tv_total_balancing);

//        Bundle bundle = getArguments();
//        assert bundle != null;
//        int pendapatan = bundle.getInt("pendapatan",0);
//        int pengeluaran = bundle.getInt("pengeluaran",0);
//        int balancing = pendapatan - pengeluaran;
//
//        tvPendapatan.setText(pendapatan);
//        tvPengeluaran.setText(pengeluaran);
//        tvBalancing.setText(balancing);

        return view;
    }
}
