package com.example.thekost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.thekost.Homepage.HomePageFragment;
import com.example.thekost.Login.LoginActivity;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private String login;

    private static final String STATE = "state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        DataPreference dataPreference;

        dataPreference = new DataPreference(MainActivity.this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        login = dataPreference.getLogin();

//        HomePageFragment homePageFragment = new HomePageFragment();
//        fragmentTransaction.replace(R.id.container, homePageFragment,
//                HomePageFragment.class.getSimpleName()).commit();

        Log.e(STATE, login + "");
        Log.e(STATE, login.equals("1") + "");
        Log.e(STATE, login.equals("0") + "");

        if(login.equals("1")){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else if (login.equals("0")) {
            HomePageFragment homePageFragment = new HomePageFragment();
            fragmentTransaction.replace(R.id.container, homePageFragment,
                    HomePageFragment.class.getSimpleName()).commit();
        }
    }
}
