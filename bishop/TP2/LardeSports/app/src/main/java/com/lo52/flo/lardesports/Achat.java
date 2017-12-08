package com.lo52.flo.lardesports;

/**
 * Created by Flo on 23.10.2017.
 */

public class Achat {

    private String acheteur;
    private Integer quantite;
    private int volant_id;
    boolean paye;

    public Achat(String acheteur, Integer quantite, int volant_id, boolean paye){

        this.acheteur = acheteur;
        this.quantite = quantite;
        this.volant_id = volant_id;
        this.paye = paye;
    }

    public String getAcheteur(){
        return acheteur;
    }

    public Integer getQuantite(){
        return  quantite;
    }

    public int getVolantId(){
        return volant_id;
    }

    public boolean getPaye(){
        return paye;
    }
}
