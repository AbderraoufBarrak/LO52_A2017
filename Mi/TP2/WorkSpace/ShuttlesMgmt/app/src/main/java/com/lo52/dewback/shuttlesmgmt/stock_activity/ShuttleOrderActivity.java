package com.lo52.dewback.shuttlesmgmt.stock_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.lo52.dewback.shuttlesmgmt.R;
import com.lo52.dewback.shuttlesmgmt.stock_activity.model.IOrderDao;
import com.lo52.dewback.shuttlesmgmt.stock_activity.model.IStockDao;
import com.lo52.dewback.shuttlesmgmt.stock_activity.model.beans.OrderDataBean;
import com.lo52.dewback.shuttlesmgmt.stock_activity.model.beans.StockDataBean;
import com.lo52.dewback.shuttlesmgmt.stock_activity.model.database.SQLiteOrderDao;
import com.lo52.dewback.shuttlesmgmt.stock_activity.model.database.SQLiteStockDao;
import com.lo52.dewback.shuttlesmgmt.stock_activity.view.IObjectConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShuttleOrderActivity extends AppCompatActivity {

    private static final String BUNDLE_ORDER_ID_KEY_STRING = "order_id";
    private static final int DEFAULT_INT_VALUE = Integer.MIN_VALUE;

    private List<OrderDataBean> orders;
    private List<StockDataBean> stocks;
    private IObjectConverter<StockDataBean, String> stockCenverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuttle_order);

        IOrderDao orderDao = new SQLiteOrderDao(getApplicationContext());
        IStockDao stockDao = new SQLiteStockDao(getApplicationContext());

        stockCenverter = object -> String.format("%s %s", object.getBrand(), object.getName());

        orders = new ArrayList<>();
        orders.addAll(orderDao.getOrders());

        stocks = new ArrayList<>();
        stocks.addAll(stockDao.getStock());

        if(getIntent().getIntExtra(BUNDLE_ORDER_ID_KEY_STRING, DEFAULT_INT_VALUE)!=DEFAULT_INT_VALUE) {
            initGuiWithExistingOrder(getIntent().getIntExtra(BUNDLE_ORDER_ID_KEY_STRING, DEFAULT_INT_VALUE));
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

    private void initGui(boolean dataLocked, Integer initialOrderId){
        Spinner productNameView = (Spinner) findViewById(R.id.shuttleNameSpinner);
        List<String> convertedStock = renderStock(stocks);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, convertedStock);
        productNameView.setAdapter(adapter);

        EditText productAmountNumberView = (EditText) findViewById(R.id.shuttleAmountNumberView);

        EditText buyerNameTextView = (EditText) findViewById(R.id.buyerNameTextView);

        Switch orderPaidStateSwitch = (Switch) findViewById(R.id.orderPaidSwitch);

        Button validationButton = (Button) findViewById(R.id.validationButton);
        validationButton.setOnClickListener(getValidationButtonOnClickListener());

        /*
        Récupération des indexs des données initiales
         */
        boolean found = false;
        if(initialOrderId!=null){
            for(OrderDataBean order : orders){
                if(Objects.equals(initialOrderId, order.getOrderId())){
                    int initialDataProductIndex = stocks.indexOf(order.getProductId());
                    if(initialDataProductIndex>=0 && !found) {
                        found = true;
                        this.runOnUiThread(() -> {
                            productNameView.setSelection(initialDataProductIndex);
                            adapter.notifyDataSetChanged();
                            productAmountNumberView.setText(String.valueOf(order.getProductAmount()));
                            buyerNameTextView.setText(order.getBuyerName());
                            orderPaidStateSwitch.setChecked(order.isOrderPaid());
                        });
                    }
                }
            }
        }

        if(!found) {
            this.runOnUiThread(() -> {
                productAmountNumberView.setText("0");
                buyerNameTextView.setText("");
                orderPaidStateSwitch.setChecked(false);
            });
        }

        /*
        Modification de l'état des données
         */
        productNameView.setEnabled(!dataLocked);
        productAmountNumberView.setEnabled(!dataLocked);
        buyerNameTextView.setEnabled(!dataLocked);
    }

    private List<String> renderStock(List<StockDataBean> stocks) {
        List<String> reply = new ArrayList<>();
        for(StockDataBean stock : stocks){
            reply.add(stockCenverter.convert(stock));
        }
        return reply;
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
