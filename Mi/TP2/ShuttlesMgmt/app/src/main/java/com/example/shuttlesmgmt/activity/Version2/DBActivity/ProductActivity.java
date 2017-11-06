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

import com.example.shuttlesmgmt.DAOImplements.ProductDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddCustomer;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddOrder;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddProduct;
import com.example.shuttlesmgmt.activity.Version2.addActivity.AddSupplier;
import com.example.shuttlesmgmt.activity.Version2.modifyActivity.modifyProduct;
import com.example.shuttlesmgmt.adapter.Version2.ProductAdapter;
import com.example.shuttlesmgmt.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private ListView lv;
    private ArrayAdapter<String> spAdapter;
    private Spinner sp;
    private  List<Product> listProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        lv = (ListView) findViewById(R.id.id_listProduct);
        sp = (Spinner) findViewById(R.id.id_spSort);

        ProductDAOImpl productDAO = new ProductDAOImpl(ProductActivity.this);
        productDAO.openRead();
        listProduct = new ArrayList<>();
        listProduct = productDAO.fetchAll();
        final ProductAdapter productAdapter = new ProductAdapter(this, listProduct);
        lv.setAdapter(productAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), modifyProduct.class);
                intent.putExtra("productInfo", listProduct.get(position).getId());
                startActivity(intent);
            }
        });

        spAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.sortTypeProduct));
        sp.setAdapter(spAdapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Price")){
                    listProduct = productAdapter.sortList(listProduct, 1);
                }else if(parent.getItemAtPosition(position).equals("Quantity")){
                    listProduct = productAdapter.sortList(listProduct, 0);
                }else if(parent.getItemAtPosition(position).equals("Name")){
                    listProduct = productAdapter.sortList(listProduct, 3);
                }else if((parent.getItemAtPosition(position).equals("Reference"))){
                    listProduct = productAdapter.sortList(listProduct, 2);
                }
                lv.setAdapter(productAdapter);
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
