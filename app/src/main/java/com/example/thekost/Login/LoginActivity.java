package com.example.thekost.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.thekost.Daftar.DaftarActivity;
import com.example.thekost.R;

public class LoginActivity extends AppCompatActivity {

    TextView ketentuan;
    Button masuk, daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        ketentuan = findViewById(R.id.login_ketentuan);
        String text = "Dengan masuk atau mendaftar, kamu menyetujui <b>Ketentuan Layanan</b>" +
                "dan <b>Kebijakan Privasi.</b>";
        ketentuan.setText(Html.fromHtml(text));

        masuk = findViewById(R.id.btn_masuk);
        daftar = findViewById(R.id.btn_daftar);

        masuk.setOnClickListener(masuk_click);
        daftar.setOnClickListener(daftar_click);
    }

    private View.OnClickListener masuk_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, LoginActivity1.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener daftar_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, DaftarActivity.class);
            startActivity(intent);
        }
    };
}
