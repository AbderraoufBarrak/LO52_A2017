package com.example.shuttlesmgmt.activity.Version2.addActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuttlesmgmt.DAOImplements.CustomerDAOImpl;
import com.example.shuttlesmgmt.DAOImplements.OrderDAOImpl;
import com.example.shuttlesmgmt.DAOImplements.ProductDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.activity.Version2.DBActivity.OrderActivity;
import com.example.shuttlesmgmt.entity.Customer;
import com.example.shuttlesmgmt.entity.Order;
import com.example.shuttlesmgmt.entity.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddOrder extends Activity implements View.OnClickListener {

    private EditText quantity, price;
    private Button add, back;
    private Spinner spCustomer, spProductName, spProductRef;
    private OrderDAOImpl orderDAO;
    private CustomerDAOImpl customerDAO;
    private ProductDAOImpl productDAO;
    private List<String> listProductName, listProductRef, listCustomerName;
    private Order order = new Order();
    private ArrayAdapter<String> spinnerAdapterCustomer, spinnerAdapterProductName, spinnerAdapterProductRef;
    private Switch isPaid;
    private Boolean isPaidValue;
    private String customerValue, productNameValue, quantityValue, priceValue, refValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        productDAO = new ProductDAOImpl(this);
        customerDAO = new CustomerDAOImpl(this);
        orderDAO = new OrderDAOImpl(this);

        productDAO.getDBRead();
        orderDAO.getDBWrite();
        customerDAO.getDBRead();

        quantity = (EditText) findViewById(R.id.id_ETQuantity);
        price = (EditText) findViewById(R.id.id_ETPrice);

        add = (Button) findViewById(R.id.id_add);
        back = (Button) findViewById(R.id.id_back);

        isPaid = (Switch) findViewById(R.id.id_isPaid);

        spCustomer = (Spinner) findViewById(R.id.id_spCustomer);
        spProductName = (Spinner) findViewById(R.id.id_spProduct);
        spProductRef = (Spinner) findViewById(R.id.id_spRef);

        spCustomer.setAdapter(spinnerAdapterCustomer);
        spProductName.setAdapter(spinnerAdapterProductName);
        spProductRef.setAdapter(spinnerAdapterProductRef);

        listCustomerName = new ArrayList<String>();
        listProductName = new ArrayList<String>();
        listProductRef = new ArrayList<String>();

        listCustomerName = customerDAO.getListCustomersName();
        listProductName = productDAO.getListProductsName();

        spinnerAdapterCustomer = new ArrayAdapter<String>(this, R.layout.spinner_item, listCustomerName);
        spinnerAdapterProductName = new ArrayAdapter<String>(this, R.layout.spinner_item, listProductName);

        spCustomer.setAdapter(spinnerAdapterCustomer);
        spProductName.setAdapter(spinnerAdapterProductName);

        spCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spProductName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listProductRef = productDAO.getListProductsRef(spProductName.getSelectedItem().toString());
                spinnerAdapterProductRef = new ArrayAdapter<String>(AddOrder.this, R.layout.spinner_item, listProductRef);
                spProductRef.setAdapter(spinnerAdapterProductRef);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(add.isPressed()){
            customerValue = spCustomer.getSelectedItem().toString();
            productNameValue = spProductName.getSelectedItem().toString();
            quantityValue = quantity.getText().toString();
            priceValue = price.getText().toString();
            isPaidValue = isPaid.isChecked();
            refValue = spProductRef.getSelectedItem().toString();

            if(quantityValue.contentEquals("")){
                Toast.makeText(getApplicationContext(), "Les champs ne peuvent pas être vide !", Toast.LENGTH_LONG).show();
            }else{
                if(priceValue.contentEquals("")){

                }else{
                    order.setPrice(Double.valueOf(priceValue));
                }
                order.setCustomerName(customerValue);
                order.setProductName(productNameValue);
                order.setDate(new Date());
                order.setQuantity(Integer.valueOf(quantityValue));
                order.setIsPaid(isPaidValue);
                if((productDAO.fetchByNameAndRef(order.getProductName(), refValue) != 0) && (customerDAO.fetchByName(order.getCustomerName()) != 0)){
                    order.setIdCustomer(customerDAO.fetchByName(order.getCustomerName()));
                    order.setIdProduct(productDAO.fetchByNameAndRef(order.getProductName(), refValue));
                    productDAO.getDBWrite();
                    if(productDAO.decreaseQuantity(order.getIdProduct(), order.getQuantity())==true){
                        if(orderDAO.create(order)== true){
                            price.setText("");
                            quantity.setText("");
                            Log.i("AppInfo", order.toString());
                            Toast.makeText(getApplicationContext(), "New Order added !", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Insufficient quantity !", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "ID customer ou ID product n'est pas correct revoir le code !", Toast.LENGTH_LONG).show();
                }
            }
        }else if(back.isPressed()){
            Intent intent = new Intent(AddOrder.this, OrderActivity.class);
            startActivity(intent);
            productDAO.close();
            orderDAO.close();
            customerDAO.close();
            this.finish();
        }
    }
}
