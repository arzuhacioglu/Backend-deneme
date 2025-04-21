package org.example.yonetici;

import jakarta.persistence.*;

@Entity //Javadaki herhangi bir classı veritabanında tablo haline getirirken kullanılur
public class Calisan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String kullanici_adi;
    private String kullanici_sifre;
    private String gorev;
    private double maas;


    public Calisan() {}//Springde nesneler oluştururken constructor parametresiz yazılır

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }

    public String getKullanici_sifre() {
        return kullanici_sifre;
    }

    public void setKullanici_sifre(String kullanici_sifre) {
        this.kullanici_sifre = kullanici_sifre;
    }

    public String getGorev() {
        return gorev;
    }

    public void setGorev(String gorev) {
        this.gorev = gorev;
    }

    public double getMaas() {
        return maas;
    }

    public void setMaas(double maas) {
        this.maas = maas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
