package fr.utbm.entity;

public class LotVolant {

    private long id;
    private int taille;
    private float prix;

    public LotVolant(int taille, float prix) {
        this.taille = taille;
        this.prix = prix;
    }

    public LotVolant(long id, int taille, float prix) {
        this.id = id;
        this.taille = taille;
        this.prix = prix;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return "LotVolant{" +
                "id=" + id +
                ", taille=" + taille +
                ", prix=" + prix +
                '}';
    }
}
