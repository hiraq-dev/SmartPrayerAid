package com.qaiser.hyra.tasbeehtcounter;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Vibrator;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Qaiser on 9/21/2016.
 */
public class VoiceService extends Service {

    private SpeechRecognizer sr;
    String result;
    Cursor c;
    Intent mIntent;
    Vibrator v;



    ArrayList mylist,arrayList2=null,Array1=null,Array2=null,Array3=null,Array4=null,Array5=null,Array6=null,Array7=null,Array8=null,
            Array9=null,Array10=null,Array11=null,Array12=null,Array13=null,CustomZikarArray=null;

    ArrayList patternarray;

    public static int count = 0;
    public static int mycounter=1;
    public static int countertakbeer=0,counterruku=0,salamcounter=0;





    boolean myflag=false;


    private static Timer timer = new Timer();
    private Context ctx;

    public IBinder onBind(Intent arg0)
    {
        return null;
    }


    private void startService()
    {
        timer.scheduleAtFixedRate(new mainTask(), 0, 2000);

    }

    private class mainTask extends TimerTask
    {
        public void run()
        {
            mIntent = new Intent(ctx,VoiceService.class);
        }
    }

    TextView tv;


    @Override
    public void onCreate() {
        super.onCreate();



        ctx = this;
        startService();




        Toast.makeText(this,"Service Started", Toast.LENGTH_SHORT).show();

        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        onClickk();

        //   startListening();



    }





    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    class listener implements RecognitionListener
    {
        public void onReadyForSpeech(Bundle params)
        {

        }
        public void onBeginningOfSpeech()
        {
            Log.d("HHHHHHH","Listening");
        }
        public void onRmsChanged(float rmsdB)
        {

        }
        public void onBufferReceived(byte[] buffer)
        {

        }
        public void onEndOfSpeech()
        {
            Log.d("HHHHHH", "Stopped");
        }

        public void onError(int error)
        {
            Log.d("HHHHHH", "Error" + error);
            onClickk();

        }

        public void onResults(Bundle results)
        {
            Log.d("HHHHHH","Result");
            String str = new String();
            ArrayList data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            for (int i = 0; i < data.size(); i++)
            {
                str += data.get(0);
                result= data.get(0).toString().toLowerCase();
                Log.d("Current Result........", data.get(0).toString());
            }


            Log.d("Recognized",result);

            if (data.size() > 0) {
                //  result = data.get(0).toString();
                check(result);
            }


        }

        boolean myflag=false;
        private void check(String result) {


            String pattern = null;
            arrayList2 = new ArabicWordsArraylists().getArrayList1();
            Array1 = (new ArabicWordsArraylists()).getArray1();
            Array2 = (new ArabicWordsArraylists()).getArray2();
            Array3 = (new ArabicWordsArraylists()).getArray3();
            Array4 = (new ArabicWordsArraylists()).getArray4();
            Array5 = (new ArabicWordsArraylists()).getArray5();
            Array6 = (new ArabicWordsArraylists()).getArray6();
            Array7 = (new ArabicWordsArraylists()).getArray7();
            Array8 = (new ArabicWordsArraylists()).getArray8();

            Array9 = (new ArabicWordsArraylists()).getArray9();
            Array10 = (new ArabicWordsArraylists()).getArray10();
            Array11 = (new ArabicWordsArraylists()).getArray11();
            Array12 = (new ArabicWordsArraylists()).getArray12();
            Array13 = (new ArabicWordsArraylists()).getArray13();
            CustomZikarArray=new MainActivity().zikkar;






            mylist=new ArabicWordsArraylists().getArrayList2();


            // ArrayList array_list1 = (new ArabicWordsArraylists()).getArrayList1();


            switch (MainActivity.myvar.myposition) {

                case 0: {
                    for (int z = 0; z < Array1.size(); z++) {
                        if (Array1.get(z).toString().contains(result)) {
                            String[] words=result.split("\\s+");
                            int xx=words.length;
                            if (xx%2==0)
                                MainActivity.myvar.zikarcount+=(xx-(xx/2));
                            if (xx%2!=0)
                                MainActivity.myvar.zikarcount+=(xx-(xx/2));

                            // mytext.setText(String.valueOf(MainActivity.myvar.zikarcount));
                            Toast.makeText(getApplicationContext(), String.valueOf(MainActivity.myvar.zikarcount), Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    break;
                }
                case 1: {
                    for (int z = 0; z < Array2.size(); z++) {
                        if (Array2.get(z).toString().contains(result)) {
                            MainActivity.myvar.zikarcount++;
                            // mytext.setText(String.valueOf(MainActivity.myvar.zikarcount));
                            Toast.makeText(getApplicationContext(), String.valueOf(MainActivity.myvar.zikarcount), Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    for (int z = 0; z < Array3.size(); z++) {
                        if (Array3.get(z).toString().contains(result)) {
                            MainActivity.myvar.zikarcount++;
                            // mytext.setText(String.valueOf(MainActivity.myvar.zikarcount));
                            Toast.makeText(getApplicationContext(), String.valueOf(MainActivity.myvar.zikarcount), Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    break;
                }

                case 3: {
                    for (int z = 0; z < Array4.size(); z++) {
                        if (Array4.get(z).toString().contains(result)) {
                            MainActivity.myvar.zikarcount++;
                            // mytext.setText(String.valueOf(MainActivity.myvar.zikarcount));
                            Toast.makeText(getApplicationContext(), String.valueOf(MainActivity.myvar.zikarcount), Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    break;
                }
                case 4: {
                    for (int z = 0; z < Array5.size(); z++) {
                        if (Array5.get(z).toString().contains(result)) {
                            String[] words=result.split("\\s+");
                            int xx=words.length;
                            if (xx%2==0)
                                MainActivity.myvar.zikarcount+=(xx-(xx/2));
                            if (xx%2!=0)
                                MainActivity.myvar.zikarcount+=(xx-(xx/2));


                            // mytext.setText(String.valueOf(MainActivity.myvar.zikarcount));
                            Toast.makeText(getApplicationContext(), String.valueOf(MainActivity.myvar.zikarcount), Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    break;
                }

                case 5: {
                    for (int z = 0; z < Array6.size(); z++) {
                        if (Array6.get(z).toString().contains(result)) {
                            MainActivity.myvar.zikarcount++;
                            // mytext.setText(String.valueOf(MainActivity.myvar.zikarcount));
                            Toast.makeText(getApplicationContext(), String.valueOf(MainActivity.myvar.zikarcount), Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    break;
                }
                case 6: {
                    for (int z = 0; z < Array7.size(); z++) {
                        if (Array7.get(z).toString().contains(result)) {
                            String[] words=result.split("\\s+");
                            int xx=words.length;
                            if (xx%2==0)
                                MainActivity.myvar.zikarcount+=(xx-(xx/2));
                            if (xx%2!=0)
                                MainActivity.myvar.zikarcount+=(xx-(xx/2));
                            // mytext.setText(String.valueOf(MainActivity.myvar.zikarcount));
                            Toast.makeText(getApplicationContext(), String.valueOf(MainActivity.myvar.zikarcount), Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    break;
                }
                case 7: {
                    for (int z = 0; z < Array8.size(); z++) {
                        if (Array8.get(z).toString().contains(result)) {
                            MainActivity.myvar.zikarcount++;
                            // mytext.setText(String.valueOf(MainActivity.myvar.zikarcount));
                            Toast.makeText(getApplicationContext(), String.valueOf(MainActivity.myvar.zikarcount), Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    break;
                }
                case 8: {
                    for (int z = 0; z < Array9.size(); z++) {
                        if (Array9.get(z).toString().contains(result)) {
                            MainActivity.myvar.zikarcount++;
                            // mytext.setText(String.valueOf(MainActivity.myvar.zikarcount));
                            Toast.makeText(getApplicationContext(), String.valueOf(MainActivity.myvar.zikarcount), Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    break;
                }
                case 9: {
                    for (int z = 0; z < Array10.size(); z++) {
                        if (Array10.get(z).toString().contains(result)) {
                            String[] words=result.split("\\s+");
                            int xx=words.length;
                            if (xx%2==0)
                                MainActivity.myvar.zikarcount+=(xx-(xx/2));
                            if (xx%2!=0)
                                MainActivity.myvar.zikarcount+=(xx-(xx/2));
                            // mytext.setText(String.valueOf(MainActivity.myvar.zikarcount));
                            Toast.makeText(getApplicationContext(), String.valueOf(MainActivity.myvar.zikarcount), Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    break;
                }
                case 10: {
                    for (int z = 0; z < Array11.size(); z++) {
                        if (Array11.get(z).toString().contains(result)) {
                            MainActivity.myvar.zikarcount++;
                            // mytext.setText(String.valueOf(MainActivity.myvar.zikarcount));
                            Toast.makeText(getApplicationContext(), String.valueOf(MainActivity.myvar.zikarcount), Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    break;
                }
                case 11: {
                    for (int z = 0; z < Array12.size(); z++) {
                        if (Array12.get(z).toString().contains(result)) {
                            MainActivity.myvar.zikarcount++;
                            // mytext.setText(String.valueOf(MainActivity.myvar.zikarcount));
                            Toast.makeText(getApplicationContext(), String.valueOf(MainActivity.myvar.zikarcount), Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    break;
                }
                case 12: {
                    for (int z = 0; z < Array13.size(); z++) {
                        if (Array13.get(z).toString().contains(result)) {
                            MainActivity.myvar.zikarcount++;
                            // mytext.setText(String.valueOf(MainActivity.myvar.zikarcount));
                            Toast.makeText(getApplicationContext(), String.valueOf(MainActivity.myvar.zikarcount), Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    break;
                }
                case 13:{


                }

                default:
                {
                    for (int z = 0; z < CustomZikarArray.size(); z++) {
                        if (CustomZikarArray.get(z).toString().contains(result)) {
                            MainActivity.myvar.zikarcount++;
                            break;
                        }
                    }

                }

            }


            onClickk();

        }


        public void onPartialResults(Bundle partialResults)
        {

        }

        public void onEvent(int eventType, Bundle params)
        {

        }

    }

    public void redirect(int i){
        mIntent.putExtra("key", i);
        startService(mIntent);
        stopSelf();
    }

    public int count(String input, String word){
        String myString=input;
        int index = myString.indexOf(word);
        int count = 0;
        while (index != -1) {
            count++;
            myString = myString.substring(index + 1);
            index = myString.indexOf(word);
        }
        return count;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(sr!=null)
            sr.destroy();
        Toast.makeText(this,"Voice Service Stopped", Toast.LENGTH_SHORT).show();

    }

    public void onClickk() {

        if (sr != null) {
            Log.d("22222222", "Destroy");
            sr.destroy();
        }




        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new listener());



        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);


        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "ar-SA");

        intent.putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES", new String[]{"ar-SA"});
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "ar-SA");
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, "ar-SA");

        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);


        AudioManager amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
        amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
        amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        amanager.setStreamMute(AudioManager.STREAM_RING, true);
        amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
        sr.startListening(intent);

        Log.i("111111", "11111111");




    }

}

