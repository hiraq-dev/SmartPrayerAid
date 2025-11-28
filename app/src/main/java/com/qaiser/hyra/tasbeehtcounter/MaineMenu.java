package com.qaiser.hyra.tasbeehtcounter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MaineMenu extends AppCompatActivity {

    public static class myglobal
    {
        public static String Addcustom=null,translatedtext=null,transtext=null;
    }

    DataBaseHelper myDB;
    public static ArrayList zikkar;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maine_menu);
        myDB = new DataBaseHelper(this);

    }

                public void Manual_Button(View view)
                {
                    MainActivity.myvar.manual=1;
                    MainActivity.myvar.Auto=0;

                    Intent jj=new Intent(MaineMenu.this,MainActivity.class);
                    startActivity(jj);


                }
                            public void Auto_Button(View view)
                            {
                                MainActivity.myvar.Auto=1;
                                MainActivity.myvar.manual=0;
                                Intent jj=new Intent(MaineMenu.this,MainActivity.class);
                                startActivity(jj);


                            }


                                    public void Tasbehat_Button(View view) {

                                        tasbeehlist();
                                    }



    public void tasbeehlist()
    {
        final Context context = this;
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_zikar_list);
        dialog.setTitle("Select Tasbeeh:");
        lv = (ListView) dialog.findViewById(R.id.listView);

        zikkar = new ArabicWordsArraylists().getArrayList1();
      try {
        Cursor res = myDB.getlist();
        if (res.getCount() != 0) {
            // history.clear();
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append(res.getString(1));
                String xx = res.getString(1).toString();
                zikkar.add(xx);

            }
        }

        ArrayAdapter<String> aa = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, zikkar);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


              //  MainActivity.myvar.zikarcount = 0;
                //  myvar.manual=0;
               // MainActivity.myvar.selectedZikar = zikkar.get(i).toString();
                //MainActivity.myvar.myposition=i;

              //  if (MainActivity.myvar.selectedZikar!=null && MainActivity.myvar.myposition!=13)
                //{
                 //   dialog.cancel();
                    // counterlimit();
                //}
                if (zikkar.get(i).toString().contains("Add Custom")) {
                    CustomZikar();
                    //  Intent jj=new Intent(MainActivity.this,VoiceService.class);
                    //stopService(jj);
                    dialog.cancel();

                }

            }
        });


        dialog.show();


    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    }


    //////////////////////////add Custom Zikar////////////////////////////////////////////////////////

    public void CustomZikar()
    {
        final LayoutInflater inflater = getLayoutInflater();
        final View alertLayout = inflater.inflate(R.layout.activity_custom_zikar, null);
        final EditText input = (EditText) alertLayout.findViewById(R.id.editText2);
        final CheckBox option1 = (CheckBox) alertLayout.findViewById(R.id.checkBox2);
        final CheckBox option2 = (CheckBox) alertLayout.findViewById(R.id.checkBox3);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Add Custom Zikar");
        alertDialog.setMessage("Zikar");
        alertDialog.setCancelable(false);


        option1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked) {
                    MainActivity.myvar.myTransText = input.getText().toString().replace(" ", "%20");
                    // if (input.getText().toString()!="") {
                    new Translator(input).execute();
                    // Toast.makeText(getApplicationContext(), Translator.global1.myresult, Toast.LENGTH_SHORT).show();

                    // Translator.global1.myresult = "";


                    //input.setText(Translator.global1.myresult);
                    option1.setEnabled(true);
                    option2.setEnabled(false); // disable checkbox
                }
                if (!isChecked) {
                    option1.setEnabled(true);
                    option2.setEnabled(true); // disable checkbox
                }
            }
        });
        option2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked) {
                    //////////////////////////////////////

                  //  mVisible = true;
                    //  mControlsView = findViewById(R.id.fullscreen_content_controls);
                    //mContentView = findViewById(R.id.fullscreen_content);

                    InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                    imeManager.showInputMethodPicker();


                    // String oldDefaultKeyboard = Settings.Secure.getString(getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);

//enable your keyboard
//        Settings.Secure.putString(getContentResolver(), Settings.Secure.ENABLED_INPUT_METHODS, "com.qaiser.hyra.mykeyboard/.MyKeyboard");

//set your keyboard as the new default keyboar


                    //Settings.Secure.putString(getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD, "com.qaiser.hyra.mykeyboard/.MyKeyboard");






                    // Set up the user interaction to manually show or hide the system UI.
               /*    mContentView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            toggle();
                        }
                    });*/

                    // Upon interacting with UI controls, delay any scheduled hide()
                    // operations to prevent the jarring behavior of controls going away
                    // while interacting with the UI.
                    //  findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);








                    //////////////////////////////////////////////










                    option2.setEnabled(true);
                    option1.setEnabled(false); // disable checkbox
                }
                if (!isChecked) {
                    option2.setEnabled(true);
                    option1.setEnabled(true); // disable checkbox
                }
            }
        });




        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // myvar.selectedZikar = input.getText().toString();


                            if (option2.isChecked() == true) {
                                MainActivity.myvar.selectedZikar = input.getText().toString();
                                //Keyboard

                            }
                            if (option1.isChecked() == false && option2.isChecked() == false)
                                MainActivity.myvar.selectedZikar = input.getText().toString();


                            //

                            // tv1.setText(myvar.selectedZikar);
                            dialog.cancel();

                            String x1 = null;

                            if (MainActivity.myvar.selectedZikar!=null) {
                                for (int z = 0; z < zikkar.size(); z++) {
                                    if (zikkar.get(z).toString().contains(MainActivity.myvar.selectedZikar)) {
                                        Toast.makeText(getApplicationContext(), "Already Exist", Toast.LENGTH_SHORT).show();
                                        x1 = zikkar.get(z).toString();
                                        break;
                                    } else {
                                        //  myvar.selectedZikar = Translator.global1.myresult ;

                                    }
                                }
                            }

                            if (x1==null)
                              getlistupdated();
                            else

                            {
                                Toast.makeText(getApplicationContext(), "Please Input Zikar Properly!", Toast.LENGTH_SHORT).show();
                            }




                            // getlistupdated();




                    }


                });

        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        //dialog.cancel();
                    }
                });

        alertDialog.setView(alertLayout);
        AlertDialog dialog = alertDialog.create();
        dialog.show();

    }


    public void getlistupdated()
    {

       // new Translator(MainActivity.myvar.myTransText).execute();
        String xx1 = null, xx2 = null;
        if (Translator.global1.myresult != null) {
            for (int z = 0; z < zikkar.size(); z++) {
                if (zikkar.get(z).toString().contains(Translator.global1.myresult)) {
                    //Toast.makeText(MainActivity.this, "Already Exist", Toast.LENGTH_SHORT).show();
                    xx1 = zikkar.get(z).toString();
                    break;
                } else {
                    //  myvar.selectedZikar = Translator.global1.myresult ;

                }
            }
            // Toast.makeText(MainActivity.this, Translator.global1.myresult + " " + xx1, Toast.LENGTH_SHORT).show();
            // myvar.selectedZikar = Translator.global1.myresult;
            // tv1.setText(Translator.global1.myresult);


            if (xx1 == null) {
                boolean isInserted = myDB.custdata(Translator.global1.myresult);
                //  myvar.selectedZikar=Translator.global1.myresult;


                if (isInserted == true) {
                    Toast.makeText(getApplicationContext(), "Custom List Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            for (int z = 0; z < zikkar.size(); z++) {
                if (zikkar.get(z).toString().contains(MainActivity.myvar.selectedZikar)) {
                    Toast.makeText(getApplicationContext(), "Already Exist", Toast.LENGTH_SHORT).show();
                    xx2 = zikkar.get(z).toString();
                    break;
                } else {
                    //  myvar.selectedZikar = Translator.global1.myresult ;

                }
            }

            if (xx2 == null) {
                boolean isInserted = myDB.custdata(MainActivity.myvar.selectedZikar);
                //  myvar.selectedZikar=Translator.global1.myresult;


                if (isInserted == true) {
                    Toast.makeText(getApplicationContext(), "Custom List Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
