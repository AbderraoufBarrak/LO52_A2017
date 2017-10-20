package fr.utbm.lo52.taaroaffbad.Database;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Date;

import fr.utbm.lo52.taaroaffbad.Beans.Vente;
import fr.utbm.lo52.taaroaffbad.Beans.Volant;

/**
 * Created by Jordan on 27/09/2017.
 */

public class RemplissageBDD {

    public RemplissageBDD(Context context, boolean volant, boolean fabricant, boolean acheteur, boolean vente){

        // insert into VOLANT
        if(volant) {
            // paramètres :validité1, validité2, marque, référence, classement, prix
            Volant volantToAdd = new Volant("2016-2017", "2017-2018", "YONEX", "AS 30", 3, 27, 500);
            Volant volantToAdd_2 = new Volant("2016-2017", "2017-2018", "RSL", "RSL GRADE 1", 3, 21, 6000);
            Volant volantToAdd_3 = new Volant("2016-2017", "2017-2018", "RSL", "RSL GRADE 3", 3, 16.70, 5000);
            Volant volantToAdd_4 = new Volant("2016-2017", "2017-2018", "RSL", "RSL GRADE 9", 3, 13.70, 10000);

            VolantDAO volantsDAO = new VolantDAO(context);
            volantsDAO.open();

            volantsDAO.addVolant(volantToAdd);
            volantsDAO.addVolant(volantToAdd_2);
            volantsDAO.addVolant(volantToAdd_3);
            volantsDAO.addVolant(volantToAdd_4);
        }

        // insert into VENTE
        if(vente) {
            // paramètres : venteID(null), marque, ref, FabID, AchID, prix, boolPaye, qte, dateAchat, datePaye
            Vente venteToAdd = new Vente(1, "ARTENGO", "BSC 950", 2, 1, 190, true, 10, new Date(), new Date());
            Vente venteToAdd_2 = new Vente(1, "ASHAWAY", "A6", 1, 2, 330, true, 10, new Date(), new Date());
            Vente venteToAdd_3 = new Vente(1, "BABOLAT", "3", 3, 3, 150, true, 10, new Date(), new Date());

            Log.i("JOJO-venteToAdd","FabID="+venteToAdd.getFabricantId());

            VenteDAO venteDAO = new VenteDAO(context);
            venteDAO.open();
            venteDAO.addVente(venteToAdd);
            venteDAO.addVente(venteToAdd_2);
            venteDAO.addVente(venteToAdd_3);

            venteDAO.getVente(); // log
        }
    }
}
