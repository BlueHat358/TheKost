package com.example.thekost.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class history implements Parcelable {
    private String nama, status;
    private int id, harga, diterima, image;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getDiterima() {
        return diterima;
    }

    public void setDiterima(int diterima) {
        this.diterima = diterima;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.status);
        dest.writeInt(this.id);
        dest.writeInt(this.harga);
        dest.writeInt(this.diterima);
        dest.writeInt(this.image);
    }

    public history() {
    }

    protected history(Parcel in) {
        this.nama = in.readString();
        this.status = in.readString();
        this.id = in.readInt();
        this.harga = in.readInt();
        this.diterima = in.readInt();
        this.image = in.readInt();
    }

    public static final Creator<history> CREATOR = new Creator<history>() {
        @Override
        public history createFromParcel(Parcel source) {
            return new history(source);
        }

        @Override
        public history[] newArray(int size) {
            return new history[size];
        }
    };
}
