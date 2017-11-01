package com.example.shuttlesmgmt.activity.DBActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shuttlesmgmt.DAOImplements.SupplierDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.db.ShuttlesSchema;
import com.example.shuttlesmgmt.entity.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);

        SupplierDAOImpl supplierDAO = new SupplierDAOImpl(SupplierActivity.this);
        supplierDAO.openRead();
        List<Supplier> listSupplier = new ArrayList<>();
        listSupplier = supplierDAO.fetchAll();
        //listview
    }
}
