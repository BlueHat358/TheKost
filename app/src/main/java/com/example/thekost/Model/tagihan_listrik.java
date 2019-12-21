package com.example.thekost.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class tagihan_listrik implements Parcelable {
    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    private int Image;

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(String idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDaya() {
        return daya;
    }

    public void setDaya(String daya) {
        this.daya = daya;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStandmeter() {
        return standmeter;
    }

    public void setStandmeter(String standmeter) {
        this.standmeter = standmeter;
    }

    public String getNoRef() {
        return noRef;
    }

    public void setNoRef(String noRef) {
        this.noRef = noRef;
    }

    public int getDenda() {
        return denda;
    }

    public void setDenda(int denda) {
        this.denda = denda;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private String idPelanggan, nama, daya, time, standmeter, noRef;
    private int denda, total;

    public tagihan_listrik() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Image);
        dest.writeString(this.idPelanggan);
        dest.writeString(this.nama);
        dest.writeString(this.daya);
        dest.writeString(this.time);
        dest.writeString(this.standmeter);
        dest.writeString(this.noRef);
        dest.writeInt(this.denda);
        dest.writeInt(this.total);
    }

    protected tagihan_listrik(Parcel in) {
        this.Image = in.readInt();
        this.idPelanggan = in.readString();
        this.nama = in.readString();
        this.daya = in.readString();
        this.time = in.readString();
        this.standmeter = in.readString();
        this.noRef = in.readString();
        this.denda = in.readInt();
        this.total = in.readInt();
    }

    public static final Creator<tagihan_listrik> CREATOR = new Creator<tagihan_listrik>() {
        @Override
        public tagihan_listrik createFromParcel(Parcel source) {
            return new tagihan_listrik(source);
        }

        @Override
        public tagihan_listrik[] newArray(int size) {
            return new tagihan_listrik[size];
        }
    };
}
