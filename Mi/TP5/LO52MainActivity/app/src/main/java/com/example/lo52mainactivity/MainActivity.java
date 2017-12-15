package com.example.lo52mainactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView label;
    private EditText et;
    private TextView tv;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native String read(String x);
    public native String write(String x);
    public native int start(int i);
    public native int stop(int i);
    public native String reset();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Example of a call to a native method
        tv = (TextView) findViewById(R.id.sample_text);
        et = (EditText) findViewById(R.id.id_ET);
        label = (TextView) findViewById(R.id.idTV);
        Button btnStart = (Button) findViewById(R.id.idStart);
        Button btnStop = (Button) findViewById(R.id.idStop);
        Button btnRead = (Button) findViewById(R.id.idRead);
        Button btnWrite = (Button) findViewById(R.id.idWrite);
        Button btnReset = (Button) findViewById(R.id.idReset);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int number = 11 + random.nextInt()%6;
                Log.i("APPINFO", "Start number" + Integer.toString(number));
                label.setText(number + " / " + start(number) );
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int number = 1 + random.nextInt()%10;
                Log.i("APPINFO", "Stop number" + Integer.toString(number));
                label.setText(number + " / " + stop(number) );
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                label.setText("");
                label.setText("return code = " + reset());
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = write(tv.getText().toString());
                label.setText(message);
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = read(et.getText().toString());
                label.setText(message);
            }
        });
        tv.setText(stringFromJNI());
    }
}
