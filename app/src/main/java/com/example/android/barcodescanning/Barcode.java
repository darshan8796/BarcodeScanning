package com.example.android.barcodescanning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class Barcode extends AppCompatActivity {

    public static final String PREF = "ClueStorage";
    public static int display = -1;
    ImageView imageView;
    private int flag = 1;
    public static int queset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcode);

        Toast.makeText(getApplicationContext(), "Barcode onCreate", Toast.LENGTH_SHORT).show();

        TextView txt = (TextView) findViewById(R.id.clue);
        SharedPreferences currClue = getSharedPreferences(PREF, MODE_PRIVATE);
        txt.setText(currClue.getString("0", "Clue Will come here!"));
        flag = currClue.getInt("1", 1);
        queset = currClue.getInt("2", 0);
        adLoader();

    }

    void adLoader() {

        final int[] imageArray = {R.drawable.ad, R.drawable.img2, R.drawable.img3};

        imageView = (ImageView) findViewById(R.id.ad);
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

        super.onDestroy();
        TextView txt = (TextView) findViewById(R.id.clue);

        Toast.makeText(getApplicationContext(), "Barcode onDestroy", Toast.LENGTH_SHORT).show();

        SharedPreferences currClue = getSharedPreferences(PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = currClue.edit();
        editor.putString("0", txt.getText().toString());
        editor.putInt("1", flag);
        editor.putInt("2", queset);
        editor.commit();


    }


    /**
     * event handler for scan button
     *
     * @param view view of the activity
     */
    public void scanNow(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setResultDisplayDuration(0);
        integrator.setWide();  // Wide scanning rectangle, may work better for 1D barcodes
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.initiateScan();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        TextView txt = (TextView) findViewById(R.id.clue);
        String scanContent = scanningResult.getContents();
        if (scanningResult != null && scanContent != null) {
//we have a result


            switch (scanContent) {
                case "clue1": {
                    if (flag == 1) {
                        txt.setText(R.string.clue1);
                        flag++;
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect Order!", Toast.LENGTH_LONG).show();
                    }
                    break;
                }

                case "clue2": {
                    if (flag == 2) {
                        txt.setText(R.string.clue2);
                        display = 1;
                        queset = 1;
                        flag++;
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect Order!", Toast.LENGTH_LONG).show();
                    }
                    break;
                }

                case "clue3": {
                    if (flag == 3) {
                        txt.setText(R.string.clue3);
                        display = 2;
                        flag++;
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect Order!", Toast.LENGTH_LONG).show();
                    }
                    break;
                }

                default:
                    Toast.makeText(getApplicationContext(), "Invalid Barcode!", Toast.LENGTH_LONG).show();
                    break;
            }

// display it on screen
            //TextView formatTxt = (TextView) findViewById(R.id.scan_format);
            // TextView contentTxt = (TextView) findViewById(R.id.scan_format);

            //contentTxt.setText("CONTENT: " + scanContent);

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void extraQ(View view) {
        Intent intent = new Intent(this, ExtraQ.class);
        startActivity(intent);
    }


}
