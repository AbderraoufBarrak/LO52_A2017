package fr.utbm.entity;

/**
 * Created by Exige on 06/10/2017.
 */

public class Acheteur {

    private long matricule;
    private String nom;
    private String prénom;
    private String société;

    public Acheteur(String nom, String prénom, String société) {
        this.nom = nom;
        this.prénom = prénom;
        this.société = société;
    }

    public Acheteur(long matricule, String nom, String prénom, String société) {
        this.matricule = matricule;
        this.nom = nom;
        this.prénom = prénom;
        this.société = société;
    }

    public long getMatricule() {
        return matricule;
    }

    public void setMatricule(long matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public String getSociété() {
        return société;
    }

    public void setSociété(String société) {
        this.société = société;
    }

    @Override
    public String toString() {
        return "Acheteur{" +
                "matricule=" + matricule +
                ", nom='" + nom + '\'' +
                ", prénom='" + prénom + '\'' +
                ", société='" + société + '\'' +
                '}';
    }
}
