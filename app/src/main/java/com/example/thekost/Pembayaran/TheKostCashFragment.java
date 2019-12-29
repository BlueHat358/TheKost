package com.example.thekost.Pembayaran;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thekost.MainActivity;
import com.example.thekost.Model.history;
import com.example.thekost.Model.model;
import com.example.thekost.Model.tagihan_listrik;
import com.example.thekost.R;
import com.example.thekost.Utils.getFormatRupiah;
import com.example.thekost.db.HistoryHelper;
import com.example.thekost.db.KostCash.TopUpHelper;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

import static android.provider.BaseColumns._ID;
import static com.example.thekost.Utils.PublicClassString.EXTRA_DETAIL_FRAGMENT;
import static com.example.thekost.Utils.PublicClassString.EXTRA_KEY;
import static com.example.thekost.Utils.PublicClassString.STATE;
import static com.example.thekost.db.DatabaseContract.HistoryColumn.DITERIMA;
import static com.example.thekost.db.DatabaseContract.HistoryColumn.HARGA;
import static com.example.thekost.db.DatabaseContract.HistoryColumn.IMAGE;
import static com.example.thekost.db.DatabaseContract.HistoryColumn.NAMA;
import static com.example.thekost.db.DatabaseContract.HistoryColumn.STATUS;
import static com.example.thekost.db.KostCash.DataBaseContract.TopUpColumn.BONUS;
import static com.example.thekost.db.KostCash.DataBaseContract.TopUpColumn.NOMINAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class TheKostCashFragment extends Fragment {

    private TopUpHelper topUpHelper;
    private HistoryHelper historyHelper;
    history history_;
    model m;
    tagihan_listrik tagihanListrik;
    TextView tvHarga, tvBonus, tvSaldo;

    int total, harga, bonus, key, price, id, saldo, nominal, listBonus[];

    Button lanjut;


    public TheKostCashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_the_kost_cash, container, false);

        tvHarga = view.findViewById(R.id.tv_total_kostcash);
        tvBonus = view.findViewById(R.id.tv_bonus_kostcash);
        tvSaldo = view.findViewById(R.id.tv_saldo_kostcash_pembayaran);
        lanjut = view.findViewById(R.id.btn_lanjut_kostcash);

        lanjut.setOnClickListener(lanjutClicked);

        int random = new Random().nextInt((6 - 1) + 1) + 1;

        listBonus = new int[]{
                500,
                1000,
                1500,
                2000,
                2500,
                3000
        };

        topUpHelper = TopUpHelper.getINSTANCE(getActivity());
        historyHelper = HistoryHelper.getINSTANCE(getActivity());
        getFormatRupiah getformat = new getFormatRupiah();
        m = new model();
        history_ = new history();

        key = getArguments().getInt(EXTRA_KEY);

        Log.e("KEY = ", key+"");

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

        bonus = listBonus[random-1];
        String concate = "Selamat anda mendapatkan bonus potongan " + getformat.getFormat(bonus) +
                " pada pembelian berikutnya menggunakan <b><i>KoshCash</i></b>";

        Log.e("Concate ", concate);

        topUpHelper.open();
        nominal = 0;

        Cursor cursor = topUpHelper.queryAll();
        cursor.moveToNext();
        nominal = cursor.getInt(cursor.getColumnIndexOrThrow(NOMINAL));
        Log.e(STATE+"ID : ", cursor.getInt(cursor.getColumnIndexOrThrow(_ID))+"");
        id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
        harga = cursor.getInt(cursor.getColumnIndexOrThrow(BONUS));
        saldo = nominal;


        topUpHelper.close();

        if (harga > 0){
            Toast.makeText(getActivity(), "Anda Memiliki Bonus Potongan : " + getformat.getFormat(harga), Toast.LENGTH_LONG).show();
        }

        Log.e("Hasil Pengurangan = ", nominal + " - " + "{" + price+" + " + harga + "}");

        total = nominal - (price - harga);

        Log.e("Hasil Pengurangan = ", nominal + "");

        if(total < 0){
            tvBonus.setText("");
            Toast.makeText(getActivity(), "Maaf Saldo Kost Cash Kamu Kurang", Toast.LENGTH_LONG).show();
        }else {
            tvBonus.setText(Html.fromHtml(concate));
            Log.e("STATE", "Else Nominal Run");
            topUpHelper.open();
            ContentValues values = new ContentValues();
            values.put(NOMINAL, total);
            values.put(BONUS, bonus);

            long result = topUpHelper.update(Integer.toString(id), values);
            topUpHelper.close();
        }

        price -= harga;

        tvSaldo.setText(getformat.getFormat(saldo));
        tvHarga.setText(getformat.getFormat(price));

        return view;
    }

    private View.OnClickListener lanjutClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.e("Nominal", nominal + "");
            if(nominal > 0){
                Log.e("STATE", "Nominal Run");
                historyHelper.open();
                ContentValues values = new ContentValues();
                values.put(NAMA, history_.getNama());
                values.put(HARGA, history_.getHarga());
                values.put(STATUS, "Telah Dibayar");
                values.put(DITERIMA, 1);
                values.put(IMAGE, history_.getImage());


                Log.e(STATE, values.toString());
                Log.e(STATE, history_.getDiterima() + "");

                long result = historyHelper.insert(values);
                historyHelper.close();
                if (result > 0)
                    Log.e(STATE, "onClick: " + "Berhasil Insert Database");
                else
                    Log.e(STATE, "onClick: " + "Gagal Insert Database");
            }
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getActivity().finish();
        }
    };

}
