package com.lo52.flo.lardesports;

/**
 * Created by Flo on 06.11.2017.
 */

public class MarqueBDD {

    private Integer id;
    private String libelle;

    public MarqueBDD(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
