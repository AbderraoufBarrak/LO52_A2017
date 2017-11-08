package com.example.shuttlesmgmt.activity.Version2.addActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shuttlesmgmt.DAOImplements.SupplierDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.activity.Version2.DBActivity.SupplierActivity;
import com.example.shuttlesmgmt.entity.Version2.Supplier;

public class AddSupplier extends Activity implements View.OnClickListener {

    private EditText name, address, phone ;
    private Button add, back;

    private SupplierDAOImpl supplierDAO;
    private Supplier supplier = new Supplier();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);

        supplierDAO = new SupplierDAOImpl(this);

        supplierDAO.getDBWrite();

        name = (EditText) findViewById(R.id.id_ETname);
        address = (EditText) findViewById(R.id.id_ETaddress);
        phone = (EditText) findViewById(R.id.id_ETphone);

        add = (Button) findViewById(R.id.id_add);
        back = (Button) findViewById(R.id.id_back);

        add.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(add.isPressed()){
            String nameValue = name.getText().toString();
            String addressValue = address.getText().toString();
            String phoneValue = phone.getText().toString();

            if(nameValue.contentEquals("") || addressValue.contentEquals("") || phoneValue.contentEquals("")){
                Toast.makeText(getApplicationContext(), "Les champs ne peuvent pas être vide !", Toast.LENGTH_LONG).show();
            }else{
                supplier.setName(nameValue);
                supplier.setAdd(addressValue);
                supplier.setPhone(phoneValue);
                //on teste si le fabricant à bien été créé sinon il existe deja
                if( supplierDAO.create(supplier) == true){
                    name.setText("");
                    address.setText("");
                    phone.setText("");
                    Toast.makeText(getApplicationContext(), "New Supplier added !", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Supplier already existed !", Toast.LENGTH_LONG).show();
                }
            }
        }else{
            Intent intent = new Intent(AddSupplier.this, SupplierActivity.class);
            startActivity(intent);
            this.finish();
            supplierDAO.close();
        }
    }

}
