package com.example.shuttlesmgmt.activity.Version2.modifyActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shuttlesmgmt.DAOImplements.SupplierDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.activity.Version2.DBActivity.SupplierActivity;
import com.example.shuttlesmgmt.entity.Version2.Supplier;

import java.util.ArrayList;
import java.util.List;

public class modifySupplier extends Activity implements View.OnClickListener {

    private List<Supplier> listSupplier;
    private Supplier Supplier;
    private EditText name, address, phone;
    private Button modify, back, delete;
    private SupplierDAOImpl SupplierDAO;
    private Intent intent;
    private String nameValue, addressValue, phoneValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_supplier);

        intent = getIntent();

        SupplierDAO = new SupplierDAOImpl(this);

        SupplierDAO.getDBWrite();

        name = (EditText) findViewById(R.id.id_ETname);
        address = (EditText) findViewById(R.id.id_ETaddress);
        phone = (EditText) findViewById(R.id.id_ETphone);

        modify = (Button) findViewById(R.id.id_modify);
        back = (Button) findViewById(R.id.id_back);
        delete = (Button) findViewById(R.id.id_delete);

        listSupplier = new ArrayList<Supplier>();
        listSupplier = SupplierDAO.fetchAll();

        if(intent.getLongExtra("supplierInfo", -1) != -1){
            Long id = intent.getLongExtra("supplierInfo", -1);

            Supplier = listSupplier.get(id.intValue()-1);
            name.setText(Supplier.getName());
            address.setText(Supplier.getAdd());
            phone.setText(Supplier.getPhone());
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
                Supplier.setName(nameValue);
                Supplier.setAdd(addressValue);
                Supplier.setPhone(phoneValue);
                if(SupplierDAO.update(Supplier)== true){
                    Log.i("AppInfo", Supplier.toString());
                    Toast.makeText(getApplicationContext(), "Supplier modified !", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Supplier already exists !", Toast.LENGTH_LONG).show();
                }
            }
        }else if(back.isPressed()){
            Intent intent = new Intent(modifySupplier.this, SupplierActivity.class);
            startActivity(intent);
            SupplierDAO.close();
            this.finish();
        }else if(delete.isPressed()){
            SupplierDAO.delete(Supplier.getId());
            Toast.makeText(getApplicationContext(), "Supplier deleted !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(modifySupplier.this, SupplierActivity.class);
            startActivity(intent);
            SupplierDAO.close();
            this.finish();
        }
    }
}

