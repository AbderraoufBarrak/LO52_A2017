package io.hervenrv.shuttlesmgmt.BDD.Produit;

/**
 * Created by basile on 21/10/17.
 */

public class Produit {
    private String ref;
    private String marque;
    private double prix;
    private String nom;
    private int imageID;

    public Produit(String ref, String marque, double prix, String nom, int imageID){
        this.ref = ref;
        this.marque = marque;
        this.prix = prix;
        this.nom = nom;
        this.imageID = imageID;
    }

    public String getRef(){
        return ref;
    }

    public void setRef(String ref){
        this.ref = ref;
    }

    public String getMarque(){
        return marque;
    }

    public void setMarque(String marque){
        this.marque = marque;
    }

    public double getPrix(){
        return prix;
    }

    public void setPrix(double prix){
        this.prix = prix;
    }

    public String getNom(){
        return nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public int getImageID(){
        return this.imageID;
    }

    public void setImageID(int imageID){
        this.imageID = imageID;
    }

}
