package com.example.shuttlesmgmt.activity.Version2.DBActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.shuttlesmgmt.DAOImplements.OrderDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddCustomer;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddOrder;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddProduct;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddSupplier;
import com.example.shuttlesmgmt.adapter.Version2.OrderAdapter;
import com.example.shuttlesmgmt.entity.Order;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity{
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        lv = (ListView) findViewById(R.id.id_listOrder);
        OrderDAOImpl orderDAO = new OrderDAOImpl(OrderActivity.this);
        orderDAO.openRead();
        List<Order> listOrder = new ArrayList<>();
        try {
            listOrder = orderDAO.fetchAll2();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        OrderAdapter orderAdapter = new OrderAdapter(this, listOrder);
        lv.setAdapter(orderAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Ajout les entrés de menu_test à l'actionbar
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    public void onClickCustomer(){
        Intent intent = new Intent(OrderActivity.this, CustomerActivity.class);
        startActivity(intent);
    }

    public void onClickOrder(){
        Intent intent = new Intent(OrderActivity.this, OrderActivity.class);
        startActivity(intent);
    }

    public void onClickSupplier(){
        Intent intent = new Intent(OrderActivity.this, SupplierActivity.class);
        startActivity(intent);
    }

    public void onClickProduct(){
        Intent intent = new Intent(OrderActivity.this, ProductActivity.class);
        startActivity(intent);
    }

    public void onClickAddProduct(){
        Intent intent = new Intent(OrderActivity.this, AddProduct.class);
        startActivity(intent);
    }

    public void onClickAddCustomer(){
        Intent intent = new Intent(OrderActivity.this, AddCustomer.class);
        startActivity(intent);
    }
    public void onClickAddSupplier(){
        Intent intent = new Intent(OrderActivity.this, AddSupplier.class);
        startActivity(intent);
    }
    public void onClickAddOrder(){
        Intent intent = new Intent(OrderActivity.this, AddOrder.class);
        startActivity(intent);
    }

    //gere les clics des items du menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_customer:
                onClickCustomer();
                this.finish();
                return true;
            case R.id.action_order:
                this.finish();
                onClickOrder();
                return true;
            case R.id.action_product:
                onClickProduct();
                this.finish();
                return true;
            case R.id.action_supplier:
                onClickSupplier();
                this.finish();
                return true;
            case R.id.addCustomer:
                onClickAddCustomer();
                this.finish();
                return true;
            case R.id.addProduct:
                onClickAddProduct();
                this.finish();
                return true;
            case R.id.addSupplier:
                onClickAddSupplier();
                this.finish();
                return true;
            case R.id.addOrder:
                onClickAddOrder();
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
