package com.example.vladmir.lo52_tp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class LO52MainActivity extends AppCompatActivity {
    Button read,write,start,stop,reset;
    TextView affiche;
    EditText valeur;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo52_main);

        // Example of a call to a native method
         affiche = (TextView) findViewById(R.id.id_affichetextview);
         read = (Button) findViewById(R.id.id_readbtn);
         write = (Button) findViewById(R.id.id_writebtn);
         start = (Button) findViewById(R.id.id_startbtn);
        stop = (Button) findViewById(R.id.id_stopbtn);
        reset = (Button) findViewById(R.id.id_resetbtn);
        valeur = (EditText) findViewById(R.id.id_valeuredittext);
       // affiche.setText(stringFromJNI());
        valeur.setText("Saisir une valeur");
        Read_BTN();
        Write_BTN();
        Stop_BTN();
        Start_BTN();
        Reset_BTN();
        Erase_EditText();
    }

    private void Read_BTN() {
        read.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String saisie;
                        saisie = valeur.getText().toString();
                        affiche.setText(readCmd(saisie));
                    }

                }
        );
    }
    private void Erase_EditText() {
        valeur.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        valeur.setText("");
                    }

                }
        );
    }

    private void Write_BTN() {
        write.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String saisie;
                        saisie = valeur.getText().toString();
                        affiche.setText(writeCmd(saisie));
                    }

                }
        );
    }

    private void Stop_BTN() {
        stop.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Random aleatoir = new Random();
                        int  n = 1 + aleatoir.nextInt()%10;
                        affiche.setText(n + "/" + stop(n));
                        valeur.setText("Saisir une valeur");
                    }

                }
        );
    }

    private void Start_BTN() {
        start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Random aleatoir = new Random();
                        int  n = 11 + aleatoir.nextInt()%6;
                        affiche.setText(n + "/" + start(n));
                        valeur.setText("Saisir une valeur");
                    }

                }
        );
    }

    private void Reset_BTN() {
        reset.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        affiche.setText("System error : " + reset());
                        valeur.setText("Saisir une valeur");
                    }

                }
        );
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
   // public native String stringFromJNI();
    public native String readCmd(String s);
    public native String writeCmd(String s);
    public native int start(int i);
    public native int stop(int i);
    public native String reset();

}
