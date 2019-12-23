package com.example.thekost.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class TentangKami implements Parcelable {
    String nama, nim;
    int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public TentangKami() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.nim);
        dest.writeInt(this.image);
    }

    protected TentangKami(Parcel in) {
        this.nama = in.readString();
        this.nim = in.readString();
        this.image = in.readInt();
    }

    public static final Creator<TentangKami> CREATOR = new Creator<TentangKami>() {
        @Override
        public TentangKami createFromParcel(Parcel source) {
            return new TentangKami(source);
        }

        @Override
        public TentangKami[] newArray(int size) {
            return new TentangKami[size];
        }
    };
}
