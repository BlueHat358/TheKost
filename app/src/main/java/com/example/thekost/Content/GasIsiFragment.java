package com.example.thekost.Content;


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
import com.example.thekost.R;

import java.util.ArrayList;

import static com.example.thekost.Utils.PublicClassString.STATE;

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
        String[] harga = getResources().getStringArray(R.array.harga_gas_isiUlang);
        int[] image = {
                R.drawable.gas_3kg,
                R.drawable.gas_5kg,
                R.drawable.gas_12kg
        };

        ArrayList<model> modelList = new ArrayList<>();
        for(int i = 0; i < nama.length; i++){
            model model_ = new model();
            model_.setName(nama[i]);
            model_.setHarga(harga[i]);
            model_.setImage(image[i]);

            modelList.add(model_);
        }
        return modelList;
    }

    private void showRecyclerList(){
        rvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        ContentAdapter contentAdapter = new ContentAdapter(list);
        rvContent.setAdapter(contentAdapter);
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

}
