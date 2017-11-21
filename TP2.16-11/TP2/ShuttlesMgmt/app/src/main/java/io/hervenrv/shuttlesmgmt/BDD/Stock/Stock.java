package io.hervenrv.shuttlesmgmt.BDD.Stock;

/**
 * Created by basile on 21/10/17.
 */

public class Stock {
    private String ref;
    private int quantite;

    public Stock(String ref, int quantite){
        this.ref = ref;
        this.quantite = quantite;
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

}
