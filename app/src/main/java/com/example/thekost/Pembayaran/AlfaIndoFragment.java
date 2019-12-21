package com.example.thekost.Pembayaran;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thekost.Model.model;
import com.example.thekost.Model.tagihan_listrik;
import com.example.thekost.R;

import java.util.Random;

import static com.example.thekost.Utils.PublicClassString.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlfaIndoFragment extends Fragment {

    private model m;
    private tagihan_listrik tagihanListrik;

    private TextView kode, total, tv_title;

    int random;
    final int min = 1000000000;
    final int max = Integer.MAX_VALUE;
    int key;
    int price;

    String title;


    public AlfaIndoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alfa_indo, container, false);

        random = new Random().nextInt((max - min) + 1) + min;

        m = new model();

        title = getArguments().getString(EXTRA_TITLE);

        tv_title = view.findViewById(R.id.tv_title_alfaindo);
        kode = view.findViewById(R.id.tv_kode_pembayaran_alfaindo);
        total = view.findViewById(R.id.tv_total_alfaindo);

        key = getArguments().getInt(EXTRA_KEY);

        Log.e(STATE, "KEY = " + key);

        if(key == 0){
            m = getArguments().getParcelable(EXTRA_DETAIL_FRAGMENT);
            price = m.getHarga();
            Log.e(STATE, "alfaindo = " + m.getHarga());
        }else{
            tagihanListrik = getArguments().getParcelable(EXTRA_DETAIL_FRAGMENT);
            price = tagihanListrik.getTotal() - tagihanListrik.getDenda();
        }

        tv_title.setText(title);
        if(title == "Alfamart"){
            kode.setText("TKA" + Integer.toString(random));
        }else{
            kode.setText("TKI" + Integer.toString(random));
        }

        total.setText("Rp. " + price);

        return view;
    }

}
