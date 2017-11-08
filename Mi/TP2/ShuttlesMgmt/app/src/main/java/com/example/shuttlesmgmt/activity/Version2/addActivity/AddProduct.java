package com.example.shuttlesmgmt.activity.Version2.addActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shuttlesmgmt.DAOImplements.ProductDAOImpl;
import com.example.shuttlesmgmt.DAOImplements.SupplierDAOImpl;
import com.example.shuttlesmgmt.R;
import com.example.shuttlesmgmt.activity.Version2.DBActivity.ProductActivity;
import com.example.shuttlesmgmt.entity.Version2.Product;

import java.util.ArrayList;
import java.util.List;

public class AddProduct extends Activity implements View.OnClickListener {

    private ProductDAOImpl productDAO;
    private SupplierDAOImpl supplierDAO;
    private Product product = new Product();
    private ArrayAdapter<String> spinnerAdapter;
    private EditText name, ref, quantity, price;
    private Spinner sp;
    private Button add, back, addNewSupplier;
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
        addNewSupplier = (Button) findViewById(R.id.id_addSupplier);

        sp.setAdapter(spinnerAdapter);
        listSupplier = new ArrayList<String>();
        listSupplier = supplierDAO.getListSuppliersName();
        spinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, listSupplier);
        sp.setAdapter(spinnerAdapter);

        add.setOnClickListener(this);
        back.setOnClickListener(this);
        addNewSupplier.setOnClickListener(this);
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

                //on teste si l'id du supplier n'est pas nulle car dans le db on commence par 1
                if(supplierDAO.fetchByName(supplierValue) != 0){
                    product.setIdSupplier(supplierDAO.fetchByName(supplierValue));
                    product.setImage("");

                    //on teste si le produit à bien été créé sinon le produit existe deja dans le db
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
        }else if(addNewSupplier.isPressed()){
            Intent intent = new Intent(AddProduct.this, AddSupplier.class);
            startActivity(intent);
            this.finish();
            productDAO.close();
            supplierDAO.close();
        }
    }

}
