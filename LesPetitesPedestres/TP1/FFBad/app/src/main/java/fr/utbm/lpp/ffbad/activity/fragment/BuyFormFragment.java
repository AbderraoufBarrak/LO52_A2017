package fr.utbm.lpp.ffbad.activity.fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.utbm.lpp.ffbad.R;
import fr.utbm.lpp.ffbad.data.db.FFBadDbContract;
import fr.utbm.lpp.ffbad.data.db.FFbadDbHelper;

public class BuyFormFragment extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnbuy;
    EditText _txtbrand, _txtmodel, _txtquantity, _txtbuyerName, _txtpayed;

    /*public static BuyFormFragment newInstance() {
        Bundle args = new Bundle();
        BuyFormFragment fragment = new BuyFormFragment();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_form_fragment);
        openHelper = new FFbadDbHelper(this);
        _btnbuy = (Button)findViewById(R.id.btnbuy);
        _txtbrand = (EditText) findViewById(R.id.txtbrand);
        _txtmodel = (EditText)findViewById(R.id.txtmodel);
        _txtquantity = (EditText)findViewById(R.id.txtquantity);
        _txtbuyerName = (EditText)findViewById(R.id.txtbuyerName);
        _txtpayed = (EditText)findViewById(R.id.txtpayed);
        _btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                db = openHelper.getWritableDatabase();
                String brand = _txtbrand.getText().toString();
                String model = _txtmodel.getText().toString();
                String quantity = _txtquantity.getText().toString();
                String buyerName = _txtbuyerName.getText().toString();
                String payed = _txtpayed.getText().toString();
                int customer_id = 152;
                int shuttlecock_id = 14;
                double price = 42.53;
                int quantity2 = 7;
                boolean is_paid = false;
                FFbadDbHelper.createSale(db, customer_id, shuttlecock_id, price, quantity2, is_paid);
                Toast.makeText(getApplicationContext(), "update db", Toast.LENGTH_LONG).show();
            }
        });

    }
}
