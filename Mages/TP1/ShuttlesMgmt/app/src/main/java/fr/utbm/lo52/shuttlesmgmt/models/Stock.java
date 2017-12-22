package fr.utbm.lo52.shuttlesmgmt.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by sbouaala on 30/09/2017
 */
public class Stock {
    private int image;
    private String marque;
    private String reference;
    private int quantite;
    private double prix;

    public Stock(int image, String marque, String reference, int quantite, double prix){
        image=image;
        marque=marque;
        reference=reference;
        quantite=quantite;
        prix= prix;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String ref) {
        this.reference = reference;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantitet(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}