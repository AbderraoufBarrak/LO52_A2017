package com.lo52.dewback.shuttlesmgmt;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lo52.dewback.shuttlesmgmt.stock_activity.Stock;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void AchatSend(View view) {
        Intent intent = new Intent(this, Achats.class);
        startActivity(intent);
    }

    public void StockSend(View view){
        Intent intent = new Intent(this, Stock.class);
        startActivity(intent);
    }
}
