package fr.utbm.ffbad.lo52tp5jni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class LO52MainActivity extends AppCompatActivity {

        private TextView tv;
        private EditText et;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lo52_main);
            tv = (TextView) findViewById(R.id.sample_text);
            et = (EditText) findViewById(R.id.editText);

        }

        public void read(View v){
            tv.setText(JNIMethods.read(et.getText().toString()));
        }

        public void write(View v){
            tv.setText(JNIMethods.write(et.getText().toString()));
        }

        public void start(View v){
            int i = new Random().nextInt(10) + 1 ;
            tv.setText(i + " / " +JNIMethods.square(i));
        }

        public void stop(View v){
            int i = new Random().nextInt(6) + 11 ;
            tv.setText(i + " / " +JNIMethods.square(i));
        }

        public void reset(View v){
            tv.setText(JNIMethods.errorCode());
        }

}
