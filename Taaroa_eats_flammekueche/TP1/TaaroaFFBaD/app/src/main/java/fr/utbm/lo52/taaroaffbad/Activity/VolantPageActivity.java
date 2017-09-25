package fr.utbm.lo52.taaroaffbad.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import fr.utbm.lo52.taaroaffbad.Beans.Volant;
import fr.utbm.lo52.taaroaffbad.R;

public class VolantPageActivity extends AppCompatActivity {

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
    }
}
