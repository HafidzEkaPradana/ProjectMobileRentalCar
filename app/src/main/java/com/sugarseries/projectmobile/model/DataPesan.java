package com.sugarseries.projectmobile.model;

public class DataPesan {
    String id, alamat, harga, selesai, sewa;

    public DataPesan() {
    }
    public DataPesan(String alamat, String harga, String selesai, String sewa) {
        this.alamat = alamat;
        this.harga = harga;
        this.selesai = selesai;
        this.sewa = sewa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getSewa() {
        return sewa;
    }

    public void setSewa(String sewa) {
        this.sewa = sewa;
    }

    public String getSelesai() {
        return selesai;
    }

    public void setSelesai(String selesai) {
        this.selesai = selesai;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
