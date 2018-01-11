package fr.utbm.lo52.a17.tp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class LO52MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo52_main);


        final TextView tv = (TextView) findViewById(R.id.label);

        final EditText saisie = (EditText) findViewById(R.id.editText);

        Button read = (Button) findViewById(R.id.readButton);
        read.setOnClickListener(this);
        Button write = (Button) findViewById(R.id.writeButton);
        write.setOnClickListener(this);
        Button start = (Button) findViewById(R.id.startButton);
        start.setOnClickListener(this);
        Button stop = (Button) findViewById(R.id.stopButton);
        stop.setOnClickListener(this);
        Button reset = (Button) findViewById(R.id.resetButton);
        reset.setOnClickListener(this);

        // Example of a call to a native method
        tv.setText(stringFromJNI());

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

   //@Override
    public void onClick(View v) {
        TextView tv = (TextView) findViewById(R.id.label);
        EditText saisie = (EditText) findViewById(R.id.editText);
        Random r=new Random();
        switch(v.getId()){
            case R.id.readButton:
                tv.setText(readCmd(saisie.getText().toString()));
                break;
            case R.id.writeButton:
                tv.setText(writeCmd(saisie.getText().toString()));
                break;
            case R.id.startButton:
                tv.setText(startCmd(r.nextInt(6)+11));
                break;
            case R.id.stopButton:
                tv.setText(stopCmd(r.nextInt(10)+1));
                break;
            case R.id.resetButton:
                    saisie.setText("");
                    tv.setText(reset());
                break;

            //case R.id.checkBox:

        }
    }



    public native String stringFromJNI();
    public native String readCmd(String str);
    public native String writeCmd(String str);
    public native String startCmd(int nbr);
    public native String stopCmd(int nbr);
    public native String reset();
}
