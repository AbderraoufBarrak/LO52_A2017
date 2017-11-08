package com.example.shuttlesmgmt.entity.Version1;

/**
 * Created by Michel on 2017/10/7.
 */

public class Achat {
    private String marque;
    private String ref;
    private String quantite;
    private String image;
    private String prix;
    private String acheteur;
    private String payer;

    public Achat(String m, String r, String qtt, String prix, String icon, String a, String payer){
        this.marque = m;
        this.ref = r;
        this.quantite = qtt;
        this.prix = prix;
        this.image = icon;
        this.acheteur = a;
        this.payer = payer;
    }

    public String getPayer(){
        return payer;
    }

    public void setPayer(String p){
        this.payer = p;
    }

    public String getAcheteur(){
        return acheteur;
    }

    public void setAcheteur(String a){
        this.acheteur = a;
    }

    public String getPrix(){
        return prix;
    }

    public void setPrix(String prix){
        this.prix = prix;
    }

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
