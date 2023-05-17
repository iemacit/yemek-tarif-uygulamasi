package com.example.projedenemetasarm;

public class YemekListesi {
    private String yemekAdi;
    private String yemekPaylasan;
    private int yemekGorseli;
    private String yemekSuresi;
    private String kisiSayisi;

    public YemekListesi(String yemekAdi, String yemekPaylasan, int yemekGorseli,String yemekSuresi,String kisiSayisi) {
        this.yemekAdi = yemekAdi;
        this.yemekPaylasan = yemekPaylasan;
        this.yemekGorseli = yemekGorseli;
        this.kisiSayisi=kisiSayisi;
        this.yemekSuresi=yemekSuresi;
    }

    public String getYemekAdi() {
        return yemekAdi;
    }

    public void setYemekAdi(String yemekAdi) {
        this.yemekAdi = yemekAdi;
    }

    public String getYemekPaylasan() {
        return yemekPaylasan;
    }

    public void setYemekPaylasan(String yemekPaylasan) {
        this.yemekPaylasan = yemekPaylasan;
    }

    public int getYemekGorseli() {
        return yemekGorseli;
    }

    public void setYemekGorseli(int yemekGorseli) {
        this.yemekGorseli = yemekGorseli;
    }

    public String getYemekSuresi() {
        return yemekSuresi;
    }

    public void setYemekSuresi(String yemekSuresi) {
        this.yemekSuresi = yemekSuresi;
    }

    public String getKisiSayisi() {
        return kisiSayisi;
    }

    public void setKisiSayisi(String kisiSayisi) {
        this.kisiSayisi = kisiSayisi;
    }
}
