package com.lo52.flo.lardesports;

/**
 * Created by Flo on 06.11.2017.
 */

/**
 * Classe permettant de décrire un objet Article de la BDD
 */
public class ArticleBDD {

    private Integer id;
    private String ref;
    private Integer marque_id;
    private Categorie cat;
    private Integer distrib_id;
    private Double prix;

    /**
     * Constructeur
     * @param id
     * @param ref
     * @param marque_id
     * @param cat
     * @param distrib_id
     * @param prix
     */
    public ArticleBDD(Integer id, String ref, Integer marque_id, Categorie cat, Integer distrib_id, Double prix) {
        this.id = id;
        this.ref = ref;
        this.marque_id = marque_id;
        this.cat = cat;
        this.distrib_id = distrib_id;
        this.prix = prix;
    }

    public String getRef() {
        return ref;
    }

    public Integer getMarque() {
        return marque_id;
    }

    public Categorie getCat(){
        return cat;
    }

    public Integer getDistributeur(){
        return distrib_id;
    }

    public Double getPrix(){
        return prix;
    }
}
