package com.example.kiera.tp5jni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;

    private static final Random rd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv);
        editText = (EditText) findViewById(R.id.editText);


    }

    protected void onClickWrite(View view){
        textView.setText(PatateJNI.write(editText.getText().toString()));
    }

    protected void onClickRead(View view){
        textView.setText(PatateJNI.read(editText.getText().toString()));
    }

    protected void onClickReset(View view){
        textView.setText(PatateJNI.randomerror());
    }

    protected void onClickStart(View view){
        int n = rd.nextInt(10);
        textView.setText(PatateJNI.carre(n));
    }

    protected void onClickStop(View view){
        int n = 10 + rd.nextInt(6);
        textView.setText(PatateJNI.carre(n));
    }
}
