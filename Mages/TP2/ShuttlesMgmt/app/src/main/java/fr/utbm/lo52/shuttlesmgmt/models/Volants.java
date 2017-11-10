package fr.utbm.lo52.shuttlesmgmt.models;


/**
 * Created by ennajihihoussame on 04/10/2017.
 */

public class Volants {


    private int reference;
    private int quantite;
    private int id_stock;

    public Volants(int reference, int quantite, int id_stock) {
        this.reference = reference;
        this.quantite = quantite;
        this.id_stock = id_stock;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }
}