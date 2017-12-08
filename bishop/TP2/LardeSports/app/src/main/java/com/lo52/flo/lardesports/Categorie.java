package com.lo52.flo.lardesports;

/**
 * Created by Flo on 27.10.2017.
 */

public enum Categorie {

    Elite,
    Standard;

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
