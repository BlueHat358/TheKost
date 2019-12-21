package com.example.thekost.Pembayaran;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.thekost.Model.model;
import com.example.thekost.Model.tagihan_listrik;
import com.example.thekost.R;

import static com.example.thekost.Utils.PublicClassString.EXTRA_DETAIL;
import static com.example.thekost.Utils.PublicClassString.EXTRA_DETAIL_FRAGMENT;
import static com.example.thekost.Utils.PublicClassString.EXTRA_DETAIL_TEMP;
import static com.example.thekost.Utils.PublicClassString.EXTRA_KEY;
import static com.example.thekost.Utils.PublicClassString.EXTRA_TITLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MetodeFragment extends Fragment {

    FragmentTransaction fragmentTransaction;

    RelativeLayout debit, alfa, indo, thekost;
    TextView tv_nama, tv_harga;
    ImageView imgItem;

    Bundle bundle;
    model m;
    tagihan_listrik tagihanListrik;

    int key, harga, img_item;
    String nama;


    public MetodeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_metode, container, false);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        debit = view.findViewById(R.id.rl_debit);
        alfa = view.findViewById(R.id.rl_alfamart);
        indo = view.findViewById(R.id.rl_indomaret);
        thekost = view.findViewById(R.id.rl_thekost);
        tv_nama = view.findViewById(R.id.tv_id_namabarang_pembayaran);
        tv_harga = view.findViewById(R.id.tv_total_pembayaran_metode);
        imgItem = view.findViewById(R.id.img_item);

        debit.setOnClickListener(metode);
        alfa.setOnClickListener(metode);
        indo.setOnClickListener(metode);
        thekost.setOnClickListener(metode);

        bundle = new Bundle();
        m = new model();
        tagihanListrik = new tagihan_listrik();

        key = getArguments().getInt(EXTRA_KEY);
        if(key == 0){
            m = getArguments().getParcelable(EXTRA_DETAIL_FRAGMENT);
            nama = m.getName();
            harga = m.getHarga();
            img_item = m.getImage();
        }else{
            tagihanListrik = getArguments().getParcelable(EXTRA_DETAIL_FRAGMENT);
            nama = tagihanListrik.getIdPelanggan();
            harga = tagihanListrik.getTotal() - tagihanListrik.getDenda();
            img_item = tagihanListrik.getImage();
        }

        tv_nama.setText(nama);
        tv_harga.setText("Rp. " + harga);
        imgItem.setImageResource(img_item);

        return view;
    }

    private void keys(int key){
        if(key == 0){
            bundle.putParcelable(EXTRA_DETAIL_FRAGMENT, m);
            bundle.putInt(EXTRA_KEY, 0);
        }else{
            bundle.putParcelable(EXTRA_DETAIL_FRAGMENT, tagihanListrik);
            bundle.putInt(EXTRA_KEY, 1);
        }
    }

    View.OnClickListener metode = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();

            switch (id){
                case R.id.rl_debit:
                    DebitFragment debitFragment = new DebitFragment();
                    fragmentTransaction.replace(R.id.metode_activity, debitFragment,
                            DebitFragment.class.getSimpleName()).commit();
                    break;
                case R.id.rl_alfamart:
                    AlfaIndoFragment alfaIndoFragment = new AlfaIndoFragment();
                    bundle.putString(EXTRA_TITLE, "Alfamart");
                    keys(key);
                    alfaIndoFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.metode_activity, alfaIndoFragment,
                            AlfaIndoFragment.class.getSimpleName()).commit();
                    break;

                case R.id.rl_indomaret:
                    AlfaIndoFragment alfaIndoFragment1 = new AlfaIndoFragment();
                    bundle.putString(EXTRA_TITLE, "Indomaret");
                    keys(key);
                    alfaIndoFragment1.setArguments(bundle);
                    fragmentTransaction.replace(R.id.metode_activity, alfaIndoFragment1,
                            AlfaIndoFragment.class.getSimpleName()).commit();
                    break;
                case R.id.rl_thekost:
                    TheKostCashFragment theKostCashFragment = new TheKostCashFragment();
                    fragmentTransaction.replace(R.id.metode_activity, theKostCashFragment,
                            TheKostCashFragment.class.getSimpleName()).commit();
                    break;
            }
        }
    };

}
