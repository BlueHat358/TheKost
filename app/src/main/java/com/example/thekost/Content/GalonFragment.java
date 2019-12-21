package com.example.thekost.Content;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.thekost.Adapter.ContentAdapter;
import com.example.thekost.Model.model;
import com.example.thekost.Pembayaran.DetailPembayaranActivity;
import com.example.thekost.Pembayaran.MetodePembayaranActivity;
import com.example.thekost.R;
import com.example.thekost.Utils.BtnRecyclerListener;
import com.example.thekost.Utils.ItemClickSupport;

import java.util.ArrayList;

import static com.example.thekost.Utils.PublicClassString.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalonFragment extends Fragment {

    private RecyclerView rvContent;
    private ArrayList<model> list = new ArrayList<>();

    private Button isiUlang, btnGalon;
    private ImageView back;

    private FragmentTransaction fragmentTransaction;


    public GalonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_galon, container, false);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        rvContent = rootView.findViewById(R.id.rv_content);
        isiUlang = rootView.findViewById(R.id.btn_isiUlang_fragment);
        back = rootView.findViewById(R.id.content_back_galon);
        btnGalon = rootView.findViewById(R.id.btn_content);

        rvContent.setHasFixedSize(true);

        list.addAll(getListContent());
        showRecyclerList();

        isiUlang.setOnClickListener(isiUlangClicked);
        back.setOnClickListener(backClicked);

        return rootView;
    }

    public ArrayList<model> getListContent(){
        String[] nama = getResources().getStringArray(R.array.nama_galon);
        int[] harga = getResources().getIntArray(R.array.harga_galon);
        int[] image = {
                R.drawable.aqua,
                R.drawable.prius,
                R.drawable.frozen
        };

        ArrayList<model> modelList = new ArrayList<>();
        for(int i = 0; i < nama.length; i++){
            model model_ = new model();
            model_.setName(nama[i]);
            model_.setHargaString("Rp. " + harga[i]);
            model_.setHarga(harga[i]);
            model_.setImage(image[i]);

            modelList.add(model_);
        }
        return modelList;
    }

    private void showRecyclerList(){
        rvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        ContentAdapter contentAdapter = new ContentAdapter(list, new BtnRecyclerListener() {
            @Override
            public void onClick(View view, int position) {
                showSelectedItem(list.get(position));
            }
        });
        rvContent.setAdapter(contentAdapter);

        ItemClickSupport.addTo(rvContent).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedItem(list.get(position));
            }
        });
    }

    private View.OnClickListener isiUlangClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GalonIsiFragment galonIsiFragment = new GalonIsiFragment();
            fragmentTransaction.replace(R.id.content_activity, galonIsiFragment,
                    GalonIsiFragment.class.getSimpleName()).commit();
        }
    };

    private View.OnClickListener backClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getActivity().onBackPressed();
        }
    };

    private void showSelectedItem(model m){
        Intent intent = new Intent(getActivity(), MetodePembayaranActivity.class);
        intent.putExtra(EXTRA_DETAIL_TEMP, m);
        intent.putExtra(EXTRA_KEY, 0);
        Log.d(STATE, "showSelectedItem() returned: " + m.getName());
        Log.d(STATE, "showSelectedItem() returned: " + m.getHarga());
        Log.d(STATE, "showSelectedItem() returned: " + m.getImage());
        startActivity(intent);
    }
}
