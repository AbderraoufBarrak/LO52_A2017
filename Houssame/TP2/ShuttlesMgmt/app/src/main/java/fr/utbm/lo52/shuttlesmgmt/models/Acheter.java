package fr.utbm.lo52.shuttlesmgmt.models;


/**
 * Created by ennajihihoussame on 04/10/2017.
 */

public class Acheter {


    private int reference;
    private int quantite;
    private int id_acheteur;
    private int quan;
    private int pai;

    public Acheter(int reference, int quantite, int id_acheteur, int quan, int pai) {
        this.reference = reference;
        this.quantite = quantite;
        this.id_acheteur = id_acheteur;
        this.quan = quan;
        this.pai = pai;
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

    public int getId_acheteur() {
        return id_acheteur;
    }

    public void setId_acheteur(int id_acheteur) {
        this.id_acheteur = id_acheteur;
    }

    public int getQuan() {
        return quan;
    }

    public void setQuan(int quan) {
        this.quan = quan;
    }

    public int getPai() {
        return pai;
    }

    public void setPai(int pai) {
        this.pai = pai;
    }
}