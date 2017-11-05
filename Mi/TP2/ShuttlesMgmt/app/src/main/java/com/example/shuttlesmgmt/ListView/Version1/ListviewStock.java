package com.example.shuttlesmgmt.ListView.Version1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.shuttlesmgmt.R;

public class ListviewStock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_stock);
        Log.i("AppInfo", "Je suis dans activity_Listview");
    }
}
