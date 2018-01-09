package fr.utbm.tp5jniapplicationbarrak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class LO52MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String text = "text";
        // Example of a call to a native method
        //TextView tv = (TextView) findViewById(R.id.sample_text);
        //tv.setText(stringFromJNIRead("FirstText"));

        Button ButtonRead = (Button)findViewById(R.id.button);
        ButtonRead.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        EditText edit_text = (EditText)findViewById(R.id.editText);
                        final String text1 = edit_text.getText().toString();
                        TextView tv = (TextView) findViewById(R.id.sample_text);
                        tv.setText(stringFromJNIRead(text1));
                    }
                });


        Button ButtonWrite = (Button)findViewById(R.id.button2);
        ButtonWrite.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        EditText edit_text = (EditText)findViewById(R.id.editText);
                        final String text1 = edit_text.getText().toString();
                        TextView tv = (TextView) findViewById(R.id.sample_text);
                        tv.setText(stringFromJNIWrite(text1));
                    }
                });
        Button ButtonStop = (Button)findViewById(R.id.button3);
        ButtonStop.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Random r = new Random();
                        long valeur = 1 + r.nextInt(10 - 1);
                        Log.i("Tag", "Random Value BARRAK " + valeur);
                        TextView tv = (TextView) findViewById(R.id.sample_text);
                        tv.setText(stringFromJNIStop(valeur));
                    }
                });
        Button ButtonStart = (Button)findViewById(R.id.button4);
        ButtonStart.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Random r = new Random();
                        long valeur = 11 + r.nextInt(16 - 11);
                        Log.i("Tag", "Random Value BARRAK " + valeur);
                        TextView tv = (TextView) findViewById(R.id.sample_text);
                        tv.setText(stringFromJNIStart(valeur));
                    }
                });
        Button ButtonReset = (Button)findViewById(R.id.button5);
        ButtonReset.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        EditText edit_text = (EditText)findViewById(R.id.editText);
                        edit_text.setText("");
                        TextView tv = (TextView) findViewById(R.id.sample_text);
                        tv.setText("Affichage du texte");
                        String Error_Status = stringFromJNIReset();
                        if (Error_Status.equals("Succès"))
                            Log.i("Tag","ErrorStatus is equal to 0 -> Success");
                        else
                            Log.i("Tag","ErrorStatus is equal to 1 -> Failure");
                    }
                });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNIRead(String text);
    public native String stringFromJNIWrite(String text);
    public native String stringFromJNIStop(long nombre);
    public native String stringFromJNIStart(long nombre);
    public native String stringFromJNIReset();

}
