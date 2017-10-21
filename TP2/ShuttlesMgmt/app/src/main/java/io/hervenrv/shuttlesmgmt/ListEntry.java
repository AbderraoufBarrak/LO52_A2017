package io.hervenrv.shuttlesmgmt;

/**
 * Created by basile on 21/10/17.
 */

public class ListEntry {
    private String marque;
    private String ref;
    private int stock;

    public ListEntry(String marque, String ref, int stock){
        this.marque = marque;
        this.ref = ref;
        this.stock = stock;
    }

    public String getMarque(){
        return marque;
    }

    public void setMarque(String marque){
        this.marque = marque;
    }

    public  String getRef(){
        return ref;
    }

    public  void setRef(String ref){
        this.ref = ref;
    }

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

}
