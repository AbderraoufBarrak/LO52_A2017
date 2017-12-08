package com.lo52.flo.lardesports;

/**
 * Created by Flo on 06.11.2017.
 */

/**
 * Classe permettant de décrire un objet distributeur de la base de données
 */
public class DistributeurBDD {

    private Integer id;
    private String name;
    private String adress;
    private String contact;
    private String tel;
    private String email;

    /**
     * Constructeur
     * @param id
     * @param namem
     * @param adress
     * @param contact
     * @param tel
     * @param email
     */
    public DistributeurBDD(Integer id, String namem, String adress, String contact, String tel, String email) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.contact = contact;
        this.tel = tel;
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getContact() {
        return contact;
    }

    public String getTel(){
        return tel;
    }

    public String getEmail(){
        return email;
    }
}
