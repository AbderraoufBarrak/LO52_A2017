package fr.utbm.beans;

import java.util.Date;

/**
 * Created by Exige on 07/10/2017.
 */

public class AchatInfo {

    private long id;
    private boolean payed;
    private int quantité;
    private long lotId;
    private long acheteurMatricule;
    private long dateId;
    private java.util.Date dateDate;
    private String acheteurNom;
    private String acheteurPrénom;
    private String acheteurSociété;
    private int lotTaille;
    private float lotPrix;
    private String référence;
    private String marque;
    private String classement;

    public AchatInfo(boolean payed, int quantité, long lotId, long acheteurMatricule, long dateId, Date dateDate, String acheteurNom, String acheteurPrénom, String acheteurSociété, int lotTaille, float lotPrix, String référence, String marque, String classement) {
        this.payed = payed;
        this.quantité = quantité;
        this.lotId = lotId;
        this.acheteurMatricule = acheteurMatricule;
        this.dateId = dateId;
        this.dateDate = dateDate;
        this.acheteurNom = acheteurNom;
        this.acheteurPrénom = acheteurPrénom;
        this.acheteurSociété = acheteurSociété;
        this.lotTaille = lotTaille;
        this.lotPrix = lotPrix;
        this.référence = référence;
        this.marque = marque;
        this.classement = classement;
    }

    public AchatInfo(long id, boolean payed, int quantité, long lotId, long acheteurMatricule, long dateId, Date dateDate, String acheteurNom, String acheteurPrénom, String acheteurSociété, int lotTaille, float lotPrix, String référence, String marque, String classement) {
        this.id = id;
        this.payed = payed;
        this.quantité = quantité;
        this.lotId = lotId;
        this.acheteurMatricule = acheteurMatricule;
        this.dateId = dateId;
        this.dateDate = dateDate;
        this.acheteurNom = acheteurNom;
        this.acheteurPrénom = acheteurPrénom;
        this.acheteurSociété = acheteurSociété;
        this.lotTaille = lotTaille;
        this.lotPrix = lotPrix;
        this.référence = référence;
        this.marque = marque;
        this.classement = classement;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public long getLotId() {
        return lotId;
    }

    public void setLotId(long lotId) {
        this.lotId = lotId;
    }

    public long getAcheteurMatricule() {
        return acheteurMatricule;
    }

    public void setAcheteurMatricule(long acheteurMatricule) {
        this.acheteurMatricule = acheteurMatricule;
    }

    public long getDateId() {
        return dateId;
    }

    public void setDateId(long dateId) {
        this.dateId = dateId;
    }

    public Date getDateDate() {
        return dateDate;
    }

    public void setDateDate(Date dateDate) {
        this.dateDate = dateDate;
    }

    public String getAcheteurNom() {
        return acheteurNom;
    }

    public void setAcheteurNom(String acheteurNom) {
        this.acheteurNom = acheteurNom;
    }

    public String getAcheteurPrénom() {
        return acheteurPrénom;
    }

    public void setAcheteurPrénom(String acheteurPrénom) {
        this.acheteurPrénom = acheteurPrénom;
    }

    public String getAcheteurSociété() {
        return acheteurSociété;
    }

    public void setAcheteurSociété(String acheteurSociété) {
        this.acheteurSociété = acheteurSociété;
    }

    public int getLotTaille() {
        return lotTaille;
    }

    public void setLotTaille(int lotTaille) {
        this.lotTaille = lotTaille;
    }

    public float getLotPrix() {
        return lotPrix;
    }

    public void setLotPrix(float lotPrix) {
        this.lotPrix = lotPrix;
    }

    public String getRéférence() {
        return référence;
    }

    public void setRéférence(String référence) {
        this.référence = référence;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getClassement() {
        return classement;
    }

    public void setClassement(String classement) {
        this.classement = classement;
    }

    @Override
    public String toString() {
        return "AchatInfo{" +
                "id=" + id +
                ", payed=" + payed +
                ", quantité=" + quantité +
                ", lotId=" + lotId +
                ", acheteurMatricule=" + acheteurMatricule +
                ", dateId=" + dateId +
                ", dateDate=" + dateDate +
                ", acheteurNom='" + acheteurNom + '\'' +
                ", acheteurPrénom='" + acheteurPrénom + '\'' +
                ", acheteurSociété='" + acheteurSociété + '\'' +
                ", lotTaille=" + lotTaille +
                ", lotPrix=" + lotPrix +
                ", référence='" + référence + '\'' +
                ", marque='" + marque + '\'' +
                ", classement='" + classement + '\'' +
                '}';
    }
}
