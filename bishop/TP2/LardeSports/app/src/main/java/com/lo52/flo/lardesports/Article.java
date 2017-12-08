package com.lo52.flo.lardesports;

/**
 * Created by Flo on 23.10.2017.
 */

public class Article {

    private String ref;
    private Integer picture;
    private String marque;
    private Integer quantite;

    public Article(String ref, Integer picture, String marque, Integer quantite) {
        this.ref = ref;
        this.picture = picture;
        this.marque = marque;
        this.quantite = quantite;
    }

    public String getRef(){
        return ref;
    }

    public Integer getPicture() {
        return picture;
    }

    public String getMarque(){
        return marque;
    }

    public Integer getQuantite(){
        return quantite;
    }
}
