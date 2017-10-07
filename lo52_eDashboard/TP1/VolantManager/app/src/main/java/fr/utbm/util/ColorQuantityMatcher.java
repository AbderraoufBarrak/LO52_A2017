package fr.utbm.util;

import fr.utbm.volantmanager.R;

/**
 * Created by Exige on 07/10/2017.
 */

public class ColorQuantityMatcher {

    public ColorQuantityMatcher() {

    }

    public static int colorAdapter(int taille) {
        int color = 0;
        if(taille > 0 && taille <= 500) {
            color = R.color.lot_50;
        } else if (taille > 500 && taille <= 5000){
            color = R.color.lot_100;
        } else {
            color = R.color.lot_150;
        }
        return color;
    }
}
