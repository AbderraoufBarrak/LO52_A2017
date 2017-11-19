package io.hervenrv.shuttlesmgmt;

/**
 * Created by basile on 21/10/17.
 */

public class ListEntryAchats {
    private String marque;
    private String ref;
    private int imageId;
    private int prix;

    public ListEntryAchats(String marque, String ref, int prix, int imageId){
        this.marque = marque;
        this.ref = ref;
        this.prix = prix;
        this.imageId = imageId;
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

    public int getPrix(){
        return prix;
    }

    public void setPrix(int prix){
        this.prix = prix;
    }

    public  void setImage(int imageId){
        this.imageId = imageId;
    }

    public int getImage(){
        return imageId;
    }

}
