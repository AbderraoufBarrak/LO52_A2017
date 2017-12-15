package fr.utbm.lpp.lo52_tp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;


import org.w3c.dom.Text;

public class LO52MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView label;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo52_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        TextView champ = (TextView) findViewById(R.id.champssaisie);
        label = (TextView) findViewById(R.id.label);
        Button btnread = (Button) findViewById(R.id.btnread);
        Button btnwrite = (Button) findViewById(R.id.btnwrite);
        Button btnstart = (Button) findViewById(R.id.btnstart);
        Button btnstop = (Button) findViewById(R.id.btnstop);
        Button btnreset = (Button) findViewById(R.id.btnreset);

        btnread.setOnClickListener(this);
        btnwrite.setOnClickListener(this);
        btnstart.setOnClickListener(this);
        btnstop.setOnClickListener(this);
        btnreset.setOnClickListener(this);

        tv.setText(stringFromJNI());
    }

    @Override
    public void onClick(View view) {
        String toDisplay;
        Random rand = new Random();

        Log.d("bonjour", "avant switch");
        switch (view.getId()){
            case R.id.btnread :
                Log.d("bonjour", "Bouton");
                toDisplay = read("Salut", "Bonsoir");
                label.setText("Read : " + toDisplay);
                Log.i("bonjour", "Fin Bouton");
                break;
            case R.id.btnwrite :
                toDisplay = write("Papa Lama");
                label.setText("Write : " + toDisplay);
                break;
            case R.id.btnstart :
                int i = 11 + rand.nextInt()%6;
                label.setText(i + "/" + start(i));
                break;
            case R.id.btnstop :
                int j = 1 + rand.nextInt()%10;
                label.setText(j + "/" + stop(j));
                break;
            case R.id.btnreset :
                label.setText("");
                label.setText("Hey, le return code is : " + reset() + " ty for your attention." + "\nYou're a llama. Gracefully, Your D(e)ad Lama.");
                break;

        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String read(String x, String y);
    public native String write(String x);
    public native int start(int j);
    public native int stop(int j);
    public native String reset();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
