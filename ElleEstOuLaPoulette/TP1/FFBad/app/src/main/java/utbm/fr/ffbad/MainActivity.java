package utbm.fr.ffbad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import utbm.fr.ffbad.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHelper dbHelper = new DbHelper(this,DbHelper.DATABASE_NAME,null,1);
        Log.v("TEST",dbHelper.getDatabaseName());
    }
}
