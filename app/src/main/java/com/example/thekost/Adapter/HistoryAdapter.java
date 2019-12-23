/*
package com.example.thekost.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.thekost.Model.history;
import com.example.thekost.R;
import com.example.thekost.Utils.BtnRecyclerListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private final ArrayList<history> list = new ArrayList<>();
    private final Context context;

    public HistoryAdapter(Context context){
        this.context = context;
    }

    public ArrayList<history> getListHistory(){
        return list;
    }

    public void setListHistory(ArrayList<history> history_){
        if(list.size() > 0){
            this.list.clear();
        }
        this.list.addAll(history_);

        notifyDataSetChanged();
    }

    public void addItem(int position, history history_){
        this.list.set(position, history_);
        notifyItemChanged(position, history_);
    }

    public void removeItem(int position){
        this.list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_pembayaran, parent, false);
        return new HistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        history history_ = list.get(position);

        Glide.with(holder.itemView.getContext())
                .load(history_.getImage())
                .into(holder.image);
        holder.nama.setText(history_.getNama());
        holder.harga.setText(history_.getHarga());
        holder.status.setText(history_.getStatus());

        if(history_.getDiterima() == 1){
            holder.diterima.setBackgroundResource(R.drawable.diterima_clicked);
            holder.diterima.setTextColor(R.color.color_black);
            holder.diterima.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvPembayaran;
        ImageView image;
        TextView nama, harga, status;
        Button diterima;

        WeakReference<BtnRecyclerListener> mListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rvPembayaran = itemView.findViewById(R.id.rv_pembayaran);
            image = itemView.findViewById(R.id.img_pembayaran);
            nama = itemView.findViewById(R.id.tv_nama_pembayaran);
            harga = itemView.findViewById(R.id.tv_harga_pembayaran);
            status = itemView.findViewById(R.id.tv_harga_pembayaran);
            diterima = itemView.findViewById(R.id.btn_diterima_pembayaran);

        }
    }
}*/

package com.example.thekost.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.thekost.Model.history;
import com.example.thekost.Model.model;
import com.example.thekost.R;
import com.example.thekost.Utils.BtnRecyclerListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.example.thekost.Utils.PublicClassString.STATE;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<history> history_;
    private BtnRecyclerListener listener;

    public HistoryAdapter(ArrayList<history> list, BtnRecyclerListener listener){
        this.history_ = list;
        this.listener = listener;
    }

    public HistoryAdapter(){
    }

    public void addItem(int position, history history_){
        this.history_.set(position, history_);
        notifyItemChanged(position, history_);
    }

    public void setListNotes(ArrayList<history> listNotes, BtnRecyclerListener listener) {

        if (listNotes.size() > 0) {
            this.history_.clear();
        }
        this.history_.addAll(listNotes);

        notifyDataSetChanged();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_pembayaran, parent, false);
        return new HistoryAdapter.ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        history list = history_.get(position);

        Log.d(STATE, "onBindViewHolder: " + list.getHarga());

        Glide.with(holder.itemView.getContext())
                .load(list.getImage())
                .into(holder.image);
        holder.nama.setText(list.getNama());
        holder.harga.setText("Rp. " + list.getHarga());
        holder.status.setText(list.getStatus());

        if(list.getDiterima() == 1){
            holder.diterima.setBackgroundResource(R.drawable.diterima_clicked);
            holder.diterima.setTextColor(R.color.color_black);
            holder.diterima.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return history_.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView rvPembayaran;
        ImageView image;
        TextView nama, harga, status;
        Button diterima;

        WeakReference<BtnRecyclerListener> mListener;
        public ViewHolder(@NonNull View itemView, BtnRecyclerListener listener) {
            super(itemView);

            rvPembayaran = itemView.findViewById(R.id.rv_pembayaran);
            image = itemView.findViewById(R.id.img_pembayaran);
            nama = itemView.findViewById(R.id.tv_nama_pembayaran);
            harga = itemView.findViewById(R.id.tv_harga_pembayaran_home);
            status = itemView.findViewById(R.id.tv_status_pembayaran);
            diterima = itemView.findViewById(R.id.btn_diterima_pembayaran);

            mListener = new WeakReference<>(listener);
            diterima.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.get().onClick(view, getAdapterPosition());
        }
    }
}

