package com.example.thekost.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class AlfaIndoDetail implements Parcelable {
    String kode;
    int total;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kode);
        dest.writeInt(this.total);
    }

    public AlfaIndoDetail() {
    }

    protected AlfaIndoDetail(Parcel in) {
        this.kode = in.readString();
        this.total = in.readInt();
    }

    public static final Parcelable.Creator<AlfaIndoDetail> CREATOR = new Parcelable.Creator<AlfaIndoDetail>() {
        @Override
        public AlfaIndoDetail createFromParcel(Parcel source) {
            return new AlfaIndoDetail(source);
        }

        @Override
        public AlfaIndoDetail[] newArray(int size) {
            return new AlfaIndoDetail[size];
        }
    };
}
