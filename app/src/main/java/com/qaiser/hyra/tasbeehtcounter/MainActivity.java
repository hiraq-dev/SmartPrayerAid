package com.qaiser.hyra.tasbeehtcounter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static class myvar {
        public static String selectedZikar = null, gc = null;
        public static int zikarcount = 0, zc = 0;
        public static boolean visible = false;
        public static int myposition;
        public static String customtext = null;
        public static String getcounter = null;
        public static String status;
        public static String sel_type;
        public static String id = null;
        public static int reset = 0;
        public static int manual = 0;
        public static int Auto = 0;
        public static String myTransText = null, Message = null, xx2;


        public static int grandtotal = 0, total = 0, prevcounter;
    }

    int counter = 0, counter1 = 0;
    TextView tv, tv1, tv2, tv3;
    ListView lv;
    public static ArrayList zikkar, mylist;
    DataBaseHelper myDB;
//Locale locale;


    /////////////////////////////////////////////////////////////////////////

    private static final boolean AUTO_HIDE = true;

    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
try {
    mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
}
catch (Exception e)
{
    e.printStackTrace();
}
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
   /* private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };*/


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }}


    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
try {
    mControlsView.setVisibility(View.GONE);
    mVisible = false;
}
catch (Exception e)
{
    e.printStackTrace();
}
        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }





    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
      //  mHideHandler.removeCallbacks(mHideRunnable);
       // mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public static class GCUtils {
        private static final int GC_TRY_COUNT = 2;
        // GC_TRY_LOOP_MAX is used for the hard limit of GC wait,
        // GC_TRY_LOOP_MAX should be greater than GC_TRY_COUNT.
        private static final int GC_TRY_LOOP_MAX = 5;
        private static final long GC_INTERVAL = DateUtils.SECOND_IN_MILLIS;
        private static final GCUtils sInstance = new GCUtils();
        private int mGCTryCount = 0;

        public static GCUtils getInstance() {
            return sInstance;
        }

        public boolean performOperationWithMemRetry(String TAG, MemRelatedOperation operation, boolean failWithException) {
            reset();

            boolean retry = true;
            while (retry) {
                try {
                    operation.operation();
                    return true;
                } catch (OutOfMemoryError e) {
                    Log.w(TAG, "No memory, attempt to release some ");
                    retry = tryGCOrWait(TAG, e);
                    if (!retry && failWithException) throw e;
                }
            }
            return false;
        }

        private void reset() {
            mGCTryCount = 0;
        }

        private boolean tryGCOrWait(String metaData, Throwable t) {
            if (mGCTryCount % GC_TRY_COUNT == 0) {
                System.gc();
            }
            if (mGCTryCount > GC_TRY_LOOP_MAX) {
                return false;
            } else {
                mGCTryCount++;
                try {
                    Thread.sleep(GC_INTERVAL);
                    return true;
                } catch (InterruptedException e) {
                    Log.e(metaData, "Sleep was interrupted.");
                    //ImeLogger.logOnException(metaData, t);
                    return false;
                }
            }
        }

        public interface MemRelatedOperation {
            void operation();
        }
    }

    ///////////////////////////////////////////////////////////////////////////




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
//


        myDB = new DataBaseHelper(this);
        startTimerThread();


        tv = (TextView) findViewById(R.id.textView2);
        tv1 = (TextView) findViewById(R.id.textView6);
        tv2 = (TextView) findViewById(R.id.textView3);
        tv3 = (TextView) findViewById(R.id.textView4);


    }


    ////////////////////////////////////////////////
    public void startButton(View view) {
        overridePendingTransition(R.anim.rotateout, R.anim.stay
        );

        showChangeLangDialog();
        // Toast.makeText(MainActivity.this, String.valueOf(myvar.manual),Toast.LENGTH_LONG).show();
    }

    private void initialize() {

        // Start receiver with the name StartupReceiver_Manual_Start
        // Check AndroidManifest.xml file
        getBaseContext().getApplicationContext().sendBroadcast(
                new Intent("StartupReceiver_Manual_Start"));
    }


    ///////////////////////////////////////////////
    public void counterlimit() {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.activity_get_counter, null);
        final EditText etUsername = (EditText) alertLayout.findViewById(R.id.editText);
        // final CheckBox cbShowPassword = (CheckBox) alertLayout.findViewById(R.id.checkBox);
        // cbShowPassword.setChecked(false);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Set Counter(Limit)");

        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });


        //  cbShowPassword.setChecked(false);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                myvar.getcounter = etUsername.getText().toString();
                //  myvar.getcounter = input.getText().toString();
                if (myvar.getcounter != null && (Integer.parseInt(myvar.getcounter)) != 0) {

                    if (myvar.Auto == 1) {
                        //finish();
                        myvar.sel_type = "Automatic";
                        //  overridePendingTransition( 0, 0);
                       /* startActivity(getIntent());
                        overridePendingTransition(
                                R.anim.rotatein, R.anim.stay
                        );*/

                        initialize();
                        Intent jj = new Intent(getApplicationContext(), VoiceService.class);
                        startService(jj);


                        //  myvar.manual=0;
                        // Toast.makeText(getApplicationContext(), "FALSE", Toast.LENGTH_SHORT).show();

                    }
                    if (myvar.manual == 1) {
                        myvar.sel_type = "Manual";

                        overridePendingTransition(
                                R.anim.rotatein, R.anim.rotateout
                        );
                        //  myvar.manual=1;
                        //Toast.makeText(getApplicationContext(), "TRUE", Toast.LENGTH_SHORT).show();

                    }

                    /////////////////////////////////////////////////////////////
                    ///////////////////////////////////////////////////////////

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = df.format(c.getTime());
                    SimpleDateFormat xx = new SimpleDateFormat("hh:mm:ss aa");

                    String time = xx.format(c.getTime());


/////////////////////////////////////////for insert list////////////////////////////////////////////////////////////////////////////

                    getlistupdated();

////////////////////////////////////////for insert//////////////////////////////////////////////////////////////////////
                    boolean isInserted = myDB.MASTER_DATA(myvar.selectedZikar, etUsername.getText().toString(),
                            myvar.total, myvar.zikarcount, myvar.status, myvar.sel_type
                            , formattedDate, time);
                    tv1.setText(myvar.selectedZikar);
                    Toast.makeText(MainActivity.this, myvar.selectedZikar, Toast.LENGTH_SHORT).show();


                    if (isInserted == true) {

                        Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Not Saved, Try Again", Toast.LENGTH_SHORT).show();
                    }

                    // Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();
                    //  dialog.cancel();
                }


                // Toast.makeText(getBaseContext(), "Counter: " + user , Toast.LENGTH_SHORT).show();
            }

            ///////////////////////////////////////////////////////////////////////////////////////////////


        });


        alert.setView(alertLayout);
        AlertDialog dialog = alert.create();
        dialog.show();

    }


