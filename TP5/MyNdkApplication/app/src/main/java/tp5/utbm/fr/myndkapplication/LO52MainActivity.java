package tp5.utbm.fr.myndkapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class LO52MainActivity extends AppCompatActivity {

    TextView tv;
    EditText et;

    // chargement de la librairie native
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo52_main);

        // définition des éléments graphiques
        this.tv = (TextView) findViewById(R.id.sample_text);
        this.et = (EditText) findViewById(R.id.editText);

    }

    /**
     * Listener
     */

    public void readButtonClick (View v){
        tv.setText(read(et.getText().toString()));
    }

    public void writeButtonClick (View c){
        tv.setText(write(et.getText().toString()));
    }

    public void stopButtonClick(View v){
        Random rand = new Random();
        int i = 1 + rand.nextInt(10);
        tv.setText(i + "/" + stop(i));
    }

    public void startButtonClick(View v){
        Random rand = new Random();
        int i = 11 + rand.nextInt(6);
        tv.setText(i + "/" + stop(i));
    }

    public void resetButtonClick(View v){
        et.setText("");
        tv.setText("Le code de retour est " + this.reset());
    }

    /**
     *  Liste des fonctions natives
     */

    public native String read(String a);

    public native String write (String a);

    public native int stop(int i);

    public native String reset();

}
