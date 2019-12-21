package com.example.thekost.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class model implements Parcelable {
    private String name, hargaString;
    private int image, harga;

    public String getHargaString() {
        return hargaString;
    }

    public void setHargaString(String hargaString) {
        this.hargaString = hargaString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public model() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.hargaString);
        dest.writeInt(this.image);
        dest.writeInt(this.harga);
    }

    protected model(Parcel in) {
        this.name = in.readString();
        this.hargaString = in.readString();
        this.image = in.readInt();
        this.harga = in.readInt();
    }

    public static final Creator<model> CREATOR = new Creator<model>() {
        @Override
        public model createFromParcel(Parcel source) {
            return new model(source);
        }

        @Override
        public model[] newArray(int size) {
            return new model[size];
        }
    };
}
