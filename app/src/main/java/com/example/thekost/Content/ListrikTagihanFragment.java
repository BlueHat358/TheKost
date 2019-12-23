package com.example.thekost.Content;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.thekost.Model.tagihan_listrik;
import com.example.thekost.Pembayaran.MetodePembayaranActivity;
import com.example.thekost.R;

import static com.example.thekost.Utils.PublicClassString.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListrikTagihanFragment extends Fragment {

    private Button token, bayar;
    private ImageView back;

    private FragmentTransaction fragmentTransaction;

    String idPelanggan = "587273028500";

    tagihan_listrik tagihanListrik;


    public ListrikTagihanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listrik_tagihan, container, false);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        tagihanListrik = new tagihan_listrik();


        back = view.findViewById(R.id.content_back_tagihan);
        token = view.findViewById(R.id.btn_token_listrik);
        bayar = view.findViewById(R.id.btn_bayar_listrik);

        back.setOnClickListener(backClicked);
        token.setOnClickListener(tokenClicked);
        bayar.setOnClickListener(bayarClicked);

        return view;
    }

    private View.OnClickListener backClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getActivity().onBackPressed();
            Log.e(STATE, "onClick: ");
        }
    };

    private View.OnClickListener tokenClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ListrikFragment listrikFragment = new ListrikFragment();
            fragmentTransaction.replace(R.id.content_activity, listrikFragment,
                    ListrikFragment.class.getSimpleName()).commit();
        }
    };

    private void setTagihanListrik(){
        tagihanListrik.setNama("Budi Utomo");
        tagihanListrik.setDaya("R1M / 900");
        tagihanListrik.setDenda(0);
        tagihanListrik.setIdPelanggan("587273028500");
        tagihanListrik.setStandmeter("12576 - 15789");
        tagihanListrik.setNoRef("0FIN5487525387522");
        tagihanListrik.setTotal(315583);
        tagihanListrik.setTime("Desember 2019");
        tagihanListrik.setImage(R.drawable.logo_pln);
    }

    private View.OnClickListener bayarClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setTagihanListrik();
            Intent intent = new Intent(getActivity(), MetodePembayaranActivity.class);
            intent.putExtra(EXTRA_KEY, 1);
            intent.putExtra(EXTRA_DETAIL_TEMP, tagihanListrik);
            startActivity(intent);
            getActivity().finish();
        }
    };

}
