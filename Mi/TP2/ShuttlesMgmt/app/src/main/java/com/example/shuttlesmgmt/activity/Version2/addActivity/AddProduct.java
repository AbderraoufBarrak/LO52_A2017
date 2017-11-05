package com.example.shuttlesmgmt.activity.Version2.addActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shuttlesmgmt.DAOImplements.ProductDAOImpl;
import com.example.shuttlesmgmt.DAOImplements.SupplierDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.activity.Version2.DBActivity.ProductActivity;
import com.example.shuttlesmgmt.entity.Product;
import com.example.shuttlesmgmt.entity.Supplier;

import java.util.ArrayList;
import java.util.List;

public class AddProduct extends Activity implements View.OnClickListener {

    private ProductDAOImpl productDAO;
    private SupplierDAOImpl supplierDAO;
    private Product product = new Product();
    private ArrayAdapter<String> spinnerAdapter;
    private EditText name, ref, quantity, price;
    private Spinner sp;
    private Button add, back;
    private List<String> listSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        supplierDAO = new SupplierDAOImpl(this);
        productDAO = new ProductDAOImpl(this);
        productDAO.getDBWrite();
        supplierDAO.getDBRead();
        sp = (Spinner) findViewById(R.id.id_sp);
        name = (EditText) findViewById(R.id.id_ETname);
        ref = (EditText) findViewById(R.id.id_ETref);
        quantity = (EditText) findViewById(R.id.id_ETquantity);
        price = (EditText) findViewById(R.id.id_ETprice);
        add = (Button) findViewById(R.id.id_add);
        back = (Button) findViewById(R.id.id_back);

        sp.setAdapter(spinnerAdapter);
        listSupplier = new ArrayList<String>();
        listSupplier = supplierDAO.getListSuppliersName();
        spinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, listSupplier);
        sp.setAdapter(spinnerAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        add.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(add.isPressed()){
            String nameValue = name.getText().toString();
            String refValue = ref.getText().toString();
            String quantityValue = quantity.getText().toString();
            String priceValue = price.getText().toString();
            String supplierValue = sp.getSelectedItem().toString();

            if(nameValue.contentEquals("") || refValue.contentEquals("") || quantityValue.contentEquals("") || priceValue.contentEquals("")){
                Toast.makeText(getApplicationContext(), "Les champs ne peuvent pas être vide !", Toast.LENGTH_LONG).show();
            }else{
                product.setSupplierName(supplierValue);
                product.setName(nameValue);
                product.setRef(refValue);
                product.setQuantity(Integer.valueOf(quantityValue));
                product.setPrice(Double.valueOf(priceValue));
                if(supplierDAO.fetchByName(supplierValue) != 0){
                    product.setIdSupplier(supplierDAO.fetchByName(supplierValue));
                    product.setImage("");
                    if(productDAO.create(product)==true){
                        name.setText("");
                        ref.setText("");
                        quantity.setText("");
                        price.setText("");
                        Toast.makeText(getApplicationContext(), "New Product added !", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Product already existed !", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "ID supplier n'est pas correct revoir le code !", Toast.LENGTH_LONG).show();
                }
            }
        }else if(back.isPressed()){
                Intent intent = new Intent(AddProduct.this, ProductActivity.class);
                startActivity(intent);
                this.finish();
                productDAO.close();
                supplierDAO.close();
        }
    }

}
