package com.example.shuttlesmgmt.activity.DBActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shuttlesmgmt.DAOImplements.CustomerDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        CustomerDAOImpl customerDAO = new CustomerDAOImpl(CustomerActivity.this);
        customerDAO.openRead();
        List<Customer> listCustomer = new ArrayList<>();
        listCustomer = customerDAO.fetchAll();
        //listview
    }
}
