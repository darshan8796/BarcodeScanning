package com.example.android.barcodescanning;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by User on 9/22/2015.
 */
public class ExtraQ extends AppCompatActivity {

    public static final String PREF = "question";
    public static int answered = 0;
    public static int queSpec1 = 0;
    public static int queSpec2 = 0;
    public static int queSpec3 = 0;
    EditText ed;
    TextView txt;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extraq);
        SharedPreferences currClue = getSharedPreferences(PREF, MODE_PRIVATE);
        answered = currClue.getInt("0", 0);
        queSpec1 = currClue.getInt("1", 0);
        queSpec2 = currClue.getInt("2", 0);
        queSpec3 = currClue.getInt("3", 0);
        // Barcode checker = new Barcode();

        if (Barcode.queset == 1 && answered == 0) {
            txt = (TextView) findViewById(R.id.question1);
            txt.setText(R.string.que1);

            txt = (TextView) findViewById(R.id.question2);
            txt.setText(R.string.que2);

            txt = (TextView) findViewById(R.id.question3);
            txt.setText(R.string.que3);

            ed = (EditText) findViewById(R.id.edittext1);
            ed.setVisibility(View.VISIBLE);

            ed = (EditText) findViewById(R.id.edittext2);
            ed.setVisibility(View.VISIBLE);

            ed = (EditText) findViewById(R.id.edittext3);
            ed.setVisibility(View.VISIBLE);

            b = (Button) findViewById(R.id.btn1);
            b.setVisibility(View.VISIBLE);

            b = (Button) findViewById(R.id.btn2);
            b.setVisibility(View.VISIBLE);

            b = (Button) findViewById(R.id.btn3);
            b.setVisibility(View.VISIBLE);
        }

        if (Barcode.queset == 1 && answered == 1) {

            if(queSpec1 == 1)
            {
                txt = (TextView) findViewById(R.id.question1);
                txt.setText("Correct!");
                ed = (EditText) findViewById(R.id.edittext1);
                ed.setVisibility(View.INVISIBLE);
                b = (Button) findViewById(R.id.btn1);
                b.setVisibility(View.INVISIBLE);
            }
            else
            {
                txt = (TextView) findViewById(R.id.question1);
                txt.setText(R.string.que1);

                ed = (EditText) findViewById(R.id.edittext1);
                ed.setVisibility(View.VISIBLE);

                b = (Button) findViewById(R.id.btn1);
                b.setVisibility(View.VISIBLE);

            }

            if(queSpec2 == 1)
            {
                txt = (TextView) findViewById(R.id.question2);
                txt.setText("Correct!");
                ed = (EditText) findViewById(R.id.edittext2);
                ed.setVisibility(View.INVISIBLE);
                b = (Button) findViewById(R.id.btn2);
                b.setVisibility(View.INVISIBLE);
            }
            else
            {
                txt = (TextView) findViewById(R.id.question2);
                txt.setText(R.string.que2);

                ed = (EditText) findViewById(R.id.edittext2);
                ed.setVisibility(View.VISIBLE);

                b = (Button) findViewById(R.id.btn2);
                b.setVisibility(View.VISIBLE);

            }

            if(queSpec3 == 1)
            {
                txt = (TextView) findViewById(R.id.question3);
                txt.setText("Correct!");
                ed = (EditText) findViewById(R.id.edittext3);
                ed.setVisibility(View.INVISIBLE);
                b = (Button) findViewById(R.id.btn3);
                b.setVisibility(View.INVISIBLE);
            }
            else
            {
                txt = (TextView) findViewById(R.id.question3);
                txt.setText(R.string.que3);

                ed = (EditText) findViewById(R.id.edittext3);
                ed.setVisibility(View.VISIBLE);

                b = (Button) findViewById(R.id.btn3);
                b.setVisibility(View.VISIBLE);

            }

        }
    }

    public void submit1(View view) {
        ed = (EditText) findViewById(R.id.edittext1);
        String s = "0000";

        if (ed.getText().toString().equals(s)) {
            txt = (TextView) findViewById(R.id.question1);
            txt.setText("Correct!");
            b = (Button) findViewById(R.id.btn1);
            ed.setVisibility(View.INVISIBLE);
            b.setVisibility(View.INVISIBLE);
            answered = 1;
            queSpec1 = 1;
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Sorry, incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    public void submit2(View view) {
        ed = (EditText) findViewById(R.id.edittext2);
        String s = "0000";

        if (ed.getText().toString().equals(s)) {
            txt = (TextView) findViewById(R.id.question2);
            txt.setText("Correct!");
            b = (Button) findViewById(R.id.btn2);
            ed.setVisibility(View.INVISIBLE);
            b.setVisibility(View.INVISIBLE);
            answered = 1;
            queSpec2 = 1;
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Sorry, incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    public void submit3(View view) {
        ed = (EditText) findViewById(R.id.edittext3);
        String s = "0000";

        if (ed.getText().toString().equals(s)) {
            txt = (TextView) findViewById(R.id.question3);
            txt.setText("Correct!");
            b = (Button) findViewById(R.id.btn3);
            ed.setVisibility(View.INVISIBLE);
            b.setVisibility(View.INVISIBLE);
            answered = 1;
            queSpec3 = 1;
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Sorry, incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        TextView txt = (TextView) findViewById(R.id.clue);

        Toast.makeText(getApplicationContext(), "ExtraQ onDestroy", Toast.LENGTH_SHORT).show();

        SharedPreferences currClue = getSharedPreferences(PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = currClue.edit();
        editor.putInt("0", answered);
        Toast.makeText(getApplicationContext(), "ExtraQ answered" + answered, Toast.LENGTH_SHORT).show();
        editor.putInt("1", queSpec1);
        Toast.makeText(getApplicationContext(), "ExtraQ queSpec1" + queSpec1, Toast.LENGTH_SHORT).show();
        editor.putInt("2", queSpec2);
        Toast.makeText(getApplicationContext(), "ExtraQ queSpec2" + queSpec2, Toast.LENGTH_SHORT).show();
                editor.putInt("3", queSpec3);
        Toast.makeText(getApplicationContext(), "ExtraQ queSpec3" + queSpec3, Toast.LENGTH_SHORT).show();
                editor.commit();


    }
}
