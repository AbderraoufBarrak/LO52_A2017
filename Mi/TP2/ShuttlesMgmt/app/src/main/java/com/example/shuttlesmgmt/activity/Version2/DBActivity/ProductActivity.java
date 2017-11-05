package com.example.shuttlesmgmt.activity.Version2.DBActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.shuttlesmgmt.DAOImplements.ProductDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddCustomer;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddOrder;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddProduct;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddSupplier;
import com.example.shuttlesmgmt.adapter.Version2.ProductAdapter;
import com.example.shuttlesmgmt.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        lv = (ListView) findViewById(R.id.id_listProduct);
        ProductDAOImpl productDAO = new ProductDAOImpl(ProductActivity.this);
        productDAO.openRead();
        List<Product> listProduct = new ArrayList<>();
        listProduct = productDAO.fetchAll();
        ProductAdapter productAdapter = new ProductAdapter(this, listProduct);
        lv.setAdapter(productAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Ajout les entrés de menu_test à l'actionbar
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    public void onClickCustomer(){
        Intent intent = new Intent(ProductActivity.this, CustomerActivity.class);
        startActivity(intent);
    }

    public void onClickOrder(){
        Intent intent = new Intent(ProductActivity.this, OrderActivity.class);
        startActivity(intent);
    }

    public void onClickSupplier(){
        Intent intent = new Intent(ProductActivity.this, SupplierActivity.class);
        startActivity(intent);
    }

    public void onClickProduct(){
        Intent intent = new Intent(ProductActivity.this, ProductActivity.class);
        startActivity(intent);
    }

    public void onClickAddProduct(){
        Intent intent = new Intent(ProductActivity.this, AddProduct.class);
        startActivity(intent);
    }

    public void onClickAddCustomer(){
        Intent intent = new Intent(ProductActivity.this, AddCustomer.class);
        startActivity(intent);
    }
    public void onClickAddSupplier(){
        Intent intent = new Intent(ProductActivity.this, AddSupplier.class);
        startActivity(intent);
    }
    public void onClickAddOrder(){
        Intent intent = new Intent(ProductActivity.this, AddOrder.class);
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
