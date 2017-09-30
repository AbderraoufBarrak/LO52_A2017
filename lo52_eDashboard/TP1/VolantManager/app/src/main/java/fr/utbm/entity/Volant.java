package fr.utbm.entity;

/**
 * Created by Exige on 23/09/2017.
 */

public class Volant {

    private long id;
    private String marque;
    private String ref;
    private String classement;

    public Volant(long id, String marque, String ref, String classement) {
        this.id = id;
        this.marque = marque;
        this.ref = ref;
        this.classement = classement;
    }

    public Volant(String marque, String ref, String classement) {
        this.marque = marque;
        this.ref = ref;
        this.classement = classement;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getClassement() {
        return classement;
    }

    public void setClassement(String classement) {
        this.classement = classement;
    }

    @Override
    public String toString() {
        return "Volant{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", ref='" + ref + '\'' +
                ", classement=" + classement +
                '}';
    }
}
