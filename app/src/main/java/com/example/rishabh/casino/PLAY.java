package com.example.rishabh.casino;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class PLAY extends ActionBarActivity {
    int no,userno;
    EditText user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        user=(EditText)findViewById(R.id.editText);
        Toast.makeText(getApplicationContext(),"Lets See How Rich your Daddy is!!!",Toast.LENGTH_SHORT).show();

     //   newgame();


    }
    public void gotogame(View v,int tonext){
        startActivity(new Intent("com.example.rishabh.casino.Game").putExtra("gotcha",tonext));
        finish();
    }
    public void guess(View v){
        String s=user.getText().toString();
        if(s.isEmpty()){
            Toast.makeText(v.getContext(),"Hey! Who Let You Come Inside The CASINO",Toast.LENGTH_SHORT).show();
        }
        else if(s.length()>5){
            Toast.makeText(v.getContext(),"Seems like you are quite rich but this casino only allows maximum of 99999$",Toast.LENGTH_SHORT).show();
            userno=99999;
            gotogame(v,userno);
        }
        else{
            userno=Integer.parseInt(s);
            if(userno==0){
                Toast.makeText(getApplicationContext(),"WHAT DO YOU THINK  HUH ! THIS VIRTUAL CASINO WONT HAVE ANY BOUNCERS",Toast.LENGTH_SHORT).show();
            }
            else {
                gotogame(v,userno);
            }
        }

        //Toast.makeText(getApplicationContext(),"PARSING TO INTEGER FROM STRING FROM USER in GUESS",Toast.LENGTH_SHORT).show();


    }


    public void newgame(){
        //Toast.makeText(getApplicationContext(),"NEW GAME FUNCN",Toast.LENGTH_SHORT).show();

        //Toast.makeText(getApplicationContext(),"num is" + no,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_play, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
