package com.example.rishabh.casino;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import java.util.Random;

import java.util.logging.LogRecord;


public class Game extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
    int genno,pos,exact;
    boolean flag;
    float balrem,betamt,multiplier;
    Spinner spin;
    TextView rembal;
    TextView bet_amount;
    TextView result;
    TextView beton;
    EditText bet;
    Button okgen;
    private Handler mhandler = new Handler();
    String options[]={"ODD NUMBER","EVEN NUMBER","PRIME NUMBER","MULTIPLE OF 3","EXACT NUMBER" };
    private AlertDialog.Builder dialogbuilder;
    private AlertDialog.Builder devInfo;
    public Game (){
        multiplier=1;
        flag=false;
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        spin=(Spinner)findViewById(R.id.spinner);
        rembal=(TextView)findViewById(R.id.textView8);
        bet_amount=(TextView)findViewById(R.id.textView9);
        beton=(TextView)findViewById(R.id.textView10);
        result=(TextView)findViewById(R.id.textView11);
        bet=(EditText)findViewById(R.id.editText2);
        okgen=(Button)findViewById(R.id.button4);


        ArrayAdapter<String> adapter_option=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,options);
        adapter_option.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter_option);
        spin.setOnItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();
        final int userno = extras.getInt("gotcha");
        rembal.setText("REMAINING BALANCE   : "+ " " +userno);
        //rembal.setText("Remaining Balance : " + userno);
        balrem=(float)userno;
        //here balrem is balance with which user entered it is different once we go inside onclick listener as it changes and will become zero

        okgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //result.setText("result now brother="+ userno);


                //            rembal.setText("Remaining Balance  : " + balrem);

                if(balrem>0){
                    // inpbet=bet.getText().toString();
                    String inpbet=bet.getText().toString();
                    if(inpbet.isEmpty()){
                        Toast.makeText(v.getContext(),"You Need To Choose An Amount For The Bet",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        betamt= Float.parseFloat(inpbet);

                        if(betamt==balrem){
                            Toast.makeText(v.getContext(),"Dude!!You Are All In..",Toast.LENGTH_SHORT).show();
                        }
                        if(betamt>balrem){
                            Toast.makeText(v.getContext(),"Seriously!!Who Let You Inside The Casino",Toast.LENGTH_SHORT).show();
                        }
                        else if(betamt==0){
                            Toast.makeText(v.getContext(),"Let's Make A Deal : #I_Play_IF_You_Play# ",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            balrem=balrem-betamt;
                            Random r= new Random();
                            genno=(((((r.nextInt(100)%69)+31)%10)%8)+1);
                            pos=spin.getSelectedItemPosition();
                            if(pos==0){
                                //odd number bet
                                if(genno%2==1){
                                    flag=true;
                                    multiplier= (float) 1.1;
                                    Toast.makeText(v.getContext(),"You Won 10% of your bet",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else if(pos==1){
                                //even number bet
                                if(genno%2==0){
                                    flag=true;
                                    multiplier= (float) 1.1;
                                    Toast.makeText(v.getContext(),"You Won 10% of your bet",Toast.LENGTH_SHORT).show();
                                }

                            }
                            else if(pos==2){
                                //prime number bet
                                if(genno==2||genno==3||genno==5||genno==7){
                                    flag=true;
                                    multiplier= (float) 1.25;
                                    Toast.makeText(v.getContext(),"You Won 25% of your bet",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else if(pos==3){
                                //multiple of 3 bet
                                if(genno==3||genno==6||genno==9){
                                    flag=true;
                                    multiplier= (float) 1.5;
                                    Toast.makeText(v.getContext(),"You Won 50% of your bet",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else if(pos==4){
                                //exact bet
                                //alert input
                                if(genno==exact){
                                    flag=true;
                                    multiplier=2;
                                    Toast.makeText(v.getContext(),"You Won 100% of your bet",Toast.LENGTH_SHORT).show();
                                }

                            }

                        }
                        if(flag==true){
                            balrem+=(betamt*multiplier);
                            result.setText("You Won!!Generated Number Is : "+genno);
                            if(balrem>100000){
                                balrem=99999;
                                Toast.makeText(v.getContext(),"Sorry Bro!!Can't let you have more than 100000(Silly Casino Policies) ",Toast.LENGTH_SHORT).show();
                            }
                            rembal.setText("REMAINING BALANCE   : "+ " " +Math.floor(balrem));
                            flag=false;
                        }
                        else{

                            result.setText("You lost../:-( Generated Number Is : "+genno);
                            rembal.setText("REMAINING BALANCE   : "+ " " +Math.floor(balrem));
                        }

                    }

                }
                if(balrem<1){
                    //alert box for game ower button to goto play activity again
                    result.setText("game is over");
                    gameoverdialog();
                }
                if((pos==4) && (balrem>=1) && (balrem>=betamt)){
                    //pause

                    mhandler.postDelayed(new Runnable() {
                        public void run() {
                            exactalertbox();
                        }
                    }, 1600);


                }
            }


        });
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public void onBackPressed(){
       // super.onBackPressed();
        ingamebackdialog();
    }

    private void ingamebackdialog() {
        //input
        dialogbuilder=new AlertDialog.Builder(this);

        //processing
        dialogbuilder.setTitle("PAUSED");
        dialogbuilder.setMessage("Game Is Paused");
        dialogbuilder.setPositiveButton("Resume", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogbuilder.setNeutralButton("NEW GAME",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent("com.example.rishabh.casino.PLAY"));
            }
        });
        dialogbuilder.setNegativeButton("Exit",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        //output
        AlertDialog dialogexact=dialogbuilder.create();
        dialogexact.show();
    }

    private void gameoverdialog() {
        //show alert box of game over and to start again and thrn start play activity
        //input
        dialogbuilder=new AlertDialog.Builder(this);

        //processing
        dialogbuilder.setTitle("Game Over");
        dialogbuilder.setMessage("Sorry Bro you've Lost All Your Money....\nWant to Play Again");
        dialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent("com.example.rishabh.casino.PLAY"));
            }
        });

        dialogbuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        //output
        AlertDialog dialogexact=dialogbuilder.create();
        dialogexact.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spin.setSelection(position);
        if(position==4){
            //exact alert
            exactalertbox();
        }
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
    private void exactalertbox() {
        //input
        dialogbuilder=new AlertDialog.Builder(this);
        final String[] arr={"1","2","3","4","5","6","7","8","9"};
        //processing
        dialogbuilder.setTitle("CHOOSE EXACT NUMBER");
        //dialogbuilder.setMessage("Choose the number You Want to Bet On:-");
        dialogbuilder.setSingleChoiceItems(arr,-1,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exact= Integer.parseInt(arr[which]);
                Toast.makeText(getApplicationContext(),"Your Bet Is On "+ exact,Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //output
        AlertDialog dialogexact=dialogbuilder.create();
        dialogexact.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        spin.setSelection(0);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_game, container, false);
            return rootView;
        }
    }
}
