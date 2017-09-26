package fr.utbm.lo52.taaroaffbad.Activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;


import fr.utbm.lo52.taaroaffbad.R;

public class VolantPageActivity extends AppCompatActivity {

    public AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volant_page);

        Intent intent = getIntent();
        String validite1_volant = intent.getStringExtra("validite1_volant");
        String validite2_volant = intent.getStringExtra("validite2_volant");
        String marque_volant = intent.getStringExtra("marque_volant");
        String reference = intent.getStringExtra("reference");
        int classement = intent.getIntExtra("classement",0);

        // marque & référence
        TextView tvVolantMarqueRef = (TextView) findViewById(R.id.tvVolantMarqueRef);
        tvVolantMarqueRef.setText(marque_volant+"\n["+reference+"]");

        // classement
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setNumStars(classement);
        Drawable drawable = ratingBar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#dfe61d"), PorterDuff.Mode.SRC_ATOP);

        // validite 1 & 2
        TextView tvValidite = (TextView) findViewById(R.id.tvValidite);
        tvValidite.setText(validite1_volant+" / "+validite2_volant);



        Button commander = (Button) findViewById(R.id.btnCommander);
        commander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(new ContextThemeWrapper(VolantPageActivity.this, R.style.AppTheme));
                LayoutInflater inflater = VolantPageActivity.this.getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.form, null));
                builder.setPositiveButton("Acheter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            }
        });


    }

}
