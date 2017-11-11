package com.example.shuttlesmgmt;

/**
 * Created by Barrak on 27/10/2017.
 */

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.shuttlesmgmt.SQLiteDatabase.Achat;
import com.example.shuttlesmgmt.SQLiteDatabase.DatabaseHelper;
import com.example.shuttlesmgmt.SQLiteDatabase.Stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FormFragment extends Fragment {

    private Spinner Sourcespinner;
    private EditText editView_customer_name;
    private String customer_name;
    private EditText editView_customer_quantity;
    private CheckBox editView_customer_checkbox;
    Button validation_button;
    String tube_reference;
    String tube_grade;
    String quantity;
    Integer total_price;
    boolean achat_status;
    Integer status;

    String tube_reference_grade;
    String[] parts;

    DatabaseHelper db;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*super.onCreateView(inflater,container,savedInstanceState);*/
        View view = inflater.inflate(R.layout.fragment_sign, container, false);

        ArrayList<String> SourceArray = new ArrayList<String>();

        Sourcespinner = (Spinner)view.findViewById(R.id.SpinnerFeedbackType);

        ArrayAdapter<String> SourceArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.simple_row_spinner,SourceArray);

        //reading and displaying all achats
        db = new DatabaseHelper(getActivity());
        List<Stock> stocks = db.getAllStock();

        for (Stock a : stocks) {
            String log = "ID " + a.getId() + " Reference : " + a.getReference() + " Grade " + a.getGrade()
                    + " Quantity " + a.getQuantity() + " Unit price " + a.getUnitPrice() + "\n";
            SourceArrayAdapter.add(a.getReference());

        }

        Sourcespinner.setAdapter(SourceArrayAdapter);

        Collections.sort(SourceArray);

        editView_customer_name=(EditText)view.findViewById(R.id.EditTextName);
        editView_customer_quantity=(EditText)view.findViewById(R.id.editTextqte);
        editView_customer_checkbox=(CheckBox)view.findViewById(R.id.CheckBoxResponse);
        validation_button=(Button)view.findViewById(R.id.ButtonSendFeedback);


        validation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("Gestion de Saisie", "onClick: ");
                tube_reference = Sourcespinner.getSelectedItem().toString();
                Stock TubeChosen = db.getStockByReference(tube_reference);
                Integer qteMaxToChoose = TubeChosen.getQuantity();

                Integer qteChosen = 0;
                if (editView_customer_quantity.getText().toString().equals("")==false)
                    qteChosen = Integer.parseInt(editView_customer_quantity.getText().toString());

                if (editView_customer_name.getText().toString().equals("")){
                    toastMessage("Customer not mentioned");
                    Log.i("Gestion de Saisie", "Customer name: ");
                }
                else if (editView_customer_quantity.getText().toString().equals("")){
                    toastMessage("Quantity not mentioned");
                    Log.i("Gestion de Saisie", "Quantity: ");
                }
                else if (qteChosen>qteMaxToChoose){
                    toastMessage("Quantity is higher than Maximum "+qteMaxToChoose);
                }
                else
                {
                    Log.i("Gestion de Saisie", "BONNE: ");
                customer_name = editView_customer_name.getText().toString();
                tube_reference = Sourcespinner.getSelectedItem().toString();

                quantity = editView_customer_quantity.getText().toString();

                Stock s = db.getStockByReference(tube_reference);
                total_price = Integer.parseInt(quantity) * s.getUnitPrice();

                achat_status = editView_customer_checkbox.isChecked();
                if (achat_status)
                    status = 1;
                else
                    status = 0;
                db.addAchat(new Achat(customer_name, tube_reference, Integer.parseInt(quantity), total_price, status));
                toastMessage("Achat Enregistré avec succés");
                Fragment fragment = new AchatFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                }


            }
        });




        return view;
    }
    public void addData (Achat achat){
        DatabaseHelper db = new DatabaseHelper(getActivity());
        db.addAchat(achat);
        toastMessage("Achat Sauvegardé avec succés");

    }
    private void toastMessage(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

}
