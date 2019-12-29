package com.example.thekost.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.thekost.Daftar.DaftarActivity;
import com.example.thekost.LupaPassword.LupaPassActivity;

import com.example.thekost.MainActivity;
import com.example.thekost.DataPreference;
import com.example.thekost.R;
import com.example.thekost.db.KostCash.TopUpHelper;

import static com.example.thekost.Utils.PublicClassString.STATE;
import static com.example.thekost.db.KostCash.DataBaseContract.TopUpColumn.BONUS;
import static com.example.thekost.db.KostCash.DataBaseContract.TopUpColumn.NOMINAL;

public class LoginActivity1 extends AppCompatActivity {

    Button lupa_pass, daftar_sekarang, login;

    private DataPreference dataPreference;
    private TopUpHelper topUpHelper;

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        getSupportActionBar().hide();

        dataPreference = new DataPreference(this);
        topUpHelper = TopUpHelper.getINSTANCE(this);

        daftar_sekarang = findViewById(R.id.btn_daftar_sekarang_loginActivity);
        lupa_pass = findViewById(R.id.btn_lupa_pass);
        login = findViewById(R.id.btn_login);
        back = findViewById(R.id.btn_back_login);

        String pass = "<b>Lupa password?</b>";
        String daftar = "<b>Daftar sekarang</b>";

        daftar_sekarang.setText(Html.fromHtml(daftar));
        lupa_pass.setText(Html.fromHtml(pass));

        login.setOnClickListener(login_click);
        lupa_pass.setOnClickListener(lupaPass);
        daftar_sekarang.setOnClickListener(daftar_);
        back.setOnClickListener(backClicked);
    }

    private View.OnClickListener login_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dataPreference.setLogin("0");

            topUpHelper.open();
            ContentValues values = new ContentValues();
            values.put(NOMINAL, 0);
            values.put(BONUS, 0);
            long result = topUpHelper.insert(values);
            if (result > 0)
                Log.e(STATE, "onClick: " + "Berhasil Insert Database");
            else
                Log.e(STATE, "onClick: " + "Gagal Insert Database");
            topUpHelper.close();

            Intent intent = new Intent(LoginActivity1.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    };

    private View.OnClickListener lupaPass = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity1.this, LupaPassActivity.class);
            startActivity(intent);
            finish();
        }
    };

    private View.OnClickListener daftar_ = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity1.this, DaftarActivity.class);
            startActivity(intent);
            finish();
        }
    };

    private View.OnClickListener backClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    };
}
