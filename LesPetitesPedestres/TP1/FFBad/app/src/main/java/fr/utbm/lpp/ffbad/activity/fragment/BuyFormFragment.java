package fr.utbm.lpp.ffbad.activity.fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.utbm.lpp.ffbad.FFBadApplication;
import fr.utbm.lpp.ffbad.R;
import fr.utbm.lpp.ffbad.data.db.FFBadDbContract;
import fr.utbm.lpp.ffbad.data.db.FFbadDbHelper;

public class BuyFormFragment extends Fragment {

    Button _btnbuy;
    EditText _txtbrand, _txtmodel, _txtquantity, _txtbuyerName, _txtpayed;



    public static BuyFormFragment newInstance() {
        Bundle args = new Bundle();
        BuyFormFragment fragment = new BuyFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_buy_form_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }





    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final FFBadApplication app = (FFBadApplication) getActivity().getApplication();
        _btnbuy = (Button) getView().findViewById(R.id.btnbuy);
        _txtbrand = (EditText) getView().findViewById(R.id.txtbrand);
        _txtmodel = (EditText) getView().findViewById(R.id.txtmodel);
        _txtquantity = (EditText) getView().findViewById(R.id.txtquantity);
        _txtbuyerName = (EditText) getView().findViewById(R.id.txtbuyerName);
        _txtpayed = (EditText) getView().findViewById(R.id.txtpayed);
        _btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
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
                FFbadDbHelper.createSale(app.getDb(), customer_id, shuttlecock_id, price, quantity2, is_paid);
                Toast.makeText(getContext(), "update db", Toast.LENGTH_LONG).show();
            }
        });

    }
}
