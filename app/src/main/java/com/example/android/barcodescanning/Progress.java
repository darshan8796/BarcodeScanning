package com.example.android.barcodescanning;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Progress extends AppCompatActivity {

    private static final String PREF = "ListStorage";
    ArrayList<String> elements;
    ImageView imageView;
    private ArrayAdapter<String> adapter;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.progress);
        Toast.makeText(getApplicationContext(), "Progress onCreate!", Toast.LENGTH_SHORT).show();
        ListView list = (ListView) findViewById(R.id.listview);
        elements = new ArrayList<String>();

        SharedPreferences listElems = getSharedPreferences(PREF, MODE_PRIVATE);
        count = listElems.getInt("counter", 0);
        for (int i = 0; i < count; i++) {
            Toast.makeText(getApplicationContext(), "Prog onCre" + i, Toast.LENGTH_SHORT).show();
            elements.add(listElems.getString(Integer.toString(i), "Default"));
        }


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elements);
        list.setAdapter(adapter);


        adLoader();


    }


    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "Progress onResume", Toast.LENGTH_SHORT).show();
        if (Barcode.display > 0) {
            switch (Barcode.display) {
                case 1:
                    //elements.add("Clue 1 done ");
                    add(1);
                    Barcode.display = 0;
                    adapter.notifyDataSetChanged();
                    //count++;
                    break;

                case 2:
                    //elements.add("Clue 2 done ");
                    add(2);
                    Barcode.display = 0;
                    adapter.notifyDataSetChanged();
                    //count++;
                    break;

                default:
                    break;
            }
        }

    }

    public void add(int c) {
        elements.clear();
        count = c;
        for (int i = 0; i < c; i++) {
            elements.add("Clue " + (i + 1) + " done!");
        }
    }

    void adLoader() {

        final int[] imageArray = {R.drawable.ad, R.drawable.img2, R.drawable.img3};

        imageView = (ImageView) findViewById(R.id.ad1);
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 0;

            public void run() {
                imageView.setImageResource(imageArray[i]);
                i++;
                if (i > imageArray.length - 1) {
                    i = 0;
                }
                handler.postDelayed(this, 5000);  //for interval...
            }
        };
        handler.postDelayed(runnable, 5000); //for initial delay..
    }



    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "Progress onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        SharedPreferences listElems = getSharedPreferences(PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = listElems.edit();

        for (int i = 0; i < count; i++) {
            Toast.makeText(getApplicationContext(), "Prog onDest" + i, Toast.LENGTH_SHORT).show();
            editor.putString(Integer.toString(i), elements.get(i));
        }

        editor.putInt("counter", count);
        editor.commit();

    }
}
