package com.example.shuttlesmgmt.activity.Version2.DBActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.shuttlesmgmt.DAOImplements.SupplierDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddCustomer;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddOrder;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddProduct;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddSupplier;
import com.example.shuttlesmgmt.activity.Version2.modifyActivity.modifySupplier;
import com.example.shuttlesmgmt.adapter.Version2.SupplierAdapter;
import com.example.shuttlesmgmt.entity.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierActivity extends AppCompatActivity {
    private ListView lv;
    List<Supplier> listSupplier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);
        lv = (ListView) findViewById(R.id.id_listSupplier);
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl(SupplierActivity.this);
        supplierDAO.openRead();
        listSupplier = new ArrayList<>();
        listSupplier = supplierDAO.fetchAll();
        SupplierAdapter supplierAdapter = new SupplierAdapter(this, listSupplier);
        lv.setAdapter(supplierAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), modifySupplier.class);
                intent.putExtra("supplierInfo", listSupplier.get(position).getId());
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
        Intent intent = new Intent(SupplierActivity.this, CustomerActivity.class);
        startActivity(intent);
    }

    public void onClickOrder(){
        Intent intent = new Intent(SupplierActivity.this, OrderActivity.class);
        startActivity(intent);
    }

    public void onClickSupplier(){
        Intent intent = new Intent(SupplierActivity.this, SupplierActivity.class);
        startActivity(intent);
    }

    public void onClickProduct(){
        Intent intent = new Intent(SupplierActivity.this, ProductActivity.class);
        startActivity(intent);
    }

    public void onClickAddProduct(){
        Intent intent = new Intent(SupplierActivity.this, AddProduct.class);
        startActivity(intent);
    }

    public void onClickAddCustomer(){
        Intent intent = new Intent(SupplierActivity.this, AddCustomer.class);
        startActivity(intent);
    }
    public void onClickAddSupplier(){
        Intent intent = new Intent(SupplierActivity.this, AddSupplier.class);
        startActivity(intent);
    }
    public void onClickAddOrder(){
        Intent intent = new Intent(SupplierActivity.this, AddOrder.class);
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
                onClickOrder();
                this.finish();
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
