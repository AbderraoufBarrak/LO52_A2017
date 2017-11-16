package utbm.fr.ffbad.entity;

import android.media.Image;
import android.support.v4.content.ContextCompat;

import utbm.fr.ffbad.R;

/**
 * Created by Clément on 20/10/2017.
 */

public class Tube {
    private int stock;
    private double prix;
    private String ref;
    private int nb_volant;
    private String imageName;

    public Tube(int stock, int prix, String ref, String imageName) {
        this.stock = stock;
        this.prix = prix;
        this.ref = ref;
        this.imageName = imageName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public int getNb_volant() {
        return nb_volant;
    }

    public void setNb_volant(int nb_volant) {
        this.nb_volant = nb_volant;
    }

    public String getImageName() {
        return imageName;
    }
}
