package fr.utbm.lpp.ffbad.activity.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import fr.utbm.lpp.ffbad.FFBadApplication;
import fr.utbm.lpp.ffbad.R;
import fr.utbm.lpp.ffbad.data.Sale;
import fr.utbm.lpp.ffbad.data.adapter.ShuttlecockCursorAdapter;
import fr.utbm.lpp.ffbad.data.db.FFBadDbContract;
import fr.utbm.lpp.ffbad.data.db.FFbadDbHelper;

public class BuyFormFragment extends Fragment {

    public static final String ARG_SALE_ID = "saleId";

    private Sale sale = null;

    private Button _btnbuy;
    private EditText _txtquantity, _txtbuyerName, _txtprice;
    private Switch _swipayed;
    private Spinner _spimodel;

    public static BuyFormFragment newInstance() {
        Bundle args = new Bundle();
        BuyFormFragment fragment = new BuyFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static BuyFormFragment newInstance(long ID) {
        Bundle args = new Bundle();
        args.putLong(ARG_SALE_ID, ID);
        BuyFormFragment fragment = new BuyFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_buy_form_fragment, container, false);
        _spimodel = v.findViewById(R.id.spimodel);
        _btnbuy = v.findViewById(R.id.btnbuy);
        _txtquantity = v.findViewById(R.id.txtquantity);
        _txtprice = v.findViewById(R.id.txtprice);
        _txtbuyerName = v.findViewById(R.id.txtbuyerName);
        _swipayed = v.findViewById(R.id.swipayed);
        return v;
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
                projection, null, null, null, null, null
        );

        ShuttlecockCursorAdapter shuttlecockAdapter = new ShuttlecockCursorAdapter(this.getContext(), cursor);
        _spimodel.setAdapter(shuttlecockAdapter);

        if(this.getArguments() != null && this.getArguments().containsKey(ARG_SALE_ID)) {
            final String saleQuery = "" +
                    "SELECT * " +
                    "FROM sale " +
                        "INNER JOIN shuttlecock ON shuttlecock._id = sale.shuttlecock_id " +
                        "INNER JOIN customer ON customer._id = sale.customer_id " +
                    "WHERE sale._id = ?";

            Log.d("SQL", saleQuery);

            long saleId = getArguments().getLong(ARG_SALE_ID);
            String[] params = new String[] { String.valueOf(saleId) };
            Cursor saleCursor = app.getDb().rawQuery(saleQuery, params);
            saleCursor.moveToFirst();
            sale = FFBadDbContract.Sale.getFromCursor(saleCursor);

            _txtquantity.setText(String.valueOf(sale.getQuantity()));
            _txtprice.setText(String.valueOf(sale.getPrice()));
            _txtbuyerName.setText(String.valueOf(sale.getCustomer().getName()));
            _swipayed.setChecked(sale.isPaid());

            _spimodel.setEnabled(false);
            _txtquantity.setEnabled(false);
            _txtprice.setEnabled(false);
            _txtbuyerName.setEnabled(false);
            if (sale.isPaid()) {
//                _swipayed.setEnabled(false);
            }
        }

        _btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (sale != null) { // Update existing sale
                    ContentValues values = new ContentValues();
                    values.put(FFBadDbContract.Sale.COLUMN_NAME_IS_PAID, _swipayed.isChecked());
                    app.getDb().update(FFBadDbContract.Sale.TABLE_NAME, values, "_id = ?", new String[] { String.valueOf(sale.getId()) });
                    Toast.makeText(getContext(), "Done", Toast.LENGTH_LONG).show();
                } else { // Create new sale
                    // TODO n'enregistre pas dans la base
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
                // Todo Retourner à la liste ?
            }
        });
    }
}
