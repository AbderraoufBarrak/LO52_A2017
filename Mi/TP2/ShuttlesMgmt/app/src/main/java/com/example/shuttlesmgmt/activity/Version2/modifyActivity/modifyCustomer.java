package com.example.shuttlesmgmt.activity.Version2.modifyActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shuttlesmgmt.DAOImplements.CustomerDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.activity.Version2.DBActivity.CustomerActivity;
import com.example.shuttlesmgmt.entity.Version2.Customer;

import java.util.ArrayList;
import java.util.List;

public class modifyCustomer extends Activity implements View.OnClickListener {

    private List<Customer> listCustomer;
    private Customer customer;
    private EditText name, address, phone;
    private Button modify, back, delete;
    private CustomerDAOImpl customerDAO;
    private Intent intent;
    private String nameValue, addressValue, phoneValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_customer);

        intent = getIntent();

        customerDAO = new CustomerDAOImpl(this);

        customerDAO.getDBWrite();

        name = (EditText) findViewById(R.id.id_ETname);
        address = (EditText) findViewById(R.id.id_ETaddress);
        phone = (EditText) findViewById(R.id.id_ETphone);

        modify = (Button) findViewById(R.id.id_modify);
        back = (Button) findViewById(R.id.id_back);
        delete = (Button) findViewById(R.id.id_delete);

        listCustomer = new ArrayList<Customer>();
        listCustomer = customerDAO.fetchAll();

        if(intent.getLongExtra("customerInfo", -1) != -1){
            Long id = intent.getLongExtra("customerInfo", -1);

            customer = listCustomer.get(id.intValue()-1);

            name.setText(customer.getName());
            address.setText(customer.getAdd());
            phone.setText(customer.getPhone());
        }

        modify.setOnClickListener(this);
        back.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(modify.isPressed()){
            nameValue = name.getText().toString();
            addressValue = address.getText().toString();
            phoneValue = phone.getText().toString();

            if(nameValue.contentEquals("") || addressValue.contentEquals("")|| phoneValue.contentEquals("")){
                Toast.makeText(getApplicationContext(), "Les champs ne peuvent pas être vide !", Toast.LENGTH_LONG).show();
            }else{
                customer.setName(nameValue);
                customer.setAdd(addressValue);
                customer.setPhone(phoneValue);

                if(customerDAO.update(customer)== true){
                    Log.i("AppInfo", customer.toString());
                    Toast.makeText(getApplicationContext(), "Customer modified !", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Customer already exists !", Toast.LENGTH_LONG).show();
                }
            }
        }else if(back.isPressed()){
            Intent intent = new Intent(modifyCustomer.this, CustomerActivity.class);
            startActivity(intent);
            customerDAO.close();
            this.finish();
        }else if(delete.isPressed()){
            customerDAO.delete(customer.getId());
            Toast.makeText(getApplicationContext(), "Customer deleted !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(modifyCustomer.this, CustomerActivity.class);
            startActivity(intent);
            customerDAO.close();
            this.finish();
        }
    }
}
