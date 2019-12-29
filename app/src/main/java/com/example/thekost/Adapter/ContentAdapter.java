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
import com.example.thekost.Model.model;
import com.example.thekost.R;
import com.example.thekost.Utils.BtnRecyclerListener;
import com.example.thekost.Utils.getFormatRupiah;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ListViewHolder> {

    private ArrayList<model> model_;
    private Context context;
    private BtnRecyclerListener listener;
    getFormatRupiah formatRupiah;

    public ContentAdapter(ArrayList<model> list, BtnRecyclerListener listener){
        this.model_ = list;
        this.listener = listener;
        formatRupiah = new getFormatRupiah();
    }

    @NonNull
    @Override
    public ContentAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list, parent, false);
        return new ListViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        model list = model_.get(position);

        Glide.with(holder.itemView.getContext())
                .load(list.getImage())
                .into(holder.image);
        //Picasso.with(holder.itemView.getContext()).load(list.getImage()).into(holder.image);
        holder.tv_name.setText(list.getName());
        holder.btn_Content.setText(formatRupiah.getFormat(list.getHarga()));
    }

    @Override
    public int getItemCount() {
        return model_.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RecyclerView rv_Content;
        ImageView image;
        TextView tv_name;
        Button btn_Content;

        WeakReference<BtnRecyclerListener> mListener;

        public ListViewHolder(@NonNull View itemView, BtnRecyclerListener listener) {
            super(itemView);

            rv_Content = itemView.findViewById(R.id.rv_content);
            image = itemView.findViewById(R.id.img_content);
            tv_name = itemView.findViewById(R.id.tv_content);
            btn_Content = itemView.findViewById(R.id.btn_content);
            mListener = new WeakReference<>(listener);

            btn_Content.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            mListener.get().onClick(view, getAdapterPosition());
        }
    }
}
