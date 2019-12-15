package com.example.thekost.Pembayaran;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thekost.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TheKostCashFragment extends Fragment {


    public TheKostCashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_the_kost_cash, container, false);
    }

}
