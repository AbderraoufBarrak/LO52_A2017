package fr.utbm.lo52.taaroaffbad.Beans;

/**
 * Created by Moi on 22/09/2017.
 */

public class Acheteur {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String type;

    public Acheteur(int id, String nom, String prenom, String adresse, String tel, String type) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.type = type;
    }

    public Acheteur(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


}
