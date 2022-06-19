package com.sugarseries.projectmobile.model;

public class DataPesan {
    String id, alamat, sewa, selesai, harga;

    public DataPesan(String id, String alamat, String tglSewa, String tglSelesai, String harga) {

    }

    public DataPesan(String alamat, String sewa, String selesai, String harga) {

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
