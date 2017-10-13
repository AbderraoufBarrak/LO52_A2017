package fr.utbm.volantmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Accès à l'activité VolantsDisplay
     * @param view
     */
    public void browseVolants(View view) {
        Intent intent = new Intent(this, VolantsDisplay.class);
        startActivity(intent);
    }

    /**
     * Accès à l'activité Purchase
     * @param view
     */
    public void purchaseVolants(View view) {
        Intent intent = new Intent(this, Purchase.class);
        startActivity(intent);
    }

    /**
     * Accès à l'activité PurchaseItem
     * @param view
     */
    public void purchaseItem(View view) {
        Intent intent = new Intent(this, PurchaseItem.class);
        startActivity(intent);
    }
}
