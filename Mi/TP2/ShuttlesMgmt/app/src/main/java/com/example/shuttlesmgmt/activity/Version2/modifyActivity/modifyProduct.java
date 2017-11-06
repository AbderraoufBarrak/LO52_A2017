package com.example.shuttlesmgmt.activity.Version2.modifyActivity;

import android.app.Activity;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class modifyProduct extends Activity implements View.OnClickListener {

    private List<Product> listProduct;
    private List<String> listSupplierName;
    private EditText name, ref,quantity, price;
    private Button modify, back, delete;
    private ProductDAOImpl productDAO;
    private SupplierDAOImpl supplierDAO;
    private ArrayAdapter<String> spinnerAdapter;
    private Intent intent;
    private String supplierValue, nameValue, refValue, quantityValue, priceValue;
    private Spinner sp;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_product);

        intent = getIntent();

        supplierDAO = new SupplierDAOImpl(this);
        productDAO = new ProductDAOImpl(this);

        productDAO.getDBRead();
        supplierDAO.getDBRead();

        product = new Product();

        sp = (Spinner) findViewById(R.id.id_sp);

        name = (EditText) findViewById(R.id.id_ETname);
        ref = (EditText) findViewById(R.id.id_ETref);
        quantity = (EditText) findViewById(R.id.id_ETquantity);
        price = (EditText) findViewById(R.id.id_ETprice);

        modify = (Button) findViewById(R.id.id_modify);
        back = (Button) findViewById(R.id.id_back);
        delete = (Button) findViewById(R.id.id_delete);

        sp.setAdapter(spinnerAdapter);

        listProduct = new ArrayList<>();
        listProduct = productDAO.fetchAll();

        listSupplierName = new ArrayList<>();
        listSupplierName = supplierDAO.getListSuppliersName();

        spinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, listSupplierName);
        sp.setAdapter(spinnerAdapter);

        if(intent.getLongExtra("productInfo", -1) != -1){
            Long id = intent.getLongExtra("productInfo", -1);

            product = listProduct.get(id.intValue()-1);

            name.setText(product.getName());
            ref.setText(product.getRef());
            quantity.setText(Integer.toString(product.getQuantity()));
            price.setText(Double.toString(product.getPrice()));

            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int pos = 0;
                    while(parent.getItemAtPosition(pos).toString().equals(product.getSupplierName())==false){
                        //Log.i("AppInfo", "FALSE , " + parent.getItemAtPosition(pos).toString() + " , " + product.getSupplierName());
                        pos++;
                    }
                    parent.setSelection(pos);
                    //Log.i("AppInfo", parent.getItemAtPosition(pos).toString() + " , " + product.getSupplierName() + " , " + pos);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        modify.setOnClickListener(this);
        back.setOnClickListener(this);
        delete.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(modify.isPressed()){
            supplierValue = sp.getSelectedItem().toString();
            nameValue = name.getText().toString();
            refValue = ref.getText().toString();
            quantityValue = quantity.getText().toString();
            priceValue = price.getText().toString();

            if(quantityValue.contentEquals("") || nameValue.contentEquals("")||refValue.contentEquals("") || priceValue.contentEquals("")){
                Toast.makeText(getApplicationContext(), "Les champs ne peuvent pas être vide !", Toast.LENGTH_LONG).show();
            }else{
                if(priceValue.contentEquals("0")){
                    Toast.makeText(getApplicationContext(), "Le champ prix ne peut pas être 0" , Toast.LENGTH_LONG).show();
                }else{
                    product.setPrice(Double.valueOf(priceValue));
                }

                if(productDAO.update(product)== true){
                    Log.i("AppInfo", product.toString());
                    Toast.makeText(getApplicationContext(), "Order modified !", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Order already existed !", Toast.LENGTH_LONG).show();
                }
            }

        }else if(back.isPressed()){
            Intent intent = new Intent(modifyProduct.this, ProductActivity.class);
            startActivity(intent);
            productDAO.close();
            supplierDAO.close();
            this.finish();
        }else if(delete.isPressed()){
            productDAO.delete(product.getId());
            Toast.makeText(getApplicationContext(), "Product deleted !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(modifyProduct.this, ProductActivity.class);
            startActivity(intent);
            productDAO.close();
            supplierDAO.close();
            this.finish();
        }
    }
}
