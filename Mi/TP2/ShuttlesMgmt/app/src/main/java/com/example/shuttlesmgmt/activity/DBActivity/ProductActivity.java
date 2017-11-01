package com.example.shuttlesmgmt.activity.DBActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shuttlesmgmt.DAOImplements.ProductDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ProductDAOImpl productDAO = new ProductDAOImpl(ProductActivity.this);
        productDAO.openRead();
        List<Product> listProduct = new ArrayList<>();
        listProduct = productDAO.fetchAll();
        //listview
    }
}
