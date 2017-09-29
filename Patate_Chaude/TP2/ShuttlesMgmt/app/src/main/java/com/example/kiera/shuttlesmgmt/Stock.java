package com.example.kiera.shuttlesmgmt;

import android.widget.ImageView;

/**
 * Created by kiera on 29/09/2017.
 */

public class Stock {
    private int image;
    private String marque;
    private String ref;
    private int qtt;
    private double prix;

    public Stock(int i, String mrq, String rf, int qt, double pr){
        image=i; marque=mrq; ref=rf; qtt=qt; prix=pr;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
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
}
