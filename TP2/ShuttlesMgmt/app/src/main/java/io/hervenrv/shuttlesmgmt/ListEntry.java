package io.hervenrv.shuttlesmgmt;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by basile on 21/10/17.
 */

public class ListEntry {
    private String marque;
    private String ref;
    private int imageId;
    private int stock;

    public ListEntry(String marque, String ref, int stock, int imageId){
        this.marque = marque;
        this.ref = ref;
        this.stock = stock;
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

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public  void setImage(int imageId){
        this.imageId = imageId;
    }

    public int getImage(){
        return imageId;
    }

}
