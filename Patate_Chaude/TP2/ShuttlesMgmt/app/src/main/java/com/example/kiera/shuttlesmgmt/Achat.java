package com.example.kiera.shuttlesmgmt;


/**
 * Created by kiera on 29/09/2017.
 */

public class Achat {
    private String acheteur, ref;
    private int qtt;
    private double prix;
    private boolean paye;

    public Achat(String acheteur, String ref, int qtt, double prix, boolean paye) {
        this.acheteur = acheteur;
        this.ref = ref;
        this.qtt = qtt;
        this.prix = prix;
        this.paye = paye;
    }


    public String getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(String acheteur) {
        this.acheteur = acheteur;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public int getQtt() {
        return qtt;
    }

    public void setQtt(int qtt) {
        this.qtt = qtt;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public boolean isPaye() {
        return paye;
    }

    public void setPaye(boolean paye) {
        this.paye = paye;
    }
}
