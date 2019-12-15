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

import com.example.thekost.R;

import static com.example.thekost.Utils.PublicClassString.STATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListrikTagihanFragment extends Fragment {

    private Button token;
    private ImageView back;

    private FragmentTransaction fragmentTransaction;


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

        back = view.findViewById(R.id.content_back_tagihan);
        token = view.findViewById(R.id.btn_token_listrik);

        back.setOnClickListener(backClicked);
        token.setOnClickListener(tokenClicked);

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

}
