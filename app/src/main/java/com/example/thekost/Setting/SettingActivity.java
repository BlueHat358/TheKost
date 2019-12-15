package com.example.thekost.Setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thekost.DataPreference;
import com.example.thekost.Login.LoginActivity;
import com.example.thekost.MainActivity;
import com.example.thekost.R;

import java.util.Set;

public class SettingActivity extends AppCompatActivity {

    DataPreference dataPreference;

    Button signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().hide();

        dataPreference = new DataPreference(this);

        signOut = findViewById(R.id.btn_signOut);

        signOut.setOnClickListener(signOutClicked);
    }

    private View.OnClickListener signOutClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dataPreference.setLogin("1");

            Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    };
}
