package com.example.thekost.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class model implements Parcelable {
    private String name, harga;
    private int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
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
        dest.writeString(this.name);
        dest.writeString(this.harga);
        dest.writeInt(this.image);
    }

    public model() {
    }

    protected model(Parcel in) {
        this.name = in.readString();
        this.harga = in.readString();
        this.image = in.readInt();
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