//////////////////////////////////////////////


    /////////////////////////////////////////////////////////////

    public void showChangeLangDialog() {
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
                    //  mylist.add(xx);
                    zikkar.add(xx);

                }
            }

            ArrayAdapter<String> aa = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, zikkar);
            lv.setAdapter(aa);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                    // myvar.zikarcount = 0;
                    //  myvar.manual=0;
                    myvar.selectedZikar = zikkar.get(i).toString();
                    myvar.zikarcount = 0;
                    myvar.total = 0;
                    myvar.getcounter = null;
                    myvar.status=null;
                    myvar.zc=0;

                    myvar.myposition = i;

                    if (myvar.selectedZikar != null && myvar.myposition != 13) {

                        Cursor res = myDB.SelectResume(myvar.selectedZikar);
                        if (res.getCount() == 0) {
                            Toast.makeText(MainActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                            counterlimit();
                        }
                        else {

                            // history.clear();
                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {
                                //  buffer.append(res.getString(0) );

                                //  buffer.append(res.getString(1));
                                //buffer.append(res.getString(2));
                                //buffer.append(res.getString(3));

                                //  myvar.selectedZikar = res.getString(1);
                               // myvar.getcounter=res.getString(1);
                                //myvar.total= res.getInt(2);
                                myvar.zc=res.getInt(3);


                                myvar.Message = "Recited:" + res.getString(3).toString() + " At:" + res.getString(4).toString() + " " + res.getString(5).toString();
                                //myvar.status=res.getString(5);


                                //history.add(id);
                                // data.add(datanum);

                            }

                            // buffer.append("Date: "+res.getString(4)+"\n");
                        }









                        if ( myvar.zc!=0) {

                            ResumeDialoge();
                            dialog.cancel();


                        }

                    }
                    if (zikkar.get(i).toString().contains("Add Custom")) {
                        myvar.selectedZikar="";
                        CustomZikar();

                        dialog.cancel();


                    }

                }
            });

            //dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            dialog.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /////////////////////////////////////////////////////////////


    private void startTimerThread() {
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                ////////////////////////////////reset////////////////////////////////////////


                                /////////////////////////////////////////////////////////////////////////////////

                                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                                if (myvar.manual == 1 && myvar.getcounter != null) {

                                    RelativeLayout rl = (RelativeLayout) findViewById(R.id.relative1);


                                    rl.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //    Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_LONG).show();
                                            myvar.zikarcount++;
                                            tv.setText(String.valueOf(myvar.zikarcount));

                                            Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            long[] vib = {0, 200, 150, 200};
                                            v1.vibrate(vib, -1);
                                            //lastrowupdate();

                                        }
                                    });
                                    tv.setText(String.valueOf(myvar.zikarcount));
                                    tv1.setText(myvar.selectedZikar);
                                    if (myvar.selectedZikar != null && myvar.myposition != 13)
                                        tv1.setText(myvar.selectedZikar);

                                    tv2.setText("Total: " + myvar.getcounter);
                                    myvar.total = ((Integer.parseInt(myvar.getcounter)) - (myvar.zikarcount));
                                    tv3.setText("Left: " + String.valueOf(myvar.total));
                                    myvar.status = "Incomplete";


                                    if ((myvar.total <= 0)) {
                                        //  myvar.grandtotal += myvar.zikarcount;
                                        myvar.status = "Completed";
                                        myvar.total = 0;
                                        counter++;
                                        tv3.setText("Recited: " + myvar.zikarcount);
                                        //myvar.total=0;
                                        //myvar.total = ((Integer.parseInt(myvar.getcounter)) + (myvar.zikarcount));
                                    }
                                    if (myvar.status == "Completed" && counter == 1) {
                                        // counter=0;
                                        long[] vib = {0, 2000, 300, 2000};
                                        v.vibrate(vib, -1);
                                    }
                                    rowupdate();

                                }


                                if (myvar.Auto == 1 && myvar.getcounter != null) {

                                    RelativeLayout rl = (RelativeLayout) findViewById(R.id.relative1);


                                    rl.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //    Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_LONG).show();
                                            myvar.zikarcount++;
                                            tv.setText(String.valueOf(myvar.zikarcount));
                                            Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            long[] vib = {0, 200, 150, 200};
                                            v1.vibrate(vib, -1);

                                            //lastrowupdate();

                                        }
                                    });

                                    tv.setText(String.valueOf(myvar.zikarcount));
                                    tv1.setText(myvar.selectedZikar);
                                    if (myvar.selectedZikar != null && myvar.myposition != 13)
                                        tv1.setText(myvar.selectedZikar);

                                    tv2.setText("Total: " + myvar.getcounter);
                                    myvar.total = ((Integer.parseInt(myvar.getcounter)) - (myvar.zikarcount));
                                    tv3.setText("Left: " + String.valueOf(myvar.total));
                                    myvar.status = "InComplete";

                                    if ((myvar.total <= 0)) {
                                        //  myvar.grandtotal += myvar.zikarcount;
                                        myvar.status = "Completed";
                                        myvar.total = 0;
                                        counter1++;
                                        tv3.setText("Recited: " + myvar.zikarcount);

                                        //myvar.total=0;
                                        //myvar.total = ((Integer.parseInt(myvar.getcounter)) + (myvar.zikarcount));
                                    }
                                    // lastrowupdate();
                                    if (myvar.status == "Completed" && counter1 == 1) {

                                        long[] vib ={0, 2000, 300, 2000};
                                        v.vibrate(vib, -1);

                                    }
                                    rowupdate();

                                }


                            }
                        });
                    }
                } catch (SecurityException rr) {
                    rr.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t.start();
    }

    /////////////////////////getRESET//////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////

    public void rowupdate() {




        Cursor res = myDB.SelectResume(myvar.selectedZikar);
        if (res.getCount() != 0) {
            Boolean isUpdate = myDB.UpdatelastinsertedData(myvar.selectedZikar, myvar.getcounter, myvar.total, myvar.zikarcount, myvar.status);


            if (isUpdate == true) {
                //   Toast.makeText(MainActivity.this, "Custom List Updated", Toast.LENGTH_SHORT).show();
            } else {
                //   Toast.makeText(MainActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();
            }
        }
        //   String[] zikar1={""};

      /*  try {

            Cursor res = myDB.getHistory();
            if (res.getCount() == 0) {
                Toast.makeText(MainActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
            } else {

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append(res.getString(1));
                    String x1 = res.getString(1).toString();

                   // Boolean isUpdate = myDB.UpdatelastinsertedData(res.getString(1).toString(), res.getString(2).toString(), Integer.parseInt(res.getString(3)), Integer.parseInt(res.getString(4)), res.getString(5).toString());


                    //  mylist.add(xx);
                }

                // buffer.append("Date: "+res.getString(4)+"\n");

            }
        }
            catch(Exception e)

            {
                e.printStackTrace();
            }


        }
//        Toast.makeText(MainActivity.this, myvar.id, Toast.LENGTH_LONG).show();

*/
    }







    /////////////////////////////////////////////////////////////////////////

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
        if (id==R.id.action_history)
        {

            Intent intent = new Intent(getApplicationContext(),HistoryView.class);
            startActivity(intent);

            return true;
        }


        return super.onOptionsItemSelected(item);

    }
    /////////////////////////////////////////////////////////////////////////
    public void AlertReset()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Are you sure you want to Reset?");
        // alertDialog.setMessage("Zikar");

        // final EditText input = new EditText(ManualTasbeeh.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        //input.setLayoutParams(lp);
        // alertDialog.setIcon(R.drawable.key);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        myvar.zikarcount = 0;
                        myvar.total = 0;
                        myvar.getcounter = null;
                        myvar.selectedZikar = null;
                        myvar.manual = 0;
                        myvar.Auto=0;
                        myvar.reset=1;
                        myvar.id=null;
                        myvar.status=null;
                        stopService(new Intent(getApplicationContext(), VoiceService.class));


                        finish();
                        //overridePendingTransition(0,0);
                        // getWindow().setWindowAnimations(0);

                        startActivity(getIntent());
                        overridePendingTransition(
                                R.anim.rotatein, R.anim.rotateout
                        );

                        Intent ii=new Intent(MainActivity.this,VoiceService.class);
                        stopService(ii);

                        tv3.setText("");
                        tv2.setText("");
                        tv.setText("");

                        //tv.setText(String.valueOf(myvar.zikarcount));
                        tv1.setText("");



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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myvar.zikarcount = 0;
        myvar.total = 0;
        myvar.getcounter = null;
        myvar.selectedZikar = null;
        myvar.manual = 0;
        myvar.Auto=0;
        // myvar.reset=1;
        myvar.id=null;

        stopService(new Intent(getApplicationContext(), VoiceService.class));
        //  startActivity(getIntent());
        overridePendingTransition(
                R.anim.rotatein, R.anim.rotateout
        );


        //Intent ii=new Intent(MainActivity.this,VoiceService.class);
        //stopService(ii);

        tv3.setText("");
        tv2.setText("");
        tv.setText("");

        //tv.setText(String.valueOf(myvar.zikarcount));
        tv1.setText("");



    }

