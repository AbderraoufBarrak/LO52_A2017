package utbm.fr.ffbad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PurchaseFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_form);

        ((TextView)findViewById(R.id.tubeRefTxtView)).setText(Integer.toString(getIntent().getIntExtra("test",456789)));

    }
}
