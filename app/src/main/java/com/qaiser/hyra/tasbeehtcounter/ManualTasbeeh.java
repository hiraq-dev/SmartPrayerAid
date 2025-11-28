package com.qaiser.hyra.tasbeehtcounter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ManualTasbeeh extends AppCompatActivity {

    public static class myvar1{
        public static String getcount=null,CustomZikar=null;
        public static int mycounter=0;
    }

    TextView textView,textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_tasbeeh);
        textView=(TextView)findViewById(R.id.textView2);
        textView6=(TextView)findViewById(R.id.textView6);
       // textView5=(TextView)findViewById(R.id.textView5);
        CustomZikar();
       // counterlimit();

        RelativeLayout rl=(RelativeLayout)findViewById(R.id.relative);

        rl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                myvar1.mycounter++;
                textView.setText(String.valueOf(myvar1.mycounter));
            }
    });


    }


    public void Back(View view)
    {
        Intent intent=new Intent(getApplication(),MainActivity.class);
        startActivity(intent);
    }


 public void counterlimit()
 {
     final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ManualTasbeeh.this);
     alertDialog.setTitle("Start Counter");
     alertDialog.setMessage("Enter Limit");

     final EditText input = new EditText(ManualTasbeeh.this);
     final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
             LinearLayout.LayoutParams.MATCH_PARENT,
             LinearLayout.LayoutParams.MATCH_PARENT);
     input.setLayoutParams(lp);
     input.setInputType(InputType.TYPE_CLASS_NUMBER);
     alertDialog.setView(input);
     // alertDialog.setIcon(R.drawable.key);

     alertDialog.setPositiveButton("YES",
             new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     myvar1.getcount = input.getText().toString();

                     if (myvar1.getcount!=null && (Integer.parseInt(myvar1.getcount))!=0) {

                         Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();
                         //  dialog.cancel();
                     } else {

                         Toast.makeText(getApplicationContext(),
                                 "Please Enter Counter Properly!", Toast.LENGTH_SHORT).show();


                     }

                 }

             });

     alertDialog.setNegativeButton("NO",
             new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialogInterface, int i) {

                     dialogInterface.cancel();
                 }
             });


     alertDialog.show();


 }


    public void CustomZikar()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ManualTasbeeh.this);
        alertDialog.setTitle("Add Custom Zikar");
        alertDialog.setMessage("Zikar");

        final EditText input = new EditText(ManualTasbeeh.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        // alertDialog.setIcon(R.drawable.key);

        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        myvar1.CustomZikar = input.getText().toString();

                        if (myvar1.CustomZikar!=null) {
                            // Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();



                            textView6.setText(myvar1.CustomZikar );

                            dialog.cancel();
                            counterlimit();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Please Input Zikar Properly!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent xx=new Intent(ManualTasbeeh.this,MainActivity.class);
                        startActivity(xx);
                        //dialog.cancel();
                    }
                });

        alertDialog.show();

    }
    ////////////////////////////////////////////////////////////////////

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

            AlertReset();

            return true;

        }


        return super.onOptionsItemSelected(item);

    }

    public void AlertReset()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ManualTasbeeh.this);
        alertDialog.setTitle("Are you sure you want to Reset?");
       // alertDialog.setMessage("Zikar");

       // final EditText input = new EditText(ManualTasbeeh.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        //input.setLayoutParams(lp);
        // alertDialog.setIcon(R.drawable.key);

        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                      myvar1.mycounter=0;
                        myvar1.CustomZikar=null;
                        textView.setText(String.valueOf(myvar1.mycounter));
                        textView6.setText("");


                    }
                });

        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        //dialog.cancel();
                    }
                });

        alertDialog.show();

    }

}

