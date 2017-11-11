package com.example.shuttlesmgmt;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shuttlesmgmt.SQLiteDatabase.Achat;
import com.example.shuttlesmgmt.SQLiteDatabase.DatabaseHelper;
import com.example.shuttlesmgmt.SQLiteDatabase.Stock;

import java.util.ArrayList;
import java.util.List;

public class StockFragment extends Fragment {

    List<Integer> IMAGES = new ArrayList<Integer>();
    List<String> REFERENCES = new ArrayList<String>();
    List<String> GRADES = new ArrayList<String>();
    List<Integer> QUANTITIES = new ArrayList<Integer>();
    List<Integer> UNITPRICES = new ArrayList<Integer>();


    /*@Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_stock);

        ListView list = (ListView)findViewById(R.id.listView);
        CustomAdapter customAdapter= new CustomAdapter();
        list.setAdapter(customAdapter);

    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /////////////////////////////////////
        //testing table Achat
        DatabaseHelper db = new DatabaseHelper(getActivity());
        //db.removeAll();
        //inserting achats
        //db.addStock(new Stock("Yonex","Grade AS30", 500,27));
        //db.addStock(new Stock("RSL","AS30", 5000,17));
        //db.addStock(new Stock("YRSL","Grade A9", 10000,30));
        //db.addStock(new Stock("NRSL","Grade A1", 6000,45));


        Log.i("Adding to Database", "Added successfully elements to database");

        //reading and displaying all achats
        List<Stock> stocks = db.getAllStock();

        for (Stock a : stocks) {
            String log = "ID " + a.getId() + " Reference : " + a.getReference() + " Grade " + a.getGrade()
                    + " Quantity " + a.getQuantity() + " Unit price " + a.getUnitPrice() + "\n";

            switch(a.getReference()) {
                case "RSL" :
                    IMAGES.add(R.drawable.t1);
                    break;
                case "NRSL" :
                    IMAGES.add(R.drawable.t2);
                    break;
                case "Yonex" :
                    IMAGES.add(R.drawable.t3);
                    break;
                default :
                    IMAGES.add(R.drawable.t4);
            }

            REFERENCES.add(a.getReference());
            GRADES.add(a.getGrade());
            QUANTITIES.add(a.getQuantity());
            UNITPRICES.add(a.getUnitPrice());
            Log.i("every stock", log);

        }
        ////////////////////////////////////

        /*super.onCreateView(inflater,container,savedInstanceState);*/
        View view = inflater.inflate(R.layout.fragment_stock, container, false);

        ListView list = (ListView)view.findViewById(R.id.listView);
        CustomAdapter customAdapter= new CustomAdapter();
        list.setAdapter(customAdapter);
        Log.i("onCreateView BARRAK", "Stock Fragment");
        return view;
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return IMAGES.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup ViewGroup) {
            view = getActivity().getLayoutInflater().inflate(R.layout.custom_tube_layout,null);
            ImageView imageView=(ImageView)view.findViewById(R.id.imageView);
            TextView textView_name=(TextView)view.findViewById(R.id.textView_name);
            TextView textView_grade=(TextView)view.findViewById(R.id.textView_grade);
            TextView textView_stock=(TextView)view.findViewById(R.id.textView_stock);
            TextView textView_prix=(TextView)view.findViewById(R.id.textView_prix);



            imageView.setImageResource(IMAGES.get(i));
            textView_grade.setText(REFERENCES.get(i));
            textView_name.setText(GRADES.get(i));
            textView_stock.setText(String.valueOf(QUANTITIES.get(i)));
            textView_prix.setText(String.valueOf(UNITPRICES.get(i)));

            return view;
        }
    }
}
