package com.example.shuttlesmgmt;

/**
 * Created by Michel on 2017/10/2.
 */

public class Stock {
    private String marque, ref, quantite, image, prix;


    public Stock(String m, String r, String qtt, String icon, String prix){
        this.marque = m;
        this.ref = r;
        this.quantite = qtt;
        this.image = icon;
        this.prix = prix;
    }

    public String getPrix(){ return prix;}

    public void setPrix(String p){ this.prix = p;}

    public String getMarque(){
        return marque;
    }

    public String getRef(){
        return ref;
    }

    public String getQuantite(){
        return quantite;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String icon){
        this.image = icon;
    }

    public void setQuantite(String qtt){
        this.quantite = qtt;
    }

    public void setRef(String ref){
        this.ref = ref;
    }

    public void setMarque(String m){
        this.marque = m;
    }
}
