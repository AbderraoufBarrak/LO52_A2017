package com.example.shuttlesmgmt;

/**
 * Created by Barrak on 27/10/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shuttlesmgmt.SQLiteDatabase.Achat;
import com.example.shuttlesmgmt.SQLiteDatabase.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class AchatFragment extends Fragment {

    List<Integer> IMAGES = new ArrayList<Integer>();
    List<String> NAMES = new ArrayList<String>();
    List<String> GRADES = new ArrayList<String>();
    List<Integer> QUANTITIES = new ArrayList<Integer>();
    List<Integer> TOTALPRICES = new ArrayList<Integer>();
    List<Integer> STATUTS = new ArrayList<Integer>();

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


        //reading and displaying all achats
        List<Achat> achats = db.getAllAchats();

        for (Achat a : achats) {
            String log = "ID " + a.getId() + " Customer : " + a.getCustomerName() + " Reference " + a.getReference()
                    + " Quantity " + a.getQuantity() + " Total price " + a.getTotalPrice() + " Status " + a.getStatus() + "\n";

            Log.i("every achat", log);


            IMAGES.add(R.drawable.customer);
            NAMES.add(a.getCustomerName());
            GRADES.add(a.getReference());
            QUANTITIES.add(a.getQuantity());
            TOTALPRICES.add(a.getTotalPrice());
            STATUTS.add(a.getStatus());


        }
        ////////////////////////////////////
        /*super.onCreateView(inflater,container,savedInstanceState);*/
        View view = inflater.inflate(R.layout.fragment_achat, container, false);

        ListView list = (ListView)view.findViewById(R.id.listView);
        CustomAdapter customAdapter= new CustomAdapter();
        list.setAdapter(customAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Fragment fragment = new ReadOnlyFormFragment();

                TextView textViewCustomer = (TextView) view.findViewById(R.id.textView_name);
                String customer = textViewCustomer.getText().toString();
                TextView textViewReference = (TextView) view.findViewById(R.id.textView_grade);
                String reference = textViewReference.getText().toString();
                TextView textViewQte = (TextView) view.findViewById(R.id.textView_stock);
                String qte = textViewQte.getText().toString();
                TextView textViewStatut = (TextView) view.findViewById(R.id.textView_statut);
                String status = textViewStatut.getText().toString();

                List<String> where = new ArrayList<String>();
                where.add(customer);
                where.add(reference);
                where.add(qte);
                where.add(status);

                String[] mMyList = new String[ where.size() ];
                where.toArray( mMyList );

                Bundle bundle = new Bundle();
                bundle.putStringArray("my_list_key",mMyList);

                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
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
            view = getActivity().getLayoutInflater().inflate(R.layout.custom_achat_layout,null);
            ImageView imageView=(ImageView)view.findViewById(R.id.imageView);
            TextView textView_name=(TextView)view.findViewById(R.id.textView_name);
            TextView textView_grade=(TextView)view.findViewById(R.id.textView_grade);
            TextView textView_stock=(TextView)view.findViewById(R.id.textView_stock);
            TextView textView_prix=(TextView)view.findViewById(R.id.textView_prix);
            TextView textView_statut=(TextView)view.findViewById(R.id.textView_statut);



            imageView.setImageResource(IMAGES.get(i));
            textView_name.setText(NAMES.get(i));
            textView_grade.setText(GRADES.get(i));
            textView_stock.setText(String.valueOf(QUANTITIES.get(i)));
            textView_prix.setText(String.valueOf(TOTALPRICES.get(i)));
            if(STATUTS.get(i)==0){
                textView_statut.setText("Non payé");
            }else
                textView_statut.setText("Payé");


            return view;
        }
    }
}

