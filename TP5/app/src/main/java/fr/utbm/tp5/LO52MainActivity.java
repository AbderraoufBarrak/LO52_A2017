package fr.utbm.tp5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LO52MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo52_main);

        // Example of a call to a native method
        final TextView label = (TextView) findViewById(R.id.label);
        final EditText editText = (EditText) findViewById(R.id.editText);

        label.setText(stringFromJNI());

        final Button buttonRead = (Button) findViewById(R.id.read);
        buttonRead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Read : " + editText.getText().toString(), Toast.LENGTH_SHORT).show();
                String text = "READ : " + read(editText.getText().toString());
                label.setText(text);
            }
        });
        final Button buttonWrite = (Button) findViewById(R.id.write);
        buttonWrite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                label.setText("WRITE : " + read(editText.getText().toString()));
            }
        });
        final Button buttonStart = (Button) findViewById(R.id.start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //nombreAleatoire = Min + (int)(Math.random() * ((Max - Min) + 1));
                int alea = 1 + (int)(Math.random() * ((10 - 1) + 1));
                label.setText(stop(alea));
            }
        });
        final Button buttonStop = (Button) findViewById(R.id.stop);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int alea = 11 + (int)(Math.random() * ((16 - 11) + 1));
                label.setText(stop(alea));
            }
        });
        final Button buttonReset = (Button) findViewById(R.id.reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText.setText("");
                label.setText(reset());
            }
        });

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native String read (String s);
    public native String stop (int s);
    public native String reset ();

}
