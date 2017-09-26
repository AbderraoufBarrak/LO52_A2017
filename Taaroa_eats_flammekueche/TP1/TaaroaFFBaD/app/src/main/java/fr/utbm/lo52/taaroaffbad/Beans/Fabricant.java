package fr.utbm.lo52.taaroaffbad.Beans;

public class Fabricant {
    private int id;
    private String nom;
    private String adresse;
    private String contact;
    private String tel;
    private String mail;

    public Fabricant(int id, String nom, String adresse, String contact, String tel, String mail) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
        this.tel = tel;
        this.mail = mail;
    }

    public Fabricant(){}

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
