package com.example.utilisateur.tp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class LO52MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Retrieve our TextView and set its content.
         * the text is retrieved by calling a native
         * function.
         */
        setContentView(R.layout.activity_main);

    }
    /* A native method that is implemented by the
     * 'hello-jni' native library, which is packaged
     * with this application.
     */
    public native String read(String s, boolean b);

    public native int square(int i);

    public native String reset();

    static {
        System.loadLibrary("native-lib");
    }

    public void read_click(View v){
        TextView tv = (TextView)findViewById(R.id.textview);
        EditText et = (EditText)findViewById(R.id.editText);
        tv.setText( read(et.getText().toString(), true));
    }

    public void write_click(View v){
        TextView tv = (TextView)findViewById(R.id.textview);
        EditText et = (EditText)findViewById(R.id.editText);
        tv.setText( read(et.getText().toString(), false));
    }

    public void stop_click(View v){
        TextView tv = (TextView)findViewById(R.id.textview);
        startStopMethod(tv, 1,10);
    }

    public void start_click(View v){
        TextView tv = (TextView)findViewById(R.id.textview);
        startStopMethod(tv, 11,16);
    }

    public void startStopMethod(TextView tv, int min, int max){
        Random rand = new Random();
        int nb = rand.nextInt(max-min+1) + min;
        tv.setText( Integer.toString(nb));
        int nb2 = square(nb);
        tv.setText("(" + nb + "/" + nb2 + ")");
    }

    public void reset_click(View v){
        TextView tv = (TextView)findViewById(R.id.textview);
        EditText et = (EditText)findViewById(R.id.editText);
        et.getText().clear();
        tv.setText(reset());
    }
}
