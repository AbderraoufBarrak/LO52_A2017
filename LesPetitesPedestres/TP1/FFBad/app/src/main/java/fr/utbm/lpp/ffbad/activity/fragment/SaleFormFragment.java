package fr.utbm.lpp.ffbad.activity.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import fr.utbm.lpp.ffbad.FFBadApplication;
import fr.utbm.lpp.ffbad.R;
import fr.utbm.lpp.ffbad.data.Sale;
import fr.utbm.lpp.ffbad.data.adapter.CustomerCursorAdapter;
import fr.utbm.lpp.ffbad.data.adapter.ShuttlecockCursorAdapter;
import fr.utbm.lpp.ffbad.data.db.FFBadDbContract;
import fr.utbm.lpp.ffbad.data.db.FFbadDbHelper;

public class SaleFormFragment extends Fragment implements TextWatcher {

    public static final String ARG_SALE_ID = "saleId";

    private OnSaleFormFragmentEvent eventManager;

    private Sale sale = null;

    private Button _btnbuy;
    private EditText _txtquantity;
    private TextView _txtprice;
    private Switch _swipayed;
    private Spinner _spimodel, _spiCustomer;

    private Integer stock = null;
    private Long shuttlecockId = null;
    private Long customerId = null;


    public static SaleFormFragment newInstance() {
        Bundle args = new Bundle();
        SaleFormFragment fragment = new SaleFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static SaleFormFragment newInstance(long ID) {
        Bundle args = new Bundle();
        args.putLong(ARG_SALE_ID, ID);
        SaleFormFragment fragment = new SaleFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sale_form, container, false);
        _spiCustomer = v.findViewById(R.id.spi_customer);
        _spimodel = v.findViewById(R.id.spimodel);
        _btnbuy = v.findViewById(R.id.btnbuy);
        _txtquantity = v.findViewById(R.id.txtquantity);
        _txtprice = v.findViewById(R.id.txtprice);
        _swipayed = v.findViewById(R.id.swipayed);
        _txtquantity.addTextChangedListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.eventManager = (OnSaleFormFragmentEvent) getActivity();
        final FFBadApplication app = (FFBadApplication) getActivity().getApplication();

        _spimodel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                shuttlecockId = l;
                TextView txtStock = view.findViewById(R.id.stock);
                stock = Integer.parseInt(txtStock.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                shuttlecockId = null;
                stock = null;
            }
        });

        _spiCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                customerId = l;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                customerId = null;
            }
        });

        if(this.getArguments() != null && this.getArguments().containsKey(ARG_SALE_ID)) { // Edit existing sale
            final String saleQuery = "" +
                    "SELECT * " +
                    "FROM sale " +
                    "INNER JOIN shuttlecock ON shuttlecock._id = sale.shuttlecock_id " +
                    "INNER JOIN customer ON customer._id = sale.customer_id " +
                    "WHERE sale._id = ?";

            long saleId = getArguments().getLong(ARG_SALE_ID);
            String[] params = new String[] { String.valueOf(saleId) };
            Cursor saleCursor = app.getDb().rawQuery(saleQuery, params);
            saleCursor.moveToFirst();
            sale = FFBadDbContract.Sale.getFromCursor(saleCursor);

            ShuttlecockCursorAdapter shuttlecockAdapter = new ShuttlecockCursorAdapter(this.getContext(), saleCursor);
            _spimodel.setAdapter(shuttlecockAdapter);

            CustomerCursorAdapter customerAdapter = new CustomerCursorAdapter(this.getContext(), saleCursor);
            _spiCustomer.setAdapter(customerAdapter);

            _txtquantity.setText(String.valueOf(sale.getQuantity()));
            _swipayed.setChecked(sale.isPaid());

            _swipayed.setTextOff("NOT PAYED");
            _swipayed.setTextOn("PAYED");

            _spiCustomer.setEnabled(false);
            _spimodel.setEnabled(false);
            _txtquantity.setEnabled(false);
            _txtprice.setEnabled(false);

            if (sale.isPaid()) {
                _swipayed.setEnabled(false);
                _btnbuy.setText("CANCEL");
            } else {
                _btnbuy.setText("UPDATE");
            }
        } else { // Create new sale
            String[] projection = {
                    FFBadDbContract.Shuttlecock._ID,
                    FFBadDbContract.Shuttlecock.COLUMN_NAME_BRAND,
                    FFBadDbContract.Shuttlecock.COLUMN_NAME_REFERENCE,
                    FFBadDbContract.Shuttlecock.COLUMN_NAME_STOCK,
                    FFBadDbContract.Shuttlecock.COLUMN_NAME_RATING,
                    FFBadDbContract.Shuttlecock.COLUMN_NAME_PRICE
            };

            Cursor shuttlecockCursor = app.getDb().query(
                    FFBadDbContract.Shuttlecock.TABLE_NAME,
                    projection, null, null, null, null, null
            );

            ShuttlecockCursorAdapter shuttlecockAdapter = new ShuttlecockCursorAdapter(this.getContext(), shuttlecockCursor);
            _spimodel.setAdapter(shuttlecockAdapter);

            Cursor customerCursor = app.getDb().query(
                    FFBadDbContract.Customer.TABLE_NAME,
                    new String[] {
                            FFBadDbContract.Customer._ID,
                            FFBadDbContract.Customer.COLUMN_NAME_NAME,
                            FFBadDbContract.Customer.COLUMN_NAME_TYPE,
                    }, null, null, null, null, null
            );

            CustomerCursorAdapter customerAdapter = new CustomerCursorAdapter(this.getContext(), customerCursor);
            _spiCustomer.setAdapter(customerAdapter);
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
                    boolean valid = true;
                    int quantity = 0;
                    try {
                        quantity = Math.max(Integer.parseInt(_txtquantity.getText().toString()), 0);
                    } catch (NumberFormatException e) {

                    }
                    if (quantity == 0) {
                        _txtquantity.setError("Requires a positive quantity");
                        valid = false;
                    } else if(stock != null) {
                        if (quantity > stock) {
                            _txtquantity.setError("Insufficient stock");
                            valid = false;
                        }
                    }
                    float price = -1f;
                    try {
                        price = Float.parseFloat(_txtprice.getText().toString());
                        Log.d("Price", "" + price);
                    } catch (NumberFormatException e) {
                        _txtprice.setError("Incorrect price");
                        valid = false;
                    }
                    if (stock == null || shuttlecockId == null || customerId == null) {
                        valid = false;
                    }

                    if (valid) {
                        FFbadDbHelper.createSale(app.getDb(), customerId, shuttlecockId, quantity, _swipayed.isChecked());
                        ContentValues values = new ContentValues();
                        values.put(FFBadDbContract.Shuttlecock.COLUMN_NAME_STOCK, stock - quantity);
                        long id = app.getDb().update(FFBadDbContract.Shuttlecock.TABLE_NAME, values, "_id = ?", new String[] {shuttlecockId.toString()});
                        Toast.makeText(getContext(), "Done: " + id, Toast.LENGTH_LONG).show();
                        eventManager.onSaleFormCompleted();
                    }
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        _txtprice.setText("Please, enter a quantity between 1 and 9999.");
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        final FFBadApplication app = (FFBadApplication) getActivity().getApplication();
        final String saleQuery = "" +
                "SELECT * " +
                "FROM SHUTTLECOCK " +
                "WHERE SHUTTLECOCK._id = ?";
        String[] params = new String[] { String.valueOf(shuttlecockId) };
        Cursor shuttlecockCursor = app.getDb().rawQuery(saleQuery, params);
        shuttlecockCursor.moveToFirst();
        if(shuttlecockCursor.getCount() == 0) {return;}
        double price = shuttlecockCursor.getDouble(shuttlecockCursor.getColumnIndex("price"));
        boolean tryParseInt = false;
        try {
            Integer.parseInt(_txtquantity.getText().toString());
            tryParseInt = true;
        } catch (NumberFormatException e) {
            tryParseInt = false;
        }

        if(tryParseInt && Integer.parseInt(_txtquantity.getText().toString()) < 10000){
            price = price * Integer.parseInt(_txtquantity.getText().toString());
            _txtprice.setText(Double.toString(price));
        }

        /*
        ShuttlecockCursorAdapter shuttlecockAdapter = new ShuttlecockCursorAdapter(this.getContext(), shuttlecockCursor);

        FFBadDbContract.Shuttlecock hey = FFBadDbContract.Shuttlecock.getFromCursor(shuttlecockCursor);
*/


/*
        FFbadDbHelper.createSale(app.getDb(), customerId, shuttlecockId, price, quantity, _swipayed.isChecked());
        ContentValues values = new ContentValues();
        values.put(FFBadDbContract.Shuttlecock.COLUMN_NAME_STOCK, stock - quantity);
        long id = app.getDb().update(FFBadDbContract.Shuttlecock.TABLE_NAME, values, "_id = ?", new String[] {});
        Toast.makeText(getContext(), "Done: " + id, Toast.LENGTH_LONG).show();*/
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public interface OnSaleFormFragmentEvent {
        /**
         * Event triggered for creating a new element
         */
        void onSaleFormCompleted();
    }
}
