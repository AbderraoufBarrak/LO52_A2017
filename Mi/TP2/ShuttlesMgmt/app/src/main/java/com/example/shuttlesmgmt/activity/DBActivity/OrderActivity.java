package com.example.shuttlesmgmt.activity.DBActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shuttlesmgmt.DAOImplements.OrderDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        OrderDAOImpl orderDAO = new OrderDAOImpl(OrderActivity.this);
        orderDAO.openRead();
        List<Order> listCustomer = new ArrayList<>();
        listCustomer = orderDAO.fetchAll();
        //listview
    }
}
