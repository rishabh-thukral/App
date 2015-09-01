package com.example.rishabh.casino;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    private AlertDialog.Builder devInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void play(View v)
    {
        startActivity(new Intent("com.example.rishabh.casino.PLAY"));


    }
    public void rules(View v)
    {
        startActivity(new Intent("com.example.rishabh.casino.Rules"));


    }

    public void devDesc(){
        devInfo = new AlertDialog.Builder(this);
        devInfo.setTitle("About The Developer");
        devInfo.setMessage("This Android App has been Developed by Rishabh Thukral, currently an undergrad student of Computer Science & Engineering at ISM DHANBAD , INDIA \nContact At : rishabhthukral276@rediffmail.com");
        devInfo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialogexact=devInfo.create();
        dialogexact.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            devDesc();
        }

        return super.onOptionsItemSelected(item);
    }
}
