package com.example.thekost.Homepage;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.thekost.Content.ContentActivity;
import com.example.thekost.DataPreference;
import com.example.thekost.MainActivity;
import com.example.thekost.Model.model;
import com.example.thekost.Pembayaran.MetodePembayaranActivity;
import com.example.thekost.R;
import com.example.thekost.Setting.SettingActivity;

import java.util.ArrayList;
import java.util.Collection;

import static com.example.thekost.Utils.PublicClassString.HOMEFRAGMENT;
import static com.example.thekost.Utils.PublicClassString.STATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {

    ImageView imgHomepage;
    LinearLayout Content;
    ImageButton imgBtn_galon, setting, imgBtn_gas, imgBtn_listrik;
    Button pembayaran, topup;

    Animation fromBottom;

    private FragmentTransaction fragmentTransaction;

    public HomePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);

        fromBottom = AnimationUtils.loadAnimation(getContext(), R.anim.frombottom);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        imgHomepage = rootView.findViewById(R.id.img_homepage);
        Content = rootView.findViewById(R.id.content);
        imgHomepage = rootView.findViewById(R.id.img_homepage);
        setting = rootView.findViewById(R.id.btn_setting);
        imgBtn_galon = rootView.findViewById(R.id.imgBtnGalon);
        imgBtn_gas = rootView.findViewById(R.id.imgBtnGas);
        imgBtn_listrik = rootView.findViewById(R.id.imgBtnListrik);
        pembayaran = rootView.findViewById(R.id.btn_pembayaran);
        topup = rootView.findViewById(R.id.btn_topUp);

        imgHomepage.animate().translationY(-2800).setDuration(800).setStartDelay(1000);

        Content.startAnimation(fromBottom);

        setting.setOnClickListener(settingClicked);
        imgBtn_galon.setOnClickListener(galonClicked);
        imgBtn_gas.setOnClickListener(gasClicked);
        imgBtn_listrik.setOnClickListener(listrikClicked);
        pembayaran.setOnClickListener(pembayaranClicked);
        topup.setOnClickListener(topupclick);

        return rootView;
    }

    private View.OnClickListener settingClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener galonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), ContentActivity.class);
            intent.putExtra(HOMEFRAGMENT, 1);
            startActivity(intent);
        }
    };

    private View.OnClickListener gasClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), ContentActivity.class);
            intent.putExtra(HOMEFRAGMENT, 2);
            startActivity(intent);
        }
    };

    private View.OnClickListener listrikClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), ContentActivity.class);
            intent.putExtra(HOMEFRAGMENT, 3);
            startActivity(intent);
        }
    };

    private View.OnClickListener pembayaranClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PembayaranFragment pembayaranFragment = new PembayaranFragment();
            fragmentTransaction.replace(R.id.container, pembayaranFragment,
                    PembayaranFragment.class.getSimpleName()).commit();

        }
    };

    private View.OnClickListener topupclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getContext(), MetodePembayaranActivity.class);
            startActivity(intent);
        }
    };

}
