package com.example.modernartui;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MENU_INFO = Menu.FIRST;
    AlertDialog.Builder dialogBuilder;

    String dialogTitle = "Inspired by the works of artists such as Piet Mondrian and Ben Nicholson";
    String dialogMessage = "Click below to learn more!";
    String dialogButtonText1 = "Not now";
    String dialogButtonText2 = "Visit MOMA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView block1 = (ImageView)findViewById(R.id.block1);
        final ImageView block2 = (ImageView)findViewById(R.id.block2);
        final ImageView block3 = (ImageView)findViewById(R.id.block3);
        final ImageView block4 = (ImageView)findViewById(R.id.block4);
        final ImageView block5 = (ImageView)findViewById(R.id.block5);



        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                block1.setBackgroundColor(Color.argb(255,0,progress,255-progress));
                block2.setBackgroundColor(Color.argb(255,progress,255-progress,0));
                block3.setBackgroundColor(Color.argb(255-progress,0,progress,56));
                block5.setBackgroundColor(Color.argb(150,0,255-progress,progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(Menu.NONE, MENU_INFO, Menu.NONE, "More information");

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_INFO:
showDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialog(){
dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(dialogTitle);
        dialogBuilder.setMessage(dialogMessage);
        dialogBuilder.setPositiveButton(dialogButtonText2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http:///www.moma.com"));
                Intent chooserIntent = Intent.createChooser(intent,"Load with: ");
                startActivity(chooserIntent);
            }
        });
        dialogBuilder.setNegativeButton(dialogButtonText1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
            }
        });
        dialogBuilder.show();
    }


}
