package com.example.thekost.Content;


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
import com.example.thekost.R;

import java.util.zip.Inflater;

import static com.example.thekost.Utils.PublicClassString.STATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListrikFragment extends Fragment {

    ToggleButton btn20, btn50, btn100, btn200, btn500, btn1jt;

    int id, prices[] = {20000, 50000, 100000, 200000, 500000, 1000000},
        price = 0;

    private Button tagihan;
    private ImageView back;

    private FragmentTransaction fragmentTransaction;

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

        btn20 = view.findViewById(R.id.btnTgl_20);
        btn50 = view.findViewById(R.id.btnTgl_50);
        btn100 = view.findViewById(R.id.btnTgl_100);
        btn200 = view.findViewById(R.id.btnTgl_200);
        btn500 = view.findViewById(R.id.btnTgl_500);
        btn1jt = view.findViewById(R.id.btnTgl_1jt);
        back = view.findViewById(R.id.content_back_token);
        tagihan = view.findViewById(R.id.btn_tagihan_listrik);

        SetTextBtn();

        btn20.setOnClickListener(toggleClick);
        btn50.setOnClickListener(toggleClick);
        btn100.setOnClickListener(toggleClick);
        btn200.setOnClickListener(toggleClick);
        btn500.setOnClickListener(toggleClick);
        btn1jt.setOnClickListener(toggleClick);
        back.setOnClickListener(backClicked);
        tagihan.setOnClickListener(tagihanClicked);

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
                    break;
                case R.id.btnTgl_50:
                    SetBGBtn();
                    SetAllFalseBtn();
                    btn50.setChecked(true);
                    btn50.setBackgroundResource(R.drawable.btntgl_style_clicked);
                    price = prices[1];
                    break;
                case R.id.btnTgl_100:
                    SetBGBtn();
                    SetAllFalseBtn();
                    btn100.setChecked(true);
                    btn100.setBackgroundResource(R.drawable.btntgl_style_clicked);
                    price = prices[2];
                    break;
                case R.id.btnTgl_200:
                    SetBGBtn();
                    SetAllFalseBtn();
                    btn200.setChecked(true);
                    btn200.setBackgroundResource(R.drawable.btntgl_style_clicked);
                    price = prices[3];
                    break;
                case R.id.btnTgl_500:
                    SetBGBtn();
                    SetAllFalseBtn();
                    btn500.setChecked(true);
                    btn500.setBackgroundResource(R.drawable.btntgl_style_clicked);
                    price = prices[4];
                    break;
                case R.id.btnTgl_1jt:
                    SetBGBtn();
                    SetAllFalseBtn();
                    btn1jt.setChecked(true);
                    btn1jt.setBackgroundResource(R.drawable.btntgl_style_clicked);
                    price = prices[5];
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

}
