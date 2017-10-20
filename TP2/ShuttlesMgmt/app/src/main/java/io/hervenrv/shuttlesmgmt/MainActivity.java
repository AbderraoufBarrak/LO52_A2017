package io.hervenrv.shuttlesmgmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openAchat(View view) {
        Intent intent = new Intent(this, Achats.class);
        startActivity(intent);
    }

    public void openStock(View view) {
        Intent intent = new Intent(this, Stock.class);
        startActivity(intent);
    }
}
