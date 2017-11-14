package com.lo52.dewback.shuttlesmgmt.stock_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import com.lo52.dewback.shuttlesmgmt.R;
import com.lo52.dewback.shuttlesmgmt.stock_activity.model.OrderDataBean;
import com.lo52.dewback.shuttlesmgmt.stock_activity.model.StockDataBean;

import java.util.ArrayList;
import java.util.List;

public class ShuttleOrderActivity extends AppCompatActivity {

    private static final String BUNDLE_ORDER_ID_KEY_STRING = "order_id";
    private List<OrderDataBean> orders;
    private List<StockDataBean> stocks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuttle_order);

        StockDataBean yonexAS30Shuttle = new StockDataBean("Yonex", "YONEX_AS30", 27.0, 500, R.drawable.yonex_as30);
        //TODO Utiliser la BDD pour gather les données (passer par une classe de DAO)
        orders = new ArrayList<>();
        orders.add(new OrderDataBean(0, yonexAS30Shuttle, 10, "John Carter", false));

        //TODO Utiliser la BDD pour gather les données (passer par une classe de DAO)
        stocks = new ArrayList<>();
        stocks.add(yonexAS30Shuttle);
        stocks.add(new StockDataBean("RSL", "Grade 3", 16.7, 5000, R.drawable.rsl_grade_a3));
        stocks.add(new StockDataBean("RSL", "Grade A9", 13.7, 10000, R.drawable.rsl_grade_a9));
        stocks.add(new StockDataBean("RSL", "Grade A1", 21.0, 6000, R.drawable.rsl_grade_a1));

        Bundle b = getIntent().getExtras();
        if(b!=null && b.get(BUNDLE_ORDER_ID_KEY_STRING)!=null) {
            initGuiWithExistingOrder((Integer) b.get(BUNDLE_ORDER_ID_KEY_STRING));
        } else{
            initGuiForNewOrder();
        }
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    private void initGuiForNewOrder(){
        initGui(false, null);
    }

    private void initGuiWithExistingOrder(int product_id){
        initGui(true, product_id);
    }

    private void initGui(boolean dataLocked, Integer initialProductId){
        Spinner productNameView = (Spinner) findViewById(R.id.shuttleNameSpinner);
        //TODO utiliser CustomOrderSpinnerItemAdapter
        ArrayAdapter<StockDataBean> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,stocks);
        productNameView.setAdapter(adapter);

        EditText productAmountNumberView = (EditText) findViewById(R.id.shuttleAmountNumberView);

        //EditText buyerNameTextView = (EditText) findViewById(R.id.buyerNameTextView);

        //Switch orderPaidStateSwitch = (Switch) findViewById(R.id.orderPaidSwitch);

        Button validationButton = (Button) findViewById(R.id.validationButton);
        validationButton.setOnClickListener(getValidationButtonOnClickListener());

        //TODO ajouter données initiales
        if(initialProductId!=null){
            productNameView.setEnabled(!dataLocked);
            productAmountNumberView.setText("0");
        }else{

        }

        //TODO ajouter code setEditable(!dataLocked)
        if(dataLocked){
            productNameView.setEnabled(!dataLocked);
            productAmountNumberView.setText("0");
        }else{

        }
    }

    private View.OnClickListener getValidationButtonOnClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO ajouter un listener de validation
            }
        };
    }
}
