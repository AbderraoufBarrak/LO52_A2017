package com.example.missornela.jnitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class LO52MainActivity extends AppCompatActivity {
    Button read,write,start,stop,reset;
    TextView tview;
    EditText etext;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo52_main);

        read = (Button) findViewById(R.id.read);
        write = (Button) findViewById(R.id.write);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        reset = (Button) findViewById(R.id.reset);
        tview = (TextView) findViewById(R.id.textview);
        etext = (EditText) findViewById(R.id.edittext);
        // affiche.setText(stringFromJNI());
        read();
        write();
        stop();
        start();
        reset1();
    }

    private void read() {
        read.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String val;
                        val = etext.getText().toString();
                        tview.setText(readCmd(val));
                    }

                }
        );
    }

    private void write() {
        write.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String val;
                        val = etext.getText().toString();
                        tview.setText(writeCmd(val));
                    }

                }
        );
    }

    private void stop() {
        stop.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Random r = new Random();
                        int  n = 1 + r.nextInt()%10;
                        tview.setText(n + "/" + stop(n));
                    }

                }
        );
    }

    private void start() {
        start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Random r = new Random();
                        int  n = 11 + r.nextInt()%6;
                        tview.setText(n + "/" + start(n));
                    }

                }
        );
    }

    private void reset1() {
        reset.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tview.setText("Result : " + reset());
                    }

                }
        );
    }



        // Example of a call to a native method
        //TextView tv = (TextView) findViewById(R.id.sample_text);
        //tv.setText(stringFromJNI());
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native String readCmd(String string1);
    public native String writeCmd(String string1);
    public native int start(int i);
    public native int stop(int i);
    public native String reset();
}
