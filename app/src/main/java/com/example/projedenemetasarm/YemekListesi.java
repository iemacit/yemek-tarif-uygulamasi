package com.example.projedenemetasarm;

public class YemekListesi {
    private String yemekAdi;
    private String yemekPaylasan;
    private String yemekGorseli;
    private String yemekSuresi;
    private String kisiSayisi;
    private String yemekAciklama;

    public YemekListesi(String yemekAdi, String yemekPaylasan, String yemekGorseli,String yemekSuresi,String kisiSayisi,String yemekAciklama) {
        this.yemekAdi = yemekAdi;
        this.yemekAciklama=yemekAciklama;
        this.yemekPaylasan = yemekPaylasan;
        this.yemekGorseli = yemekGorseli;
        this.kisiSayisi=kisiSayisi;
        this.yemekSuresi=yemekSuresi;
    }

    public String getYemekAciklama() {
        return yemekAciklama;
    }

    public void setYemekAciklama(String yemekAciklama) {
        this.yemekAciklama = yemekAciklama;
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

    public String getYemekGorseli() {
        return yemekGorseli;
    }

    public void setYemekGorseli(String yemekGorseli) {
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
