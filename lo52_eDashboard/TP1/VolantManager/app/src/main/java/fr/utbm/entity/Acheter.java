package fr.utbm.entity;

/**
 * Created by Exige on 06/10/2017.
 */

public class Acheter {

    private long id;
    private long lot_id;
    private long date_id;
    private long acheteur_matricule;
    private int quantité;
    private boolean payed;

    public Acheter(long lot_id, long date_id, long acheteur_matricule, int quantité, boolean payed) {
        this.lot_id = lot_id;
        this.date_id = date_id;
        this.acheteur_matricule = acheteur_matricule;
        this.quantité = quantité;
        this.payed = payed;
    }

    public Acheter(long id, long lot_id, long date_id, long acheteur_matricule, int quantité, boolean payed) {
        this.id = id;
        this.lot_id = lot_id;
        this.date_id = date_id;
        this.acheteur_matricule = acheteur_matricule;
        this.quantité = quantité;
        this.payed = payed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLot_id() {
        return lot_id;
    }

    public void setLot_id(long lot_id) {
        this.lot_id = lot_id;
    }

    public long getDate_id() {
        return date_id;
    }

    public void setDate_id(long date_id) {
        this.date_id = date_id;
    }

    public long getAcheteur_matricule() {
        return acheteur_matricule;
    }

    public void setAcheteur_matricule(long acheteur_matricule) {
        this.acheteur_matricule = acheteur_matricule;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    @Override
    public String toString() {
        return "Acheter{" +
                "id=" + id +
                ", lot_id=" + lot_id +
                ", date_id=" + date_id +
                ", acheteur_matricule=" + acheteur_matricule +
                ", quantité=" + quantité +
                ", payed=" + payed +
                '}';
    }
}
