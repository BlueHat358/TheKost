package com.example.thekost.Homepage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thekost.Adapter.HistoryAdapter;
import com.example.thekost.Model.history;
import com.example.thekost.R;
import com.example.thekost.Setting.SettingActivity;
import com.example.thekost.Utils.BtnRecyclerListener;
import com.example.thekost.Utils.MappingHelper;
import com.example.thekost.db.HistoryHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.example.thekost.Utils.PublicClassString.STATE;
import static com.example.thekost.db.DatabaseContract.HistoryColumn.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class PembayaranFragment extends Fragment implements LoadHistoryCallback {

    RecyclerView rvPembayaran;
    Button home;
    ImageView bg, setting;

    HistoryHelper historyHelper;

    FragmentTransaction fragmentTransaction;

    private ArrayList<history> list = new ArrayList<>();

    public PembayaranFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pembayaran, container, false);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        rvPembayaran = view.findViewById(R.id.rv_pembayaran);
        bg = view.findViewById(R.id.img_bg_pembayaran);
        setting = view.findViewById(R.id.btn_setting_pembayaran);
        home = view.findViewById(R.id.btn_home);

        home.setOnClickListener(homeClicked);
        setting.setOnClickListener(settingClicked);

        historyHelper = HistoryHelper.getINSTANCE(getActivity());
        //historyHelper.open();

        rvPembayaran.setHasFixedSize(true);

        bg.animate().translationY(-2800);

        new LoadHistoryAsync(historyHelper, this, getActivity()).execute();
        //historyHelper.close();

        return view;
    }

    private void showRecyclerList(final ArrayList<history> history_){
        rvPembayaran.setLayoutManager(new LinearLayoutManager(getActivity()));
        final HistoryAdapter historyAdapter = new HistoryAdapter(history_, new BtnRecyclerListener() {
            @Override
            public void onClick(View view, int position) {
                //historyHelper = HistoryHelper.getINSTANCE(getActivity());
                historyHelper.open();
                Log.d(STATE, "onClick: " + "Button Diterima is Clicked " + position);

                ContentValues values = new ContentValues();
                values.put(DITERIMA, 1);
                values.put(STATUS, "Telah Dibayar");
                int id = history_.get(position).getId();
                historyHelper.update(Integer.toString(id), values);
                historyHelper.close();

                Toast.makeText(getContext(), "Pesanan Telah Diterima", Toast.LENGTH_LONG).show();

                PembayaranFragment pembayaranFragment = new PembayaranFragment();
                fragmentTransaction.replace(R.id.container, pembayaranFragment,
                        PembayaranFragment.class.getSimpleName()).commit();

            }
        });
        rvPembayaran.setAdapter(historyAdapter);
    }

    @Override
    public void postExecute(ArrayList<history> history_) {
        showRecyclerList(history_);
    }

    private static class LoadHistoryAsync extends AsyncTask<Void, Void, ArrayList<history>>{

        private final WeakReference<HistoryHelper> weakHistoryHelper;
        private final WeakReference<LoadHistoryCallback> weakCallback;
        HistoryHelper historyHelper1;
        ArrayList<history> list;

        private LoadHistoryAsync(HistoryHelper historyHelper, LoadHistoryCallback callback, Context context) {
            weakHistoryHelper = new WeakReference<>(historyHelper);
            weakCallback = new WeakReference<>(callback);
            historyHelper1 = HistoryHelper.getINSTANCE(context);
            //historyHelper.open();
        }

        @Override
        protected ArrayList<history> doInBackground(Void... voids) {
            list = new ArrayList<>();
            historyHelper1.open();
            Cursor cursor = weakHistoryHelper.get().queryAll();
            list = MappingHelper.mapCursorToArrayList(cursor);
            historyHelper1.close();
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<history> history_) {
            super.onPostExecute(history_);
            weakCallback.get().postExecute(history_);
        }
    }

    private View.OnClickListener settingClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener homeClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            HomePageFragment fragment = new HomePageFragment();
            fragmentTransaction.replace(R.id.container, fragment,
                    HomePageFragment.class.getSimpleName()).commit();
        }
    };
}

interface LoadHistoryCallback {
    void postExecute(ArrayList<history> history_);
}
