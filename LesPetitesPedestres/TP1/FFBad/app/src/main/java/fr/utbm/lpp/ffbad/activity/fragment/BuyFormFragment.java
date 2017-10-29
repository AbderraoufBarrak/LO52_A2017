package fr.utbm.lpp.ffbad.activity.fragment;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Arrays;

import fr.utbm.lpp.ffbad.FFBadApplication;
import fr.utbm.lpp.ffbad.R;
import fr.utbm.lpp.ffbad.data.Sale;
import fr.utbm.lpp.ffbad.data.adapter.SaleCursorAdapter;
import fr.utbm.lpp.ffbad.data.adapter.ShuttlecockCursorAdapter;
import fr.utbm.lpp.ffbad.data.db.FFBadDbContract;
import fr.utbm.lpp.ffbad.data.db.FFbadDbHelper;

public class BuyFormFragment extends Fragment {

    Button _btnbuy;
    EditText _txtquantity, _txtbuyerName, _txtprice;
    Switch _swipayed;
    Spinner _spimodel;


    public static BuyFormFragment newInstance() {
        Bundle args = new Bundle();
        BuyFormFragment fragment = new BuyFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static BuyFormFragment newInstance(long ID) {
        Bundle args = new Bundle();
        args.putLong("0", ID);
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

        String[] projection = {
                FFBadDbContract.Shuttlecock._ID,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_BRAND,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_REFERENCE,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_STOCK,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_RATING
        };

        Cursor cursor = app.getDb().query(
                FFBadDbContract.Shuttlecock.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );




        _spimodel = (Spinner) getView().findViewById(R.id.spimodel);
        ShuttlecockCursorAdapter adapter = new ShuttlecockCursorAdapter(this.getContext(), cursor);
        _spimodel.setAdapter(adapter);

        _btnbuy = (Button) getView().findViewById(R.id.btnbuy);
        _txtquantity = (EditText) getView().findViewById(R.id.txtquantity);
        _txtprice = (EditText) getView().findViewById(R.id.txtprice);
        _txtprice.setText("42.30");
        _txtbuyerName = (EditText) getView().findViewById(R.id.txtbuyerName);
        _swipayed = (Switch) getView().findViewById(R.id.swipayed);

        if(this.getArguments() != null){
            final String MY_QUERY = "" +
                    "SELECT * " +
                    "FROM sale " +
                        "INNER JOIN shuttlecock ON shuttlecock._id = sale.shuttlecock_id " +
                        "INNER JOIN customer ON customer._id = sale.customer_id " +
                    "WHERE sale._id = ?";

            Log.d("SQL", MY_QUERY);

            long a = getArguments().getLong("0");
            String[] b = new String[1];
            String c = String.valueOf(a);
            Arrays.fill(b, c);
            Cursor cursordb = app.getDb().rawQuery(MY_QUERY, b);    //TODO régler le problème d'index

            Sale sale = FFBadDbContract.Sale.getFromCursor(cursordb);

            _txtquantity.setText(sale.getQuantity());
            //_txtprice.setText(sale.getPrice());
            //_txtbuyerName.setText(sale.getCustomer());
            _swipayed.setEnabled(sale.isPaid());
            //_spimodel.setSelected();
        }

        _btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String buyerName = _txtbuyerName.getText().toString();
                int customer_id = 152;
                int shuttlecock_id = 14;
                double price = 42.53;

                String text = _spimodel.getSelectedItem().toString();
                _txtbuyerName.setText(text);

                int quantity = Integer.parseInt(_txtquantity.getText().toString());
                boolean is_paid = _swipayed.isChecked();
                FFbadDbHelper.createSale(app.getDb(), customer_id, shuttlecock_id, price, quantity, is_paid);
                if(is_paid) {
                    Toast.makeText(getContext(), "update db y", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "update db n", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
