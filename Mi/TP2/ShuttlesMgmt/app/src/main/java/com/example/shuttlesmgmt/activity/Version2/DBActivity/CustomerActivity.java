package com.example.shuttlesmgmt.activity.Version2.DBActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.shuttlesmgmt.DAOImplements.CustomerDAOImpl;
import com.example.shuttlesmgmt.activity.Version2.modifyActivity.modifyCustomer;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddCustomer;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddOrder;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddProduct;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddSupplier;
import com.example.shuttlesmgmt.adapter.Version2.CustomerAdapter;
import com.example.shuttlesmgmt.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends AppCompatActivity{
    private ListView lv;
    private  List<Customer> listCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        lv = (ListView) findViewById(R.id.id_listCustomer);

        CustomerDAOImpl customerDAO = new CustomerDAOImpl(CustomerActivity.this);
        customerDAO.openRead();
        listCustomer = new ArrayList<Customer>();
        listCustomer = customerDAO.fetchAll();
        CustomerAdapter customerAdapter = new CustomerAdapter(this, listCustomer);
        lv.setAdapter(customerAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), modifyCustomer.class);
                intent.putExtra("customerInfo", listCustomer.get(position).getId());
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Ajout les entrés de menu_test à l'actionbar
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    public void onClickCustomer(){
        Intent intent = new Intent(CustomerActivity.this, CustomerActivity.class);
        startActivity(intent);
    }

    public void onClickOrder(){
        Intent intent = new Intent(CustomerActivity.this, OrderActivity.class);
        startActivity(intent);
    }

    public void onClickSupplier(){
        Intent intent = new Intent(CustomerActivity.this, SupplierActivity.class);
        startActivity(intent);
    }

    public void onClickProduct(){
        Intent intent = new Intent(CustomerActivity.this, ProductActivity.class);
        startActivity(intent);
    }

    public void onClickAddProduct(){
        Intent intent = new Intent(CustomerActivity.this, AddProduct.class);
        startActivity(intent);
    }

    public void onClickAddCustomer(){
        Intent intent = new Intent(CustomerActivity.this, AddCustomer.class);
        startActivity(intent);
    }
    public void onClickAddSupplier(){
        Intent intent = new Intent(CustomerActivity.this, AddSupplier.class);
        startActivity(intent);
    }
    public void onClickAddOrder(){
        Intent intent = new Intent(CustomerActivity.this, AddOrder.class);
        startActivity(intent);
    }

    //gere les clics des items du menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
