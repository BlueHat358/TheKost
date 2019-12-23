package com.example.thekost.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.thekost.Model.TentangKami;
import com.example.thekost.R;

import java.util.ArrayList;

public class TentangKamiAdapter extends RecyclerView.Adapter<TentangKamiAdapter.ViewHolder> {

    private ArrayList<TentangKami> list;

    public TentangKamiAdapter(ArrayList<TentangKami> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_tentang_kami, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TentangKami kami = list.get(position);

        Glide.with(holder.itemView.getContext())
                .load(kami.getImage())
                .into(holder.image);
        //Picasso.with(holder.itemView.getContext()).load(list.getImage()).into(holder.image);
        holder.tv_name.setText(kami.getNama());
        holder.tv_nim.setText(kami.getNim());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv_Content;
        ImageView image;
        TextView tv_name, tv_nim;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rv_Content = itemView.findViewById(R.id.rv_tentang_kami);
            image = itemView.findViewById(R.id.img_tentang_kami);
            tv_name = itemView.findViewById(R.id.tv_nama_tentang_kami);
            tv_nim = itemView.findViewById(R.id.tv_nim_tentang_kami);
        }
    }
}
