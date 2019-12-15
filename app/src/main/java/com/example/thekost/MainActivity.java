package com.example.thekost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.thekost.Homepage.HomePageFragment;
import com.example.thekost.Login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private DataPreference dataPreference;
    String login;

    private static final String STATE = "state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        dataPreference = new DataPreference(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        login = dataPreference.getLogin();

//        HomePageFragment homePageFragment = new HomePageFragment();
//        fragmentTransaction.replace(R.id.container, homePageFragment,
//                HomePageFragment.class.getSimpleName()).commit();

        if(login == "1"){
            HomePageFragment homePageFragment = new HomePageFragment();
            fragmentTransaction.replace(R.id.container, homePageFragment,
                    HomePageFragment.class.getSimpleName()).commit();
        }
        else{
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
