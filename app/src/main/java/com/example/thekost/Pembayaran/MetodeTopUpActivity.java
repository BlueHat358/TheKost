package com.example.thekost.Pembayaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.thekost.R;

public class MetodeTopUpActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode_top_up);

        getSupportActionBar().hide();

        bundle = new Bundle();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        TopUpFragment topUpFragment = new TopUpFragment();
        topUpFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.metode_topup_activity, topUpFragment,
                TopUpFragment.class.getSimpleName()).commit();
    }
}
