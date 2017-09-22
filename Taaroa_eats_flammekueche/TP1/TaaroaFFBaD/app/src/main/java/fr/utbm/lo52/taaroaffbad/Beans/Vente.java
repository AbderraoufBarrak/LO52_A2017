package fr.utbm.lo52.taaroaffbad.Beans;

import java.util.Date;

/**
 * Created by Moi on 22/09/2017.
 */

public class Vente {
    private int fabricantId;
    private String marque;
    private String reference;
    private int acheteurId;
    private int prix;
    private Boolean paye;
    private Date dateAchat;
    private Date datePaye;

    public Vente(int fabricantId, String marque, String reference, int acheteurId, int prix, Boolean paye, Date dateAchat, Date datePaye) {
        this.fabricantId = fabricantId;
        this.marque = marque;
        this.reference = reference;
        this.acheteurId = acheteurId;
        this.prix = prix;
        this.paye = paye;
        this.dateAchat = dateAchat;
        this.datePaye = datePaye;
    }

    public int getFabricantId() {
        return fabricantId;
    }

    public void setFabricantId(int fabricantId) {
        this.fabricantId = fabricantId;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getAcheteurId() {
        return acheteurId;
    }

    public void setAcheteurId(int acheteurId) {
        this.acheteurId = acheteurId;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Boolean getPaye() {
        return paye;
    }

    public void setPaye(Boolean paye) {
        this.paye = paye;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Date getDatePaye() {
        return datePaye;
    }

    public void setDatePaye(Date datePaye) {
        this.datePaye = datePaye;
    }
}
