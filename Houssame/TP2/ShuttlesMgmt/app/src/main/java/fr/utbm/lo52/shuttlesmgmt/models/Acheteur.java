package fr.utbm.lo52.shuttlesmgmt.models;


/**
 * Created by ennajihihoussame on 04/10/2017.
 */

public class Acheteur {

    private int id_acheteur;


    public Acheteur(int id_acheteur) {
        this.id_acheteur = id_acheteur;
    }

    public int getId_acheteur() {
        return id_acheteur;
    }

    public void setId_acheteur(int id_acheteur) {
        this.id_acheteur = id_acheteur;
    }
}