package io.hervenrv.shuttlesmgmt.BDD.Achat;

/**
 * Created by basile on 21/10/17.
 */

public class Achat {
    private long id;
    private String acheteur;
    private String ref;
    private int quantite;
    private boolean paye;
    private String date;

    public Achat(long id, String acheteur, String ref, int quantite, boolean paye, String date){
        this.acheteur = acheteur;
        this.date = date;
        this.id = id;
        this.ref = ref;
        this.quantite = quantite;
        this.paye = paye;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public  String getRef(){
        return ref;
    }

    public  void setRef(String ref){
        this.ref = ref;
    }

    public int getQuantite(){
        return quantite;
    }

    public void setQuantite(int stock){
        this.quantite = stock;
    }

    public boolean getPaye(){
        return paye;
    }

    public void setPaye(boolean paye) {
        this.paye = paye;
    }

    public String getAcheteur(){
        return acheteur;
    }

    public void setAcheteur(String acheteur){
        this.acheteur = acheteur;
    }
}
