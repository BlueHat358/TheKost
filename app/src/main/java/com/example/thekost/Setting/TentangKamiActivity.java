package com.example.thekost.Setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.thekost.Adapter.ContentAdapter;
import com.example.thekost.Adapter.TentangKamiAdapter;
import com.example.thekost.Model.TentangKami;
import com.example.thekost.R;

import java.util.ArrayList;

import static com.example.thekost.Utils.PublicClassString.STATE;

public class TentangKamiActivity extends AppCompatActivity {

    private RecyclerView rvContent;
    private ArrayList<TentangKami> list = new ArrayList<>();

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_kami);

        this.getSupportActionBar().hide();

        rvContent = findViewById(R.id.rv_tentang_kami);
        back = findViewById(R.id.content_back_tentangkami);

        rvContent.setHasFixedSize(true);

        list.addAll(getListContent());

        showRecyclerList();

        back.setOnClickListener(backClicked);
    }

    public ArrayList<TentangKami> getListContent(){
        String[] nama = {
                "M. Helmi Habibi",
                "Vika Arya Prasetya",
                "Fajar Muhammad Sidiq",
                "Kurniawan Dwi Waestaputra",
                "Ilham Nur Rohman"
        };
        String[] nim = {
                "18.11.1891",
                "18.11.1892",
                "18.11.1895",
                "18.11.1896",
                "18.11.1903"
        };
        int[] image = {
                R.drawable.helmi,
                R.drawable.vika,
                R.drawable.fajar,
                R.drawable.nawan,
                R.drawable.ilham
        };

        ArrayList<TentangKami> modelList = new ArrayList<>();
        for(int i = 0; i < nama.length; i++){
            TentangKami model_ = new TentangKami();
            model_.setNama(nama[i]);
            model_.setNim(nim[i]);
            model_.setImage(image[i]);

            modelList.add(model_);
        }
        return modelList;
    }

    private void showRecyclerList(){
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        TentangKamiAdapter kamiAdapter = new TentangKamiAdapter(list);
        rvContent.setAdapter(kamiAdapter);
    }

    private View.OnClickListener backClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
            Log.e(STATE, "onClick: ");
        }
    };
}