//////////////////////////add Custom Zikar////////////////////////////////////////////////////////

    public void CustomZikar() {
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
                    myvar.myTransText = input.getText().toString().replace(" ", "%20");
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

                    mVisible = true;
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

                        if (myvar.selectedZikar != null) {


                            if (option2.isChecked() == true) {
                                myvar.selectedZikar = input.getText().toString();
                                //Keyboard

                            }
                            if (option1.isChecked() == false && option2.isChecked() == false)
                                myvar.selectedZikar = input.getText().toString();


                            //

                            // tv1.setText(myvar.selectedZikar);
                            dialog.cancel();

                            String x1 = null;

                            if (myvar.selectedZikar!=null) {
                                for (int z = 0; z < zikkar.size(); z++) {
                                    if (zikkar.get(z).toString().contains(myvar.selectedZikar)) {
                                        Toast.makeText(MainActivity.this, "Already Exist", Toast.LENGTH_SHORT).show();
                                        x1 = zikkar.get(z).toString();
                                        break;
                                    } else {
                                        //  myvar.selectedZikar = Translator.global1.myresult ;

                                    }
                                }
                            }

                            if (x1==null)
                                counterlimit();




                            // getlistupdated();


                        } else

                        {
                            Toast.makeText(getApplicationContext(), "Please Input Zikar Properly!", Toast.LENGTH_SHORT).show();
                        }

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


    public void getlistupdated() {

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
                    Toast.makeText(MainActivity.this, "Custom List Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            for (int z = 0; z < zikkar.size(); z++) {
                if (zikkar.get(z).toString().contains(myvar.selectedZikar)) {
                    Toast.makeText(MainActivity.this, "Already Exist", Toast.LENGTH_SHORT).show();
                    xx2 = zikkar.get(z).toString();
                    break;
                } else {
                    //  myvar.selectedZikar = Translator.global1.myresult ;

                }
            }

            if (xx2 == null) {
                boolean isInserted = myDB.custdata(myvar.selectedZikar);
                //  myvar.selectedZikar=Translator.global1.myresult;


                if (isInserted == true) {
                    Toast.makeText(MainActivity.this, "Custom List Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void ResumeDialoge()
    {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.activity_resume, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Already Exist!!");

        alertDialog.setMessage(myvar.selectedZikar+"\n Already Exist, "+myvar.Message +" Do You Want to Resume or Update?");
        alertDialog.setCancelable(false);
        // myvar.getcounter="";
//        myvar.zikarcount= Integer.parseInt("");




        alertDialog.setPositiveButton("Update",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {



                        setlimitResume();

                    }



                });

        alertDialog.setNegativeButton("Resume",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Cursor res = myDB.SelectResume(myvar.selectedZikar);
                        if (res.getCount() == 0) {
                            Toast.makeText(MainActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            // history.clear();
                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {
                                //  buffer.append(res.getString(0) );

                                //  buffer.append(res.getString(1));
                                //buffer.append(res.getString(2));
                                //buffer.append(res.getString(3));

                                //  myvar.selectedZikar = res.getString(1);
                                myvar.getcounter=res.getString(1);
                                myvar.total= res.getInt(2);
                                myvar.zikarcount=res.getInt(3);
                                //myvar.status=res.getString(5);


                                //history.add(id);
                                // data.add(datanum);

                            }

                            // buffer.append("Date: "+res.getString(4)+"\n");
                        }


                        if (myvar.Auto == 1) {
                            tv1.setText(myvar.selectedZikar);
                            initialize();
                            startService(new Intent(getApplicationContext(), VoiceService.class));


                            myvar.sel_type = "Automatic";

                          /*  startActivity(getIntent());
                            overridePendingTransition(
                                    R.anim.rotatein, R.anim.stay
                            );*/

                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            String formattedDate = df.format(c.getTime());
                            SimpleDateFormat xx = new SimpleDateFormat("hh:mm:ss aa");
                            String time = xx.format(c.getTime());
                            //myvar.getcounter=myvar.gc;
                            // myvar.zikarcount=myvar.zc;
                            // lastrowupdate();
                            boolean isInserted = myDB.ResumeUpdate(myvar.selectedZikar, myvar.getcounter, myvar.total, myvar.zikarcount, myvar.status, myvar.sel_type
                                    , formattedDate, time);
                            if (isInserted == true) {

                                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(MainActivity.this, "Not Saved, Try Again", Toast.LENGTH_SHORT).show();
                            }


                        }
                        if (myvar.manual == 1) {
                            myvar.sel_type = "Manual";

                            overridePendingTransition(
                                    R.anim.rotatein, R.anim.rotateout
                            );

                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            String formattedDate = df.format(c.getTime());
                            SimpleDateFormat xx = new SimpleDateFormat("hh:mm:ss aa");
                            String time = xx.format(c.getTime());
                            //myvar.getcounter=myvar.gc;
                            // myvar.zikarcount=myvar.zc;
                            // lastrowupdate();
                            boolean isInserted = myDB.ResumeUpdate(myvar.selectedZikar, myvar.getcounter, myvar.total, myvar.zikarcount, myvar.status, myvar.sel_type
                                    , formattedDate, time);
                            if (isInserted == true) {

                                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(MainActivity.this, "Not Saved, Try Again", Toast.LENGTH_SHORT).show();
                            }


                        }










                        //dialog.cancel();
                    }
                });

        alertDialog.setView(alertLayout);
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    public void setlimitResume(){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.activity_get_counter, null);
        final EditText etUsername = (EditText) alertLayout.findViewById(R.id.editText);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Set Counter(Limit)");

        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });


        //  cbShowPassword.setChecked(false);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


                myvar.getcounter = etUsername.getText().toString();
                //  myvar.getcounter = input.getText().toString();
                if (myvar.getcounter!=null && (Integer.parseInt(myvar.getcounter))!=0) {



                    if (myvar.Auto == 1) {
                        tv1.setText(myvar.selectedZikar);
                        initialize();
                        startService(new Intent(getApplicationContext(), VoiceService.class));
                        //finish();
                        myvar.sel_type = "Automatic";
                        //  overridePendingTransition( 0, 0);
                      /*  startActivity(getIntent());
                        overridePendingTransition(
                                R.anim.rotatein, R.anim.stay
                        );*/

                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate = df.format(c.getTime());
                        SimpleDateFormat xx= new SimpleDateFormat("hh:mm:ss aa");
                        String time = xx.format(c.getTime());

                        myvar.total=0;
                        myvar.zikarcount=0;
                        boolean isInserted = myDB.ResumeUpdate(myvar.selectedZikar,myvar.getcounter,myvar.total,myvar.zikarcount,myvar.status,myvar.sel_type
                                ,formattedDate, time);
                        if (isInserted == true) {

                            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();


                        }
                        else {
                            Toast.makeText(MainActivity.this, "Not Saved, Try Again", Toast.LENGTH_SHORT).show();
                        }


                        //  myvar.manual=0;
                        // Toast.makeText(getApplicationContext(), "FALSE", Toast.LENGTH_SHORT).show();

                    }
                    if (myvar.manual == 1) {
                        myvar.sel_type = "Manual";

                        overridePendingTransition(
                                R.anim.rotatein, R.anim.rotateout
                        );


                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate = df.format(c.getTime());
                        SimpleDateFormat xx= new SimpleDateFormat("hh:mm:ss aa");
                        String time = xx.format(c.getTime());

                        myvar.total=0;
                        myvar.zikarcount=0;
                        boolean isInserted = myDB.ResumeUpdate(myvar.selectedZikar,myvar.getcounter,myvar.total,myvar.zikarcount,myvar.status,myvar.sel_type
                                ,formattedDate, time);
                        if (isInserted == true) {

                            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();


                        }
                        else {
                            Toast.makeText(MainActivity.this, "Not Saved, Try Again", Toast.LENGTH_SHORT).show();
                        }


                    }



                }
            }

            ///////////////////////////////////////////////////////////////////////////////////////////////



        });

        alert.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {



                        //dialog.cancel();
                    }
                });





        alert.setView(alertLayout);
        AlertDialog dialog = alert.create();
        dialog.show();

    }
////////////////////////////////////keyboard arabic/////////////////////////////////////////////////////////////////////////


}