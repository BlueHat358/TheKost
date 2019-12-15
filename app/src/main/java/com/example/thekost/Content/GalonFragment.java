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

/**
 * A simple {@link Fragment} subclass.
 */
public class GalonFragment extends Fragment {

    private RecyclerView rvContent;
    private ArrayList<model> list = new ArrayList<>();

    private Button isiUlang;
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

        rvContent.setHasFixedSize(true);

        list.addAll(getListContent());
        showRecyclerList();

        isiUlang.setOnClickListener(isiUlangClicked);
        back.setOnClickListener(backClicked);

        return rootView;
    }

    public ArrayList<model> getListContent(){
        String[] nama = getResources().getStringArray(R.array.nama_galon);
        String[] harga = getResources().getStringArray(R.array.harga_galon);
        int[] image = {
                R.drawable.aqua,
                R.drawable.prius,
                R.drawable.frozen
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
}
