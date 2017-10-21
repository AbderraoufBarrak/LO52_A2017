package fr.utbm.volantmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.utbm.DAO.AcheterDAO;
import fr.utbm.DAO.AcheteurDAO;
import fr.utbm.DAO.DateDAO;
import fr.utbm.DAO.LotVolantDAO;
import fr.utbm.DAO.VolantsDAO;
import fr.utbm.beans.AchatInfo;
import fr.utbm.beans.LotInfo;
import fr.utbm.entity.Acheter;
import fr.utbm.entity.Acheteur;
import fr.utbm.entity.Date;
import fr.utbm.util.CustomAdapter;
import fr.utbm.volantmanager.R;

public class PurchaseItem extends AppCompatActivity {

    private CustomAdapter adapter;
    private List<LotInfo> myLotInfos = new ArrayList<>();
    //private TextView quantity;
    private TextView totalPrice;
    private VolantsDAO volantsDAO;
    private LotVolantDAO lotVolantDAO;
    private TextView acheteurName;
    private TextView acheteurFamily;
    private TextView acheteurSociety;
    private CheckBox payed;
    private Intent intent;
    private EditText quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_item);

        intent = getIntent();

        Spinner volantsSP = (Spinner) findViewById(R.id.volants_sp);
        totalPrice = (TextView) findViewById(R.id.total_price_tv);
        //quantity = (TextView) findViewById(R.id.quantity_tv);
        acheteurName = (TextView) findViewById(R.id.acheteur_name_et);
        acheteurFamily = (TextView) findViewById(R.id.acheteur_family_et);
        acheteurSociety = (TextView) findViewById(R.id.acheteur_society_et);
        payed = (CheckBox) findViewById(R.id.payed_box);
        final CheckBox payed = (CheckBox) findViewById(R.id.payed_box);
        final Button plusQuantity = (Button) findViewById(R.id.plus_b);
        final Button minusQuantity = (Button) findViewById(R.id.minus_b);
        final Button updatePayedButton = (Button) findViewById(R.id.updatePayed_b);
        final Button valideButton = (Button) findViewById(R.id.validate_b);
        quantity = (EditText) findViewById(R.id.quantity_et);


        volantsDAO = new VolantsDAO(PurchaseItem.this);
        lotVolantDAO = new LotVolantDAO(PurchaseItem.this);

        volantsDAO.open();
        lotVolantDAO.open();

        myLotInfos = lotVolantDAO.getTestVolants(volantsDAO);

        adapter = new CustomAdapter(this, myLotInfos);
        volantsSP.setAdapter(adapter);

        if(intent.getLongExtra("achatInfoID", -1) != -1) {
            Log.d("eDBTEAM/PurchaseItem", "Récupération des données...");
            long id = intent.getLongExtra("achatInfoID", -1);

            DateDAO dateDAO = new DateDAO(this);
            AcheteurDAO acheteurDAO = new AcheteurDAO(this);
            AcheterDAO acheterDAO = new AcheterDAO(this);

            dateDAO.open();
            acheteurDAO.open();
            acheterDAO.open();
            AchatInfo retrievedInfos = acheterDAO.getAchatInfo(id, volantsDAO, lotVolantDAO, dateDAO, acheteurDAO);

            acheteurName.setText(retrievedInfos.getAcheteurNom());
            acheteurName.setFocusable(false);
            acheteurFamily.setText(retrievedInfos.getAcheteurPrénom());
            acheteurFamily.setFocusable(false);
            acheteurSociety.setText(retrievedInfos.getAcheteurSociété());
            acheteurSociety.setFocusable(false);
            quantity.setText("" + retrievedInfos.getQuantité());
            quantity.setFocusable(false);

            int position = 0;
            int index = 0;
            for (LotInfo currentLot: myLotInfos) {
                if(currentLot.getRef().equals(retrievedInfos.getRéférence()) && currentLot.getMarque().equals(retrievedInfos.getMarque())) {
                    position = index;
                }
                index++;
            }
            volantsSP.setSelection(position);

            payed.setChecked(retrievedInfos.isPayed());
            if(retrievedInfos.isPayed()) {
                payed.setTextColor(getResources().getColor(R.color.payed));
                payed.setText("Payé");
            } else {
                payed.setTextColor(getResources().getColor(R.color.unpayed));
                payed.setText("Non payé");
            }

            volantsSP.setEnabled(false);
            volantsSP.setClickable(false);
            plusQuantity.setEnabled(false);
            minusQuantity.setEnabled(false);
            valideButton.setVisibility(View.GONE);
        } else {
            this.quantity.setText("0");
            payed.setChecked(false);
            payed.setTextColor(getResources().getColor(R.color.unpayed));
            payed.setText("Non payé");
            updatePayedButton.setVisibility(View.GONE);
        }

        volantsSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String ref = ((TextView)view.findViewById(R.id.lot_ref_tv)).getText().toString();
                String marque = ((TextView)view.findViewById(R.id.lot_marque_tv)).getText().toString();
                String taille = ((TextView)view.findViewById(R.id.lot_taille_tv)).getText().toString();
                String prixString = ((TextView)view.findViewById(R.id.lot_prix_tv)).getText().toString();
                float prix = Float.valueOf(prixString.substring(0, prixString.length() - 1).replace(',','.'));

                totalPrice.setText("" + String.format("%.2f", updatePrice()) + "€");
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        payed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked) {
                    payed.setTextColor(getResources().getColor(R.color.payed));
                    payed.setText("Payé");
                } else {
                    payed.setTextColor(getResources().getColor(R.color.unpayed));
                    payed.setText("Non payé");
                }

            }
        });

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("eDBTEAM/PurchaseItem", "TextChanged -> " + quantity.getText().toString());
                if(!quantity.getText().toString().equals("")) {
                    totalPrice.setText("" + String.format("%.2f", updatePrice()) + "€");
                } else {
                    quantity.setText("0");
                }

            }
        });
    }

    public void increaseQuantity(View view) {
        this.quantity.setText("" + (Integer.valueOf(this.quantity.getText().toString()) + 1));
        this.totalPrice.setText("" + String.format("%.2f", this.updatePrice()) + "€");
    }

    public void decreaseQuantity(View view) {
        if(Integer.valueOf(this.quantity.getText().toString()) > 0) {
            this.quantity.setText("" + (Integer.valueOf(this.quantity.getText().toString()) - 1));
            this.totalPrice.setText("" + String.format("%.2f", this.updatePrice()) + "€");
        }
    }

    public float updatePrice() {
        String prixString = ((TextView) findViewById(R.id.lot_prix_tv)).getText().toString();
        float prix = Float.valueOf(prixString.substring(0, prixString.length() - 1).replace(',','.'));
        return Integer.valueOf(this.quantity.getText().toString()) * prix;
    }

    public void validateCommand(View view) {

        if(acheteurName.getText().toString().equals("") || acheteurFamily.getText().toString().equals("") || acheteurSociety.getText().toString().equals("")) {
            Toast.makeText(getBaseContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
        } else {
            DateDAO dateDAO = new DateDAO(this);
            AcheteurDAO acheteurDAO = new AcheteurDAO(this);
            AcheterDAO acheterDAO = new AcheterDAO(this);

            dateDAO.open();
            acheteurDAO.open();
            acheterDAO.open();

            dateDAO.addDate(new Date(new java.util.Date()));
            long acheteurID = acheteurDAO.addAcheteur(new Acheteur(acheteurName.getText().toString(), acheteurFamily.getText().toString(), acheteurSociety.getText().toString()));

            String prixString = ((TextView) findViewById(R.id.lot_prix_tv)).getText().toString();
            float prix = Float.valueOf(prixString.substring(0, prixString.length() - 1).replace(',','.'));

            String tailleString = ((TextView) findViewById(R.id.lot_taille_tv)).getText().toString();
            int taille = Integer.valueOf(tailleString);
            long lotID = volantsDAO.getLotVolantID(((TextView) findViewById(R.id.lot_marque_tv)).getText().toString(), ((TextView) findViewById(R.id.lot_ref_tv)).getText().toString());
            acheterDAO.addAcheter(new Acheter(lotID, dateDAO.getMaxID(dateDAO.TABLE_NAME, dateDAO.ID), acheteurID, Integer.valueOf(this.quantity.getText().toString()), payed.isChecked()));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void updatePayed(View view) {
        AcheterDAO acheterDAO = new AcheterDAO(this);
        acheterDAO.open();
        Acheter achatState = acheterDAO.getAcheter(this.intent.getLongExtra("achatInfoID", -1));
        achatState.setPayed(payed.isChecked());
        acheterDAO.updatePayed(achatState);
        Intent moveTo = new Intent(this, Purchase.class);
        startActivity(moveTo);
    }
}
