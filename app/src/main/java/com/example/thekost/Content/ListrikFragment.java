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
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.thekost.DataPreference;
import com.example.thekost.Model.tagihan_listrik;
import com.example.thekost.Pembayaran.MetodePembayaranActivity;
import com.example.thekost.R;

import java.util.zip.Inflater;

import static com.example.thekost.Utils.PublicClassString.EXTRA_DETAIL_TEMP;
import static com.example.thekost.Utils.PublicClassString.EXTRA_KEY;
import static com.example.thekost.Utils.PublicClassString.EXTRA_NAMA_ITEM;
import static com.example.thekost.Utils.PublicClassString.EXTRA_PRICE;
import static com.example.thekost.Utils.PublicClassString.STATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListrikFragment extends Fragment {

    ToggleButton btn20, btn50, btn100, btn200, btn500, btn1jt;
    private Button tagihan, lanjut;
    private ImageView back;

    int id, prices[] = {20000, 50000, 100000, 200000, 500000, 1000000},
        price = 0;

    String idPelanggan = "587273028500";

    private FragmentTransaction fragmentTransaction;

    tagihan_listrik tagihanListrik;

    public ListrikFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listrik, container, false);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        tagihanListrik = new tagihan_listrik();

        btn20 = view.findViewById(R.id.btnTgl_20);
        btn50 = view.findViewById(R.id.btnTgl_50);
        btn100 = view.findViewById(R.id.btnTgl_100);
        btn200 = view.findViewById(R.id.btnTgl_200);
        btn500 = view.findViewById(R.id.btnTgl_500);
        btn1jt = view.findViewById(R.id.btnTgl_1jt);
        back = view.findViewById(R.id.content_back_token);
        tagihan = view.findViewById(R.id.btn_tagihan_listrik);
        lanjut = view.findViewById(R.id.btn_lanjut_listrik);

        SetTextBtn();

        btn20.setOnClickListener(toggleClick);
        btn50.setOnClickListener(toggleClick);
        btn100.setOnClickListener(toggleClick);
        btn200.setOnClickListener(toggleClick);
        btn500.setOnClickListener(toggleClick);
        btn1jt.setOnClickListener(toggleClick);
        back.setOnClickListener(backClicked);
        tagihan.setOnClickListener(tagihanClicked);
        lanjut.setOnClickListener(lanjutClicked);

        return view;
    }

    private void SetBGBtn(){
        btn20.setBackgroundResource(R.drawable.btntgl_style);
        btn50.setBackgroundResource(R.drawable.btntgl_style);
        btn100.setBackgroundResource(R.drawable.btntgl_style);
        btn200.setBackgroundResource(R.drawable.btntgl_style);
        btn500.setBackgroundResource(R.drawable.btntgl_style);
        btn1jt.setBackgroundResource(R.drawable.btntgl_style);
    }

    private void SetTextBtn(){
        btn20.setTextOff("20.000");
        btn50.setTextOff("50.000");
        btn100.setTextOff("100.000");
        btn200.setTextOff("200.000");
        btn500.setTextOff("500.000");
        btn1jt.setTextOff("1.000.000");
        btn20.setTextOn("20.000");
        btn50.setTextOn("50.000");
        btn100.setTextOn("100.000");
        btn200.setTextOn("200.000");
        btn500.setTextOn("500.000");
        btn1jt.setTextOn("1.000.000");
    }

    private void SetAllFalseBtn(){
        btn20.setChecked(false);
        btn50.setChecked(false);
        btn100.setChecked(false);
        btn200.setChecked(false);
        btn500.setChecked(false);
        btn1jt.setChecked(false);
    }

    private void setTagihanListrik(){
        tagihanListrik.setTotal(price);
        tagihanListrik.setNama("Budi Utomo");
        tagihanListrik.setDaya("R1M / 900");
        tagihanListrik.setDenda(0);
        tagihanListrik.setIdPelanggan("587273028500");
        tagihanListrik.setStandmeter("12576 - 15789");
        tagihanListrik.setNoRef("0FIN5487525387522");
        tagihanListrik.setTime("Desember 2019");
        tagihanListrik.setImage(R.drawable.logo_pln);
    }

    View.OnClickListener toggleClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            id = view.getId();
            switch (id){
                case R.id.btnTgl_20:
                    SetBGBtn();
                    SetAllFalseBtn();
                    btn20.setChecked(true);
                    btn20.setBackgroundResource(R.drawable.btntgl_style_clicked);
                    price = prices[0];
                    setTagihanListrik();
                    break;
                case R.id.btnTgl_50:
                    SetBGBtn();
                    SetAllFalseBtn();
                    btn50.setChecked(true);
                    btn50.setBackgroundResource(R.drawable.btntgl_style_clicked);
                    price = prices[1];
                    setTagihanListrik();
                    break;
                case R.id.btnTgl_100:
                    SetBGBtn();
                    SetAllFalseBtn();
                    btn100.setChecked(true);
                    btn100.setBackgroundResource(R.drawable.btntgl_style_clicked);
                    price = prices[2];
                    setTagihanListrik();
                    break;
                case R.id.btnTgl_200:
                    SetBGBtn();
                    SetAllFalseBtn();
                    btn200.setChecked(true);
                    btn200.setBackgroundResource(R.drawable.btntgl_style_clicked);
                    price = prices[3];
                    setTagihanListrik();
                    break;
                case R.id.btnTgl_500:
                    SetBGBtn();
                    SetAllFalseBtn();
                    btn500.setChecked(true);
                    btn500.setBackgroundResource(R.drawable.btntgl_style_clicked);
                    price = prices[4];
                    setTagihanListrik();
                    break;
                case R.id.btnTgl_1jt:
                    SetBGBtn();
                    SetAllFalseBtn();
                    btn1jt.setChecked(true);
                    btn1jt.setBackgroundResource(R.drawable.btntgl_style_clicked);
                    price = prices[5];
                    setTagihanListrik();
                    break;

                    default:
                        Toast.makeText(getActivity(), "Kamu hsrus memilih sebelum melanjutkan",
                                Toast.LENGTH_LONG).show();
                        break;
            }
        }
    };

    private View.OnClickListener backClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getActivity().onBackPressed();
            Log.e(STATE, "onClick: ");
        }
    };

    private View.OnClickListener tagihanClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ListrikTagihanFragment listrikTagihanFragment = new ListrikTagihanFragment();
            fragmentTransaction.replace(R.id.content_activity, listrikTagihanFragment,
                    ListrikTagihanFragment.class.getSimpleName()).commit();
        }
    };

    private View.OnClickListener lanjutClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), MetodePembayaranActivity.class);
            intent.putExtra(EXTRA_KEY, 1);
            intent.putExtra(EXTRA_DETAIL_TEMP, tagihanListrik);
            Log.e(STATE, "Listrik Fragemnt = " + tagihanListrik.getIdPelanggan());
            startActivity(intent);
            getActivity().finish();
        }
    };

}
