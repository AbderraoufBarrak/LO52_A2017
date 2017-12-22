package com.lo52.dewback.tp5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LO52MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo52_main);
    }

    native String read(String pString);
    native String write(String pString);
    native String stop(int pInt);
    native String start(int pInt);
    native String reset();


    private int rand(int pMin, int pMax)
    {
        return (int)(pMin + Math.random() * (pMax - pMin));
    }

    public void onRead(View pView)
    {
        TextView tmp = (TextView) findViewById(R.id.label);
        EditText tmp2 = (EditText)findViewById(R.id.TextField);
        tmp.setText(read(tmp2.getText().toString()));
    }

    public void onWrite(View pView)
    {
        TextView tmp = (TextView) findViewById(R.id.label);
        EditText tmp2 = (EditText)findViewById(R.id.TextField);
        tmp.setText(write(tmp2.getText().toString()));
    }

    public void onStart(View pView)
    {
        TextView tmp = (TextView) findViewById(R.id.label);
        tmp.setText(start(rand(11,16)));
    }

    public void onStop(View pView)
    {
        TextView tmp = (TextView) findViewById(R.id.label);
        tmp.setText(start(rand(1,10)));
    }

    public void onReset(View pView)
    {
        TextView tmp = (TextView) findViewById(R.id.label);
        EditText tmp2 = (EditText)findViewById(R.id.TextField);
        tmp.setText(reset());
        tmp2.setText("");
    }

}
