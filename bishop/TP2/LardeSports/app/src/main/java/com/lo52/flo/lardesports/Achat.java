package com.lo52.flo.lardesports;

/**
 * Created by Flo on 23.10.2017.
 */

/**
 * Classe Achat
 * Elle représente un objet Achat qui est affiché dans la liste des achats
 */
public class Achat {

    /**
     * Attributs
     */
    private String acheteur;
    private Integer quantite;
    private int volant_id;
    boolean paye;

    /**
     * Constructeur de la classe
     * @param acheteur
     * @param quantite
     * @param volant_id
     * @param paye
     */
    public Achat(String acheteur, Integer quantite, int volant_id, boolean paye){

        this.acheteur = acheteur;
        this.quantite = quantite;
        this.volant_id = volant_id;
        this.paye = paye;
    }

    /**
     * Getter permettant de récupérer l'attribut acheteur
     * @return acheteur
     */
    public String getAcheteur(){
        return acheteur;
    }

    /**
     * Getter permettant de récupérer l'attribut quantite
     * @return quantite
     */
    public Integer getQuantite(){
        return  quantite;
    }

    /**
     * Getter permettant de récupérer l'attribut id du volant
     * @return volant_id
     */
    public int getVolantId(){
        return volant_id;
    }

    /**
     * Getter permettant de récupérer l'attribut paye
     * @return paye
     */
    public boolean getPaye(){
        return paye;
    }
}
