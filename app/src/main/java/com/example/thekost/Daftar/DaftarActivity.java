package com.example.thekost.Daftar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.thekost.Login.LoginActivity1;
import com.example.thekost.MainActivity;
import com.example.thekost.R;

public class DaftarActivity extends AppCompatActivity {

    Button login_aja, daftar_sekarang;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        getSupportActionBar().hide();

        login_aja = findViewById(R.id.btn_login_aja);
        String login = "<b>Login aja</b>";
        login_aja.setText(Html.fromHtml(login));

        daftar_sekarang = findViewById(R.id.btn_daftar_sekarang);
        back = findViewById(R.id.btn_back_daftar);

        login_aja.setOnClickListener(masuk_click);
        daftar_sekarang.setOnClickListener(daftar_click);
        back.setOnClickListener(backClicked);
    }

    private View.OnClickListener masuk_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(DaftarActivity.this, LoginActivity1.class);
            startActivity(intent);
            finish();
        }
    };

    private View.OnClickListener daftar_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(DaftarActivity.this, DaftarActivity1.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener backClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    };
}
