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
import com.example.thekost.Pembayaran.MetodePembayaranActivity;
import com.example.thekost.R;
import com.example.thekost.Utils.BtnRecyclerListener;
import com.example.thekost.Utils.ItemClickSupport;

import java.util.ArrayList;

import static com.example.thekost.Utils.PublicClassString.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class GasIsiFragment extends Fragment {

    private RecyclerView rvContent;
    private ArrayList<model> list = new ArrayList<>();

    private Button gas;
    private ImageView back;

    private FragmentTransaction fragmentTransaction;


    public GasIsiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gas_isi, container, false);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        rvContent = rootView.findViewById(R.id.rv_content);
        gas = rootView.findViewById(R.id.btn_gas_fragment);
        back = rootView.findViewById(R.id.content_back_isi_gas);

        rvContent.setHasFixedSize(true);

        list.addAll(getListContent());
        showRecyclerList();

        gas.setOnClickListener(gasClicked);
        back.setOnClickListener(backClicked);

        return rootView;
    }

    public ArrayList<model> getListContent(){
        String[] nama = getResources().getStringArray(R.array.nama_gas);
        int[] harga = getResources().getIntArray(R.array.harga_gas_isiUlang);
        int[] image = {
                R.drawable.gas_3kg,
                R.drawable.gas_5kg,
                R.drawable.gas_12kg
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

    private View.OnClickListener gasClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GasFragment gasFragment = new GasFragment();
            fragmentTransaction.replace(R.id.content_activity, gasFragment,
                    GasFragment.class.getSimpleName()).commit();
        }
    };

    private View.OnClickListener backClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getActivity().onBackPressed();
            Log.e(STATE, "onClick: ");
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
