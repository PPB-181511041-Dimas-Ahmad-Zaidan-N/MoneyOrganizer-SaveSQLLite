package com.dimas.moneyorganizer;

import android.os.Parcel;
import android.os.Parcelable;

public class MoneyTrack implements Parcelable {
    private String hari;
    private String tanggal;
    private int pendapatan;
    private int pengeluaran;


    MoneyTrack(Parcel source) {
        hari = source.readString();
        tanggal = source.readString();
        pendapatan = source.readInt();
        pengeluaran = source.readInt();
    }

    public MoneyTrack(){

    }

    String getHari() { return hari; }

    void setHari(String hari) { this.hari = hari; }

    String getTanggal() { return tanggal; }

    void setTanggal(String tanggal) { this.tanggal = tanggal; }

    int getPendapatan() { return pendapatan; }

    void setPendapatan(int pendapatan) { this.pendapatan = pendapatan; }

    int getPengeluaran() { return pengeluaran; }

    void setPengeluaran(int pengeluaran) { this.pengeluaran = pengeluaran; }

    public static final Creator<MoneyTrack> CREATOR = new Creator<MoneyTrack>() {
        @Override
        public MoneyTrack createFromParcel(Parcel source) {
            return new MoneyTrack(source);
        }

        @Override
        public MoneyTrack[] newArray(int size) {
            return new MoneyTrack[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hari);
        dest.writeString(tanggal);
        dest.writeInt(pendapatan);
        dest.writeInt(pengeluaran);
    }
}
