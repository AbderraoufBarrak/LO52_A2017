package fr.utbm.lo52.taaroaffbad.Beans;

import java.io.Serializable;

/**
 * Created by Moi on 22/09/2017.
 */

public class Volant implements Serializable{
    private String validite_1;
    private String validite_2;
    private String marque;
    private String reference;
    private int classement;

    public Volant(String validite_1, String validite_2, String marque, String reference, int classement) {
        this.validite_1 = validite_1;
        this.validite_2 = validite_2;
        this.marque = marque;
        this.reference = reference;
        this.classement = classement;
    }

    public Volant(){}

    public String getValidite_1() {
        return validite_1;
    }

    public void setValidite_1(String validite_1) {
        this.validite_1 = validite_1;
    }

    public String getValidite_2() {
        return validite_2;
    }

    public void setValidite_2(String validite_2) {
        this.validite_2 = validite_2;
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

    public int getClassement() {
        return classement;
    }

    public void setClassement(int classement) {
        this.classement = classement;
    }

    @Override
    public String toString() {
        return "Volant{" +
                "validite_1='" + validite_1 + '\'' +
                ", validite_2='" + validite_2 + '\'' +
                ", marque='" + marque + '\'' +
                ", reference='" + reference + '\'' +
                ", classement=" + classement +
                '}';
    }
}
