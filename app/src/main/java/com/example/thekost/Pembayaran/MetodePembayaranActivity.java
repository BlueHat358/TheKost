package com.example.thekost.Pembayaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.thekost.Model.model;
import com.example.thekost.Model.tagihan_listrik;
import com.example.thekost.R;

import static com.example.thekost.Utils.PublicClassString.*;

public class MetodePembayaranActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
    private Bundle bundle;

    model m;
    tagihan_listrik tagihanListrik;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode_pembayaran);

        getSupportActionBar().hide();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        TopUpFragment fragment = new TopUpFragment();
        fragmentTransaction.replace(R.id.metode_activity, fragment,
                TopUpFragment.class.getSimpleName()).commit();

//        bundle = new Bundle();
//        m = new model();
//        tagihanListrik = new tagihan_listrik();
//
//        int key = getIntent().getExtras().getInt(EXTRA_KEY);
//        if(key == 0){
//            m = getIntent().getExtras().getParcelable(EXTRA_DETAIL_TEMP);
//            bundle.putParcelable(EXTRA_DETAIL_FRAGMENT, m);
//            bundle.putInt(EXTRA_KEY, 0);
//
//            Log.e(STATE, Integer.toString(m.getHarga()));
//        }else{
//            tagihanListrik = getIntent().getExtras().getParcelable(EXTRA_DETAIL_TEMP);
//            bundle.putParcelable(EXTRA_DETAIL_FRAGMENT, tagihanListrik);
//            bundle.putInt(EXTRA_KEY, 1);
//            Log.e(STATE, "listrik = " + tagihanListrik.getIdPelanggan());
//        }
//
//        MetodeFragment metodeFragment = new MetodeFragment();
//        metodeFragment.setArguments(bundle);
//        fragmentTransaction.replace(R.id.metode_activity, metodeFragment,
//                MetodeFragment.class.getSimpleName()).commit();

//        back = findViewById(R.id.content_back_metode);

//        back.setOnClickListener(backClicked);
    }

//    View.OnClickListener backClicked = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            onBackPressed();
//        }
//    };
}
