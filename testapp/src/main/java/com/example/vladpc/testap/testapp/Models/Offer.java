package com.example.vladpc.testap.testapp.Models;

public class Offer {
    private String titlu;
    private String descriere;
    private String pret;
    private int imagine;
    private int favorite;

    public Offer(String titlu, String descriere, String pret, int img, int favorite) {
        this.favorite = favorite;
        setTitlu(titlu);
        setDescriere(descriere);
        setPret(pret);
        setImagine(img);
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getPret() {
        return pret;
    }

    public void setPret(String pret) {
        this.pret = pret;
    }

    public int getImagine() {
        return imagine;
    }

    public void setImagine(int imagine) {
        this.imagine = imagine;
    }
}
