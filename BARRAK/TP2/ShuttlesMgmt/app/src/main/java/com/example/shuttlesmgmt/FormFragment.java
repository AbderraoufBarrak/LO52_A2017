package com.example.shuttlesmgmt;

/**
 * Created by Barrak on 27/10/2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;


public class FormFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*super.onCreateView(inflater,container,savedInstanceState);*/
        View view = inflater.inflate(R.layout.fragment_sign, container, false);

        ArrayList<String> SourceArray = new ArrayList<String>();

        Spinner Sourcespinner = (Spinner)view.findViewById(R.id.SpinnerFeedbackType);

        ArrayAdapter<String> SourceArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.simple_row_spinner,SourceArray);

        SourceArrayAdapter.add("Chennai");
        SourceArrayAdapter.add("Mumbai");
        SourceArrayAdapter.add("Kolkatta");
        SourceArrayAdapter.add("Delhi");

        Sourcespinner.setAdapter(SourceArrayAdapter);

        Collections.sort(SourceArray);


        return view;
    }

}
