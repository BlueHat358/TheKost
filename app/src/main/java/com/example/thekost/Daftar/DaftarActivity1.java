package com.example.thekost.Daftar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thekost.DataPreference;
import com.example.thekost.Homepage.HomePageFragment;
import com.example.thekost.Login.LoginActivity1;
import com.example.thekost.MainActivity;
import com.example.thekost.R;

public class DaftarActivity1 extends AppCompatActivity {

    TextView tv_ketentuan;
    Button btn_daftar;

    FragmentTransaction fragmentTransaction;

    private DataPreference dataPreference;

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar1);

        dataPreference = new DataPreference(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        getSupportActionBar().hide();

        tv_ketentuan = findViewById(R.id.tv_ketentuan);
        String ketentuan = "Dengan masuk atau mendaftar, kamu menyetujui <b>Ketentuan Layanan</b>" +
                " dan <b>Kebijakan Privasi.</b>";
        tv_ketentuan.setText(Html.fromHtml(ketentuan));

        btn_daftar = findViewById(R.id.btn_daftar_sekarang_daftarActivity);
        back = findViewById(R.id.btn_back_daftar1);

        btn_daftar.setOnClickListener(daftar_click);
        back.setOnClickListener(backClicked);
    }

    private View.OnClickListener daftar_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dataPreference.setLogin("0");

            Intent intent = new Intent(DaftarActivity1.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
