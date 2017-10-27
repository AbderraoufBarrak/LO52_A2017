package fr.utbm.lpp.ffbad.activity.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import fr.utbm.lpp.ffbad.FFBadApplication;
import fr.utbm.lpp.ffbad.R;
import fr.utbm.lpp.ffbad.data.db.FFbadDbHelper;

public class BuyFormFragment extends Fragment {

    Button _btnbuy;
    EditText _txtbrandmodel, _txtquantity, _txtbuyerName, _txtprice;
    Switch _swipayed;



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
        _txtbrandmodel = (EditText) getView().findViewById(R.id.txtbrandmodel);
        _txtquantity = (EditText) getView().findViewById(R.id.txtquantity);
        _txtprice = (EditText) getView().findViewById(R.id.txtprice);
        _txtprice.setText("42.30");
        _txtbuyerName = (EditText) getView().findViewById(R.id.txtbuyerName);
        _swipayed = (Switch) getView().findViewById(R.id.swipayed);
        _btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String model = _txtbrandmodel.getText().toString();
                String buyerName = _txtbuyerName.getText().toString();

                int customer_id = 152;
                int shuttlecock_id = 14;
                double price = 42.53;

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
