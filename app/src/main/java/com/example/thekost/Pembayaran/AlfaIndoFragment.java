package com.example.thekost.Pembayaran;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.thekost.Homepage.HomePageFragment;
import com.example.thekost.MainActivity;
import com.example.thekost.Model.history;
import com.example.thekost.Model.model;
import com.example.thekost.Model.tagihan_listrik;
import com.example.thekost.R;
import com.example.thekost.db.HistoryHelper;

import java.util.Random;

import static com.example.thekost.Utils.PublicClassString.*;
import static com.example.thekost.db.DatabaseContract.HistoryColumn.DITERIMA;
import static com.example.thekost.db.DatabaseContract.HistoryColumn.HARGA;
import static com.example.thekost.db.DatabaseContract.HistoryColumn.IMAGE;
import static com.example.thekost.db.DatabaseContract.HistoryColumn.NAMA;
import static com.example.thekost.db.DatabaseContract.HistoryColumn.STATUS;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlfaIndoFragment extends Fragment {

    private model m;
    private tagihan_listrik tagihanListrik;
    private HistoryHelper historyHelper;

    private TextView kode, total, tv_title;
    Button selesai;

    history history_;

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
        history_ = new history();
        historyHelper = HistoryHelper.getINSTANCE(getActivity());

        title = getArguments().getString(EXTRA_TITLE);

        tv_title = view.findViewById(R.id.tv_title_alfaindo);
        kode = view.findViewById(R.id.tv_kode_pembayaran_alfaindo);
        total = view.findViewById(R.id.tv_total_alfaindo);
        selesai = view.findViewById(R.id.btn_selesai_alfindo);

        key = getArguments().getInt(EXTRA_KEY);

        Log.e(STATE, "KEY = " + key);

        if(key == 0){
            m = getArguments().getParcelable(EXTRA_DETAIL_FRAGMENT);
            price = m.getHarga();
            history_.setImage(m.getImage());
            history_.setDiterima(0);
            history_.setHarga(price);
            history_.setStatus("Menunggu Pembayaran");
            history_.setNama(m.getName());
            Log.e(STATE, "alfaindo = " + m.getHarga());
        }else{
            tagihanListrik = getArguments().getParcelable(EXTRA_DETAIL_FRAGMENT);
            price = tagihanListrik.getTotal() - tagihanListrik.getDenda();
            history_.setImage(tagihanListrik.getImage());
            history_.setDiterima(0);
            history_.setHarga(price);
            history_.setStatus("Menunggu Pembayaran");
            history_.setNama(tagihanListrik.getNama());
        }

        tv_title.setText(title);
        if(title == "Alfamart"){
            kode.setText("TKA" + Integer.toString(random));
        }else{
            kode.setText("TKI" + Integer.toString(random));
        }

        total.setText("Rp. " + price);

        selesai.setOnClickListener(selesaiClicked);

        return view;
    }

    private View.OnClickListener selesaiClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            historyHelper.open();
            ContentValues values = new ContentValues();
            values.put(NAMA, history_.getNama());
            values.put(HARGA, history_.getHarga());
            values.put(STATUS, history_.getStatus());
            values.put(DITERIMA, history_.getDiterima());
            values.put(IMAGE, history_.getImage());

            Log.e(STATE, values.toString());
            Log.e(STATE, history_.getId() + "");

            long result = historyHelper.insert(values);
            if (result > 0)
                Log.e(STATE, "onClick: " + "Berhasil Insert Database");
            else
                Log.e(STATE, "onClick: " + "Gagal Insert Database");
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getActivity().finish();
        }
    };

}
