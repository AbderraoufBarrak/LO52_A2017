package com.lo52.flo.lardesports;

/**
 * Created by Flo on 27.10.2017.
 */

/**
 * Enum permettant d'identifer les catégories de volants
 */
public enum Categorie {

    Elite,
    Standard;

    /**
     * Conversion de l'enum en string
     * @param cat
     * @return
     */
    public static String catToString(Categorie cat){
        switch (cat){

            case Elite :
                return "Elite";
            case Standard :
                return "Standard";
            default :
                return "Catégorie inconnu";
        }
    }

    /**
     * Conversion de string en enum Categorie
     * @param cat
     * @return
     */
    public static Categorie stringToCat(String cat){
        switch (cat){

            case "Standard" :
                return Standard;
            case "Elite" :
                return Elite;
            default :
                return null;
        }
    }
}
