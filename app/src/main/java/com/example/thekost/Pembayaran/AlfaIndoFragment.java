package com.example.thekost.Pembayaran;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
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
import com.example.thekost.Utils.getFormatRupiah;
import com.example.thekost.db.HistoryHelper;
import com.example.thekost.db.KostCash.TopUpHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.example.thekost.Utils.PublicClassString.*;
import static com.example.thekost.db.DatabaseContract.HistoryColumn.*;
import static com.example.thekost.db.KostCash.DataBaseContract.TopUpColumn.BONUS;
import static com.example.thekost.db.KostCash.DataBaseContract.TopUpColumn.NOMINAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlfaIndoFragment extends Fragment {

    private model m;
    private tagihan_listrik tagihanListrik;
    private HistoryHelper historyHelper;
    private TopUpHelper topUpHelper;
    private SimpleDateFormat dateFormat;
    private Date date;
    private CountDownTimer ctimer;

    private TextView kode, total, tv_title, tvTime;
    Button selesai;

    history history_;

    int random;
    final int min = 1000000000;
    final int max = Integer.MAX_VALUE;
    int key, key1;
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
        tvTime = view.findViewById(R.id.tv_time_alfaindo);

        key = getArguments().getInt(EXTRA_KEY);
        key1 = getArguments().getInt(EXTRA_KEYS);

        getFormatRupiah formatRupiah = new getFormatRupiah();
        dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.getDefault());
        GregorianCalendar gc = new GregorianCalendar();
        GregorianCalendar gc1= new GregorianCalendar();

        gc.add(Calendar.DATE, 1);
        gc1.add(Calendar.DATE, 0);

        long diff = gc.getTimeInMillis() - gc1.getTimeInMillis();

        startTimer(diff);

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
        }else if(key == 1){
            tagihanListrik = getArguments().getParcelable(EXTRA_DETAIL_FRAGMENT);
            price = tagihanListrik.getTotal() - tagihanListrik.getDenda();
            history_.setImage(tagihanListrik.getImage());
            history_.setDiterima(0);
            history_.setHarga(price);
            history_.setStatus("Menunggu Pembayaran");
            history_.setNama(tagihanListrik.getNama());
        }else{
            price = getArguments().getInt(EXTRA_TOPUP);
        }

        tv_title.setText(title);
        if(title == "Alfamart"){
            kode.setText("TKA" + random);
        }else{
            kode.setText("TKI" + random);
        }

        Log.e(STATE, price + "");

        total.setText(formatRupiah.getFormat(price));

        selesai.setOnClickListener(selesaiClicked);

        Log.e("KEY1 = ", key1 + "");

        return view;
    }

    private View.OnClickListener selesaiClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.e("KEY Selesai = ", key1 + "");

            if (key1 == 0){
                historyHelper.open();
                ContentValues values = new ContentValues();
                values.put(NAMA, history_.getNama());
                values.put(HARGA, history_.getHarga());
                values.put(STATUS, history_.getStatus());
                values.put(DITERIMA, history_.getDiterima());
                values.put(IMAGE, history_.getImage());


                Log.e(STATE, values.toString());
                Log.e(STATE, history_.getDiterima() + "");

                long result = historyHelper.insert(values);
                //historyHelper.close();
                if (result > 0)
                    Log.e(STATE, "onClick: " + "Berhasil Insert Database");
                else
                    Log.e(STATE, "onClick: " + "Gagal Insert Database");
            }else if(key1 == 1){
                topUpHelper = TopUpHelper.getINSTANCE(getActivity());
                topUpHelper.open();
                int nominal = 0;

                Cursor cursor = topUpHelper.queryAll();
                cursor.moveToNext();
                nominal = cursor.getInt(cursor.getColumnIndexOrThrow(NOMINAL));
                Log.e(STATE+"ID : ", cursor.getInt(cursor.getColumnIndexOrThrow(_ID))+"");

                topUpHelper.close();

                nominal += price;

                topUpHelper.open();
                ContentValues values = new ContentValues();
                values.put(NOMINAL, nominal);

                long result = topUpHelper.update("1", values);
                if (result > 0)
                    Log.e(STATE, "onClick: " + "Berhasil Update Database");
                else
                    Log.e(STATE, "onClick: " + "Gagal Update Database");
                topUpHelper.close();
            }
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getActivity().finish();
        }
    };

    private void startTimer(long milis){
        ctimer = new CountDownTimer(milis, 1000) {
            @Override
            public void onTick(long l) {
                String t = (TimeUnit.MILLISECONDS.toHours(l) + " Jam : " +
                        (TimeUnit.MILLISECONDS.toMinutes(l) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(l)) + " Menit : "+
                        (TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l))+ " Detik")));
                tvTime.setText(t);
            }

            @Override
            public void onFinish() {

            }
        };
        ctimer.start();
    }

    void cancelTimer() {
        if(ctimer!=null) {
            ctimer.cancel();
            ctimer = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        cancelTimer();
        Log.e("STOP" , "CountDown is Stopped");
    }
}
