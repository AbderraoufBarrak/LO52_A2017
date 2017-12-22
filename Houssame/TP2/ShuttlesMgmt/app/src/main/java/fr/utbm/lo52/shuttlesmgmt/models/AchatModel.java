package fr.utbm.lo52.shuttlesmgmt.models;

/**
 * Created by ennajihihoussame on 08/11/2017.
 */

public class AchatModel {
    private String acheteur;
    private String prix;
    private String quantite;
    private String reference;
    private Boolean payer;

    public Boolean getPayer() {
        return payer;
    }

    public void setPayer(Boolean payer) {
        this.payer = payer;
    }

    public AchatModel(String acheteur, String prix, String quantite, String reference, Boolean payer) {
        this.acheteur = acheteur;
        this.prix = prix;
        this.quantite = quantite;
        this.reference = reference;
        this.payer = payer;
    }

    public AchatModel(String acheteur, String prix, String quantite, String reference) {
        this.acheteur = acheteur;
        this.prix = prix;
        this.quantite = quantite;
        this.reference = reference;
    }

    public String getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(String acheteur) {
        this.acheteur = acheteur;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

}
