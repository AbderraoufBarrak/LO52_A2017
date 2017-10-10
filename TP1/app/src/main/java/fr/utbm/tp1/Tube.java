package fr.utbm.tp1;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tube")
public class Tube {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "marque")
    private String marque;

    @ColumnInfo(name = "reference")
    private String reference;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "classement")
    private int classement;

    @ColumnInfo(name = "prix_user")
    private int prix_user;

    @ColumnInfo(name = "prix_club")
    private int prix_club;

    @ColumnInfo(name = "stock")
    private int stock;

    public long getId() {
        return id;
    }

    public String getMarque() {
        return marque;
    }

    public String getReference() {
        return reference;
    }

    public String getDescription() {
        return description;
    }

    public int getClassement() {
        return classement;
    }

    public int getPrix_user() {
        return prix_user;
    }

    public int getPrix_club() {
        return prix_club;
    }

    public int getStock() {
        return stock;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClassement(int classement) {
        this.classement = classement;
    }

    public void setPrix_user(int prix_user) {
        this.prix_user = prix_user;
    }

    public void setPrix_club(int prix_club) {
        this.prix_club = prix_club;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
