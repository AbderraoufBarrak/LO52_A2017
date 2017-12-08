package com.lo52.flo.lardesports;

/**
 * Created by Flo on 23.10.2017.
 */

/**
 * Classe Article
 * Elle représente un objet Article qui est affiché dans la liste des articles (stock)
 */
public class Article {

    private String ref;
    private Integer picture;
    private String marque;
    private Integer quantite;

    /**
     * Constructeur de la classe
     * @param ref
     * @param picture
     * @param marque
     * @param quantite
     */
    public Article(String ref, Integer picture, String marque, Integer quantite) {
        this.ref = ref;
        this.picture = picture;
        this.marque = marque;
        this.quantite = quantite;
    }

    /**
     * Fonction permettant de renvoyer l'attribut reference
     * @return ref
     */
    public String getRef(){
        return ref;
    }

    /**
     * Fonction permettant de renvoyer une chaîne correspondant à l'image du produit
     * @return picture
     */
    public Integer getPicture() {
        return picture;
    }

    /**
     * Fonction permettant de renvoyer la marque du produit
     * @return marque
     */
    public String getMarque(){
        return marque;
    }

    /**
     * Fonction permettant de renvoyer la quantité de ce produit disponible en stock
     * @return quantite
     */
    public Integer getQuantite(){
        return quantite;
    }
}
