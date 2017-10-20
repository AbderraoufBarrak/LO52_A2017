package utbm.fr.ffbad.entity;

/**
 * Created by Clément on 20/10/2017.
 */

public class Tube {
    private int stock;
    private int prix;
    private String ref;
    private int nb_volant;
    private String image;

    public Tube(int stock, int prix, String ref, String image) {
        this.stock = stock;
        this.prix = prix;
        this.ref = ref;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrix() {
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

    public String getImage() {
        return image;
    }
}
