package fr.utbm.beans;

/**
 * Created by Exige on 06/10/2017.
 */

public class LotInfo {

    private long id;
    private String ref;
    private String marque;
    private String classement;
    private int taille;
    private float prix;

    public LotInfo(long id, String marque, String ref, String classement, int taille, float prix) {
        this.id = id;
        this.ref = ref;
        this.marque = marque;
        this.classement = classement;
        this.taille = taille;
        this.prix = prix;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
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

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "LotInfo{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", marque='" + marque + '\'' +
                ", classement='" + classement + '\'' +
                ", taille=" + taille +
                ", prix=" + prix +
                '}';
    }
}
