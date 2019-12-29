package com.example.thekost.Pembayaran;


import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thekost.R;
import com.example.thekost.Utils.getFormatRupiah;
import com.example.thekost.db.KostCash.TopUpHelper;
import com.isapanah.awesomespinner.AwesomeSpinner;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.example.thekost.Utils.PublicClassString.*;
import static com.example.thekost.db.KostCash.DataBaseContract.TopUpColumn.BONUS;
import static com.example.thekost.db.KostCash.DataBaseContract.TopUpColumn.NOMINAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopUpFragment extends Fragment {

    AwesomeSpinner spinner;
    TopUpHelper topUpHelper;
    String[] list;
    int[] harga;
    int price, saldo;

    Bundle bundle;

    RelativeLayout debit, alfa, indo;
    FragmentTransaction fragmentTransaction;
    TextView tv_nama, tvSaldo;
    ImageView imgItem;


    public TopUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_top_up, container, false);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        topUpHelper = TopUpHelper.getINSTANCE(getActivity());

        getFormatRupiah getformat = new getFormatRupiah();

        bundle = new Bundle();

        debit = view.findViewById(R.id.rl_debit_topup);
        alfa = view.findViewById(R.id.rl_alfamart_topup);
        indo = view.findViewById(R.id.rl_indomaret_topup);
        tvSaldo = view.findViewById(R.id.tv_saldo_metodeTopUp);

        debit.setOnClickListener(metode);
        alfa.setOnClickListener(metode);
        indo.setOnClickListener(metode);

        topUpHelper.open();
        Cursor cursor = topUpHelper.queryAll();
        cursor.moveToNext();
        saldo = cursor.getInt(cursor.getColumnIndexOrThrow(NOMINAL));
        topUpHelper.close();

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
                price = harga[position];
            }
        });

        tvSaldo.setText(getformat.getFormat(saldo));

        return view;
    }

    private View.OnClickListener metode = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();

            switch (id){
                case R.id.rl_debit_topup:
                    DebitFragment debitFragment = new DebitFragment();
                    fragmentTransaction.replace(R.id.metode_topup_activity, debitFragment,
                            DebitFragment.class.getSimpleName()).commit();
                    break;
                case R.id.rl_alfamart_topup:
                    AlfaIndoFragment alfaIndoFragment = new AlfaIndoFragment();
                    bundle.putString(EXTRA_TITLE, "Alfamart");
                    bundle.putInt(EXTRA_KEYS, 1);
                    bundle.putInt(EXTRA_KEY, 2);
                    bundle.putInt(EXTRA_TOPUP, price);
                    alfaIndoFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.metode_topup_activity, alfaIndoFragment,
                            AlfaIndoFragment.class.getSimpleName()).commit();
                    break;

                case R.id.rl_indomaret_topup:
                    AlfaIndoFragment alfaIndoFragment1 = new AlfaIndoFragment();
                    bundle.putString(EXTRA_TITLE, "Indomaret");
                    bundle.putInt(EXTRA_KEYS, 1);
                    bundle.putInt(EXTRA_KEY, 2);
                    bundle.putInt(EXTRA_TOPUP, price);
                    alfaIndoFragment1.setArguments(bundle);
                    fragmentTransaction.replace(R.id.metode_topup_activity, alfaIndoFragment1,
                            AlfaIndoFragment.class.getSimpleName()).commit();
                    break;
            }
        }
    };

}
