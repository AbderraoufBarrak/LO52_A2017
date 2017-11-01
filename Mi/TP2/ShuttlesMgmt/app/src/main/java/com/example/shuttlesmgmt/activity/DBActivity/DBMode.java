package com.example.shuttlesmgmt.activity.DBActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.shuttlesmgmt.DAOImplements.CustomerDAOImpl;
import com.example.shuttlesmgmt.DAOImplements.OrderDAOImpl;
import com.example.shuttlesmgmt.DAOImplements.ProductDAOImpl;
import com.example.shuttlesmgmt.DAOImplements.SupplierDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.activity.addActivity.AddCustomer;
import com.example.shuttlesmgmt.activity.addActivity.AddOrder;
import com.example.shuttlesmgmt.activity.addActivity.AddProduct;
import com.example.shuttlesmgmt.activity.addActivity.AddSupplier;
import com.example.shuttlesmgmt.entity.Customer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DBMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbmode);

        CustomerDAOImpl customerDAO = new CustomerDAOImpl(DBMode.this);
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl(DBMode.this);
        OrderDAOImpl orderDAO = new OrderDAOImpl(DBMode.this);
        ProductDAOImpl productDAO = new ProductDAOImpl(DBMode.this);
        customerDAO.openWrite();
        supplierDAO.openWrite();
        orderDAO.openWrite();
        productDAO.openWrite();

        if(supplierDAO.isEmpty()){
            supplierDAO.readData(R.raw.data_supplier, DBMode.this);
        }
        if(productDAO.isEmpty()){
            productDAO.readData(R.raw.data_product, DBMode.this);
        }
        if(customerDAO.isEmpty()) {
            customerDAO.readData(R.raw.data_customer, DBMode.this);
        }
        if(orderDAO.isEmpty()){
            if(customerDAO.isEmpty()==false && productDAO.isEmpty() == false){
                orderDAO.readData(R.raw.data_order, DBMode.this);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Ajout les entrés de menu_test à l'actionbar
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    public void onClickCustomer(){
        Intent intent = new Intent(DBMode.this, CustomerActivity.class);
        startActivity(intent);
    }

    public void onClickOrder(){
        Intent intent = new Intent(DBMode.this, OrderActivity.class);
        startActivity(intent);
    }

    public void onClickSupplier(){
        Intent intent = new Intent(DBMode.this, SupplierActivity.class);
        startActivity(intent);
    }

    public void onClickProduct(){
        Intent intent = new Intent(DBMode.this, ProductActivity.class);
        startActivity(intent);
    }

    public void onClickAddProduct(){
        Intent intent = new Intent(DBMode.this, AddProduct.class);
        startActivity(intent);
    }

    public void onClickAddCustomer(){
        Intent intent = new Intent(DBMode.this, AddCustomer.class);
        startActivity(intent);
    }
    public void onClickAddSupplier(){
        Intent intent = new Intent(DBMode.this, AddSupplier.class);
        startActivity(intent);
    }
    public void onClickAddOrder(){
        Intent intent = new Intent(DBMode.this, AddOrder.class);
        startActivity(intent);
    }

    //gere le click sur une action de l'actionbar
    public boolean onOptionsItemsSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_customer:
                onClickCustomer();
                return true;
            case R.id.action_order:
                onClickOrder();
                return true;
            case R.id.action_product:
                onClickProduct();
                return true;
            case R.id.action_supplier:
                onClickSupplier();
                return true;
            case R.id.addCustomer:
                onClickAddCustomer();
                return true;
            case R.id.addProduct:
                onClickAddProduct();
                return true;
            case R.id.addSupplier:
                onClickAddSupplier();
                return true;
            case R.id.addOrder:
                onClickAddOrder();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
