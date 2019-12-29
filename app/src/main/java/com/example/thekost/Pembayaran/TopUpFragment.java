package com.example.thekost.Pembayaran;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.thekost.R;
import com.isapanah.awesomespinner.AwesomeSpinner;

import java.util.ArrayList;
import java.util.List;

import static com.example.thekost.Utils.PublicClassString.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopUpFragment extends Fragment {

    AwesomeSpinner spinner;
    String[] list;
    int[] harga;


    public TopUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_top_up, container, false);

//        spinner.setHintTextColor(R.color.magenta);
//        spinner.setSelectedItemHintColor(R.color.blue);
//        spinner.setDownArrowTintColor(R.color.red);

        spinner =  view.findViewById(R.id.spinner_nominal_topUp);

        list = new String[]{
                "Rp. 10.000",
                "Rp. 20.000",
                "Rp. 50.000",
                "Rp. 100.000",
                "Rp. 500.000",
                "Rp. 1.000.000"
        };

        harga = new int[]{
                10000,
                20000,
                50000,
                100000,
                500000,
                1000000
        };


        List<String> categories = new ArrayList<String>();
        categories.add(list[0]);
        categories.add(list[1]);
        categories.add(list[2]);
        categories.add(list[3]);
        categories.add(list[4]);
        categories.add(list[5]);

        ArrayAdapter<String> nominal = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);
        spinner.setAdapter(nominal);
        spinner.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>()
        {
            @Override
            public void onItemSelected(int position, String itemAtPosition)
            {
                Log.e(STATE, harga[position]+"");
            }
        });

        return view;
    }

}
