package com.example.shuttlesmgmt.activity.Version2.modifyActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuttlesmgmt.DAOImplements.OrderDAOImpl;
import com.example.shuttlesmgmt.DAOImplements.ProductDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.activity.Version2.DBActivity.OrderActivity;
import com.example.shuttlesmgmt.entity.Version2.Order;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class modifyOrder extends Activity implements View.OnClickListener {

    private List<Order> listOrder;
    private Order order;
    private TextView customer, productName, productRef;
    private EditText quantity, price;
    private Switch isPaid;
    private Button modify, back, delete;
    private OrderDAOImpl orderDAO;
    private ProductDAOImpl productDAO;
    private Intent intent;
    private Boolean isPaidValue;
    private String quantityValue, priceValue;
    private int oldQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_order);

        intent = getIntent();

        orderDAO = new OrderDAOImpl(this);
        productDAO = new ProductDAOImpl(this);

        orderDAO.getDBWrite();
        productDAO.getDBRead();

        customer = (TextView) findViewById(R.id.id_TVcustomer);
        productName = (TextView) findViewById(R.id.id_TVproductName);
        productRef = (TextView) findViewById(R.id.id_TVproductRef);

        quantity = (EditText) findViewById(R.id.id_ETquantity);
        price = (EditText) findViewById(R.id.id_ETprice);

        isPaid = (Switch) findViewById(R.id.id_isPaid);

        modify = (Button) findViewById(R.id.id_modify);
        back = (Button) findViewById(R.id.id_back);
        delete = (Button) findViewById(R.id.id_delete);

        listOrder = new ArrayList<Order>();
        try {
            listOrder = orderDAO.fetchAll2();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(intent.getLongExtra("orderInfo", -1) != -1){
            Long id = intent.getLongExtra("orderInfo", -1);

            order = listOrder.get(id.intValue()-1);

            customer.setText(order.getCustomerName());
            customer.setFocusable(false);

            productName.setText(order.getProductName());
            productName.setFocusable(false);

            productRef.setText(productDAO.getProductRef(order.getIdProduct()));
            productRef.setFocusable(false);

            quantity.setText(Integer.toString(order.getQuantity()));
            oldQuantity = order.getQuantity();

            price.setText(Double.toString(order.getPrice()));

            isPaid.setChecked(order.getIsPaid());
        }

        modify.setOnClickListener(this);
        back.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(modify.isPressed()){
            quantityValue = quantity.getText().toString();
            priceValue = price.getText().toString();
            isPaidValue = isPaid.isChecked();

            if(quantityValue.contentEquals("")){
                Toast.makeText(getApplicationContext(), "Le champs quantité ne peut pas être vide !", Toast.LENGTH_LONG).show();
            }else{
                //on met a 0 le prix si le champ est vide ou nulle sinon le valeur
                if(priceValue.contentEquals("") || priceValue.contentEquals("0")){
                    order.setPrice(0);
                }else{
                    order.setPrice(Double.valueOf(priceValue));
                }

                order.setDate(new Date());

                //on teste ici pour la modification du produit si il y a assez de quantité dans le stock
                if(productDAO.modifyQuantity(order.getIdProduct(), Integer.valueOf(quantityValue), oldQuantity)==true){
                    order.setQuantity(Integer.valueOf(quantityValue));
                    order.setIsPaid(isPaidValue);

                    if(orderDAO.update(order)== true){
                        Log.i("AppInfo", order.toString());
                        Toast.makeText(getApplicationContext(), "Order modified !", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Order already exists !", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Insufficient quantity !", Toast.LENGTH_LONG).show();
                }

            }
        }else if(back.isPressed()){
            Intent intent = new Intent(modifyOrder.this, OrderActivity.class);
            startActivity(intent);
            productDAO.close();
            orderDAO.close();
            this.finish();
        }else if(delete.isPressed()){
            orderDAO.delete(order.getId());
            Toast.makeText(getApplicationContext(), "Order deleted !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(modifyOrder.this, OrderActivity.class);
            startActivity(intent);
            productDAO.close();
            orderDAO.close();
            this.finish();
        }
    }


}
