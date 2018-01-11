package fr.utbm.houssame.lo52_tp5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Random;

public class LO52MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    Button read, write, start, stop, reset;
    EditText editText;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo52_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        read = findViewById(R.id.btn_read);
        write = findViewById(R.id.btn_write);
        start = findViewById(R.id.btn_start);
        stop = findViewById(R.id.btn_stop);
        reset = findViewById(R.id.btn_reset);
        read.setOnClickListener(this);
        write.setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        reset.setOnClickListener(this);
        // Example of a call to a native method
        editText = (EditText) findViewById(R.id.field);
        editText.setText(stringFromJNI());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lo52_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native String readCmd(String jIn);
    public native String writeCmd(String jIn);
    public native String stopCmd(int jIn);
    public native String resetCmd(int jIn);

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_read :
                Toast.makeText(this, readCmd(editText.getText().toString()),Toast.LENGTH_SHORT).show();
                editText.setText(readCmd(editText.getText().toString()));

                break;
            case R.id.btn_write :
                editText.setText(writeCmd(editText.getText().toString()));

                break;
            case R.id.btn_start :
                random = new Random();
                int r1 = random.nextInt( 16 - 11 + 1) + 11;
                editText.setText(r1 + " ");
                editText.setText(stopCmd(r1));
                break;
            case R.id.btn_stop :
                random = new Random();
                int r2 = random.nextInt(10 - 1 + 1) + 1;
                //editText.setText(r2 + " ");
                editText.setText(stopCmd(r2));
                //editText.setText(stopCmd(Integer.parseInt(editText.getText().toString())));
                break;
            case R.id.btn_reset :
                // vider le champ de saisie
                editText.setText("");
                random = new Random();
                int r3 = random.nextInt(3 - 0 + 1) + 0;
                editText.setText(resetCmd(r3));
                break;

        }

    }
}
