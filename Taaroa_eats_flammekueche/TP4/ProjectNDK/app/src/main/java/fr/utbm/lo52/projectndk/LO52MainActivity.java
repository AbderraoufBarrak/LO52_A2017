package fr.utbm.lo52.projectndk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LO52MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    String newText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo52_main);

        final TextView tv = (TextView) findViewById(R.id.textView);
        final Button buttonRead = (Button) findViewById(R.id.bRead);
        final Button buttonWrite = (Button) findViewById(R.id.bWrite);
        final Button buttonStop = (Button) findViewById(R.id.bStop);
        final Button buttonStart = (Button) findViewById(R.id.bStart);
        final Button buttonReset = (Button) findViewById(R.id.bReset);
        final EditText editText = (EditText) findViewById(R.id.editText);

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newText = editText.getText().toString();
                tv.setText(read(newText));
                Log.i("READ()",read(newText));
            }
        });

        buttonWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newText = editText.getText().toString();
                tv.setText(write(newText));
                Log.i("WRITE()",write(newText));
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // min + (rand * ((max-min)+1)
                int num = 1 + (int) (Math.random() * (( 10 - 1 )+1));
                tv.setText(num+"/"+stop(num));
                Log.i("STOP()","stop= "+num+"/"+stop(num));
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = 11 + (int) (Math.random() * (( 16 - 11 )+1));
                tv.setText(num+"/"+start(num));
                Log.i("START()","start= "+num+"/"+start(num));
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                tv.setText("");

                editText.setText(reset());
                tv.setText(reset());
                Log.i("START()","RESET= "+reset());
            }
        });
    }

    public native String read(String s);
    public native String write(String s);
    public native int stop(int i);
    public native int start(int i);
    public native String reset();
}
