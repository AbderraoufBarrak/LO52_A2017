package com.example.shuttlesmgmt;

/**
 * Created by Barrak on 11/11/2017.
 */

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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


public class ReadOnlyFormFragment extends Fragment {

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
        String[] listFromActivity = getArguments().getStringArray("my_list_key");

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        editView_customer_name=(EditText)view.findViewById(R.id.EditTextName);
        editView_customer_name.setText(listFromActivity[0]);
        editView_customer_name.setEnabled(false);

        editView_customer_quantity=(EditText)view.findViewById(R.id.editTextqte);
        editView_customer_quantity.setText(listFromActivity[2]);
        editView_customer_quantity.setEnabled(false);

        ArrayList<String> SourceArray = new ArrayList<String>();
        Sourcespinner = (Spinner)view.findViewById(R.id.SpinnerFeedbackType);
        ArrayAdapter<String> SourceArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.simple_row_spinner,SourceArray);
        SourceArrayAdapter.add(listFromActivity[1]);
        Sourcespinner.setAdapter(SourceArrayAdapter);
        Sourcespinner.setEnabled(false);

        editView_customer_checkbox=(CheckBox)view.findViewById(R.id.CheckBoxResponse);
        if (listFromActivity[3]=="Payé")
            editView_customer_checkbox.setChecked(true);
        else
            editView_customer_checkbox.setChecked(false);
        editView_customer_checkbox.setEnabled(false);





        return view;
    }



}
