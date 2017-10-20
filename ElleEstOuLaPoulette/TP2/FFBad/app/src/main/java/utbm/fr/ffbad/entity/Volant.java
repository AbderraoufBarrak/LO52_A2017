package utbm.fr.ffbad.entity;

/**
 * Created by Clément on 20/10/2017.
 */

public class Volant {
    private String ref;
    private String marque;
    private String nom;
    private int classement;

    public Volant(String ref, String marque) {
        this.ref = ref;
        this.marque = marque;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getClassement() {
        return classement;
    }

    public void setClassement(int classement) {
        this.classement = classement;
    }
}
