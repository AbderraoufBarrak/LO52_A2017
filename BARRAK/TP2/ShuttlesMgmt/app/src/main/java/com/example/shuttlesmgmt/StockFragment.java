package com.example.shuttlesmgmt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class StockFragment extends Fragment {

    int[] IMAGES = {R.drawable.t1,R.drawable.t2,R.drawable.t3,R.drawable.t4};
    String[] NAMES = {"Yonex","RSL ","RSL ","RSL "};
    String[] GRADES = {"AS30 ","Grade 3 ","Grade A9 ","Grade A1 "};
    int[] STOCKRESTANT = {500 ,5000,10000,6000};
    String[] PRIXTUBES = {"27","16.70","13.7","21"};
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
        /*super.onCreateView(inflater,container,savedInstanceState);*/
        View view = inflater.inflate(R.layout.fragment_stock, container, false);

        ListView list = (ListView)view.findViewById(R.id.listView);
        CustomAdapter customAdapter= new CustomAdapter();
        list.setAdapter(customAdapter);

        return view;
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return IMAGES.length;
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

            imageView.setImageResource(IMAGES[i]);
            textView_name.setText(NAMES[i]);
            textView_grade.setText(GRADES[i]);
            textView_stock.setText(STOCKRESTANT[i]+"");
            textView_prix.setText(PRIXTUBES[i]+"");

            return view;
        }
    }
}
