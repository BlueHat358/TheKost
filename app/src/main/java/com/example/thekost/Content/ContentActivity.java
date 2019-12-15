package com.example.thekost.Content;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.example.thekost.Adapter.ContentAdapter;
import com.example.thekost.Homepage.HomePageFragment;
import com.example.thekost.Model.model;
import com.example.thekost.R;

import java.util.ArrayList;

import static com.example.thekost.Utils.PublicClassString.HOMEFRAGMENT;

public class ContentActivity extends AppCompatActivity {

    private final static String STATE = "state";
    FragmentTransaction fragmentTransaction;

    int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        getSupportActionBar().hide();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Intent intent = getIntent();
        Id = intent.getIntExtra(HOMEFRAGMENT, 1);

        RunFragment(Id);
    }

    private void RunFragment(int id){
        switch (id){
            case 1:
                GalonFragment galonFragment = new GalonFragment();
                fragmentTransaction.replace(R.id.content_activity, galonFragment,
                        GalonFragment.class.getSimpleName()).commit();
                break;
            case 2:
                GasFragment gasFragment = new GasFragment();
                fragmentTransaction.replace(R.id.content_activity, gasFragment,
                        GasFragment.class.getSimpleName()).commit();
                break;
            case 3:
                ListrikFragment listrikFragment = new ListrikFragment();
                fragmentTransaction.replace(R.id.content_activity, listrikFragment,
                        ListrikFragment.class.getSimpleName()).commit();
                break;
        }
    }
}
