package com.example.shuttlesmgmt.activity.Version2.DBActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.shuttlesmgmt.DAOImplements.OrderDAOImpl;
import com.example.shuttlesmgmt.activity.Version2.modifyActivity.modifyOrder;
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
    private  List<Order> listOrder;
    private Spinner sp;
    private ArrayAdapter<String> spAdapter;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        lv = (ListView) findViewById(R.id.id_listOrder);
        sp = (Spinner) findViewById(R.id.id_spSort);

        OrderDAOImpl orderDAO = new OrderDAOImpl(OrderActivity.this);
        orderDAO.openRead();
        listOrder = new ArrayList<>();
        try {
            listOrder = orderDAO.fetchAll2();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        orderAdapter = new OrderAdapter(this, listOrder);
        lv.setAdapter(orderAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), modifyOrder.class);
                intent.putExtra("orderInfo", listOrder.get(position).getId());
                startActivity(intent);
            }
        });

        spAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.sortTypeOrder));
        sp.setAdapter(spAdapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Quantity")){
                    listOrder = orderAdapter.sortList(listOrder, 1);
                }else if(parent.getItemAtPosition(position).equals("Price")){
                    listOrder = orderAdapter.sortList(listOrder, 0);
                }else if(parent.getItemAtPosition(position).equals("Customer Name")){
                    listOrder = orderAdapter.sortList(listOrder, 3);
                }else if((parent.getItemAtPosition(position).equals("Date"))){
                    listOrder = orderAdapter.sortList(listOrder, 2);
                }else if((parent.getItemAtPosition(position).equals("Product Name"))){
                    listOrder = orderAdapter.sortList(listOrder, 4);
                }else if((parent.getItemAtPosition(position).equals("isPaid"))){
                    listOrder = orderAdapter.sortList(listOrder, 5);
                }
                lv.setAdapter(orderAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
