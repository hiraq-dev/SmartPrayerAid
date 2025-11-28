package com.qaiser.hyra.namazapplication;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class VoiceService extends Service {

    private SpeechRecognizer sr;
    String result;
    Cursor c;
    String[] word = new String[10];
    Intent mIntent, mIntent1;
    Vibrator v;



    ArrayList arrayList2=null,Array1=null,Array2=null,Array3=null;

    ArrayList patternarray;

   public static int count = 0;
    public static int mycounter=1;
    public static int countertakbeer=0,counterruku=0,salamcounter=0;

    boolean myflag=false;




    public VoiceService() {
    }

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

        private void check(String result) {
            long[] takbeer = {0, 2000, 300, 2000};
            long[] ruku_start = {0, 300, 150, 300};
            long[] ruku_stop = {0, 300, 150, 300, 150, 300};
            long[] first_sajda_start = {0, 300, 150, 300, 150, 300, 150, 300};
            long[] first_sajda_stop = {0, 300, 150, 300, 150, 300, 150, 300, 150, 300};
            long[] second_sajda_start = {0, 300, 150, 300, 150, 300, 150, 300, 150, 300, 150, 300};
            long[] second_sajda_stop = {0, 300, 150, 300, 150, 300, 150, 300, 150, 300, 150, 300, 150, 300};
            long[] atahiyat1 = {0, 800, 150, 800, 150};
            long[] salam = {0, 800, 150, 800};

            String pattern = null;
            arrayList2 = ((new PatternList()).getArrayList2());
            Array1 = (new ArabicWordsArraylists()).getTakbeerArray();
            Array2 = (new ArabicWordsArraylists()).getRukuArrayy();
            Array3 = (new ArabicWordsArraylists()).getSalamArray();

           // ArrayList array_list1 = (new ArabicWordsArraylists()).getArrayList1();

            Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


            for (int z = 0; z < Array1.size(); z++) {
                if (Array1.get(z).toString().contains(result)) {
                    countertakbeer++;
                    Toast.makeText(VoiceService.this, "takbeer " + countertakbeer, Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            for (int x = 0; x < Array2.size(); x++) {

                if (Array2.get(x).toString().contains(result)) {
                    counterruku++;
                    Toast.makeText(VoiceService.this, "ruku " + counterruku, Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            for (int a = 0; a < Array3.size(); a++) {
                if (Array3.get(a).toString().contains(result)) {
                    salamcounter++;
                    Toast.makeText(VoiceService.this, "salam " + salamcounter, Toast.LENGTH_SHORT).show();
                    break;
                }
            }


            ////////////////////////////////////////////////////////////////////////////

            //for 4 farz
            try {
                if ((mycounter==28 && countertakbeer == 22 && salamcounter == 2 || mycounter==28 && counterruku == 4 && salamcounter == 2)) {
                    vb.vibrate(salam, -1);
                    counterruku = 0;
                    salamcounter = 0;
                    countertakbeer = 0;
                    mycounter=1;
                } else if ((mycounter==27 && countertakbeer == 22 && salamcounter == 1 || mycounter==27 && counterruku == 4 && salamcounter == 1))
                {vb.vibrate(salam, -1);
                mycounter++;
                }

                else if (mycounter==26 && countertakbeer == 22 && counterruku == 4) {
                    vb.vibrate(second_sajda_stop, -1);
                    mycounter++;
                }

                else if (mycounter==25 && countertakbeer == 21 && counterruku == 4) {
                    vb.vibrate(second_sajda_start, -1);
                    mycounter++;
                }

                else if (mycounter==24 && countertakbeer == 20 && counterruku == 4) {
                    vb.vibrate(first_sajda_stop, -1);
                    mycounter++;
                }

                else if (mycounter==23 && countertakbeer == 19 && counterruku == 4) {
                    vb.vibrate(first_sajda_start, -1);
                    mycounter++;
                }
                else if (mycounter==22 && countertakbeer == 18 && counterruku == 4)
                {
                    vb.vibrate(ruku_stop, -1);
                    mycounter++;
                }
                else if (mycounter==21 && countertakbeer == 18 && counterruku == 3) {
                    vb.vibrate(ruku_start, -1);
                    mycounter++;
                }
                else if (mycounter==20 && countertakbeer == 17 && counterruku == 3) {
                    vb.vibrate(second_sajda_stop, -1);
                    mycounter++;
                }

                else if (mycounter==19 && countertakbeer == 16 && counterruku == 3) {
                    vb.vibrate(second_sajda_start, -1);
                    mycounter++;
                }
                else if (mycounter==18 && countertakbeer == 15 && counterruku == 3) {
                    vb.vibrate(first_sajda_stop, -1);
                    mycounter++;
                }
                else if (mycounter==17 && countertakbeer == 14 && counterruku == 3) {
                    vb.vibrate(first_sajda_start, -1);
                    mycounter++;
                }
                else if (mycounter==16 && counterruku == 3 && countertakbeer == 13) {
                    vb.vibrate(ruku_stop, -1);
                    mycounter++;
                }
                else if (mycounter==15 && counterruku == 2 && countertakbeer == 13) {
                    vb.vibrate(ruku_start, -1);
                    mycounter++;
                }

                else if (mycounter==14 && countertakbeer == 12) {
                    vb.vibrate(atahiyat1, -1);
                    mycounter++;
                }
                    //////////////////////2 Rakat /////////////////////////////////////
                else if (mycounter==13 && countertakbeer == 11 && counterruku == 2) {
                    vb.vibrate(second_sajda_stop, -1);
                    mycounter++;
                }

                else if (mycounter==12 && countertakbeer == 10 && counterruku == 2) {
                    vb.vibrate(second_sajda_start, -1);
                    mycounter++;
                }

                else if (mycounter==11 && countertakbeer == 9 && counterruku == 2) {
                    vb.vibrate(first_sajda_stop, -1);
                    mycounter++;
                }

                else if (mycounter==10 && countertakbeer == 8 && counterruku == 2) {
                    vb.vibrate(first_sajda_start, -1);
                    mycounter++;
                }

                else if (mycounter==9 && counterruku == 2 && countertakbeer == 7) {
                    vb.vibrate(ruku_stop, -1);
                    mycounter++;
                }

                else if ((mycounter==8 && countertakbeer == 7 && counterruku == 1)) {
                    vb.vibrate(ruku_start, -1);
                    mycounter++;
                }

                else if ((mycounter==7 && countertakbeer == 6 && counterruku == 1)) {
                    vb.vibrate(second_sajda_stop, -1);
                    mycounter++;
                }

                else if ((mycounter==6 && countertakbeer == 5 && counterruku == 1)) {

                    vb.vibrate(second_sajda_start, -1);
                    mycounter++;
                }

                else if ((mycounter==5 && countertakbeer == 4 && counterruku == 1)) {
                    vb.vibrate(first_sajda_stop, -1);
                    mycounter++;
                }
                else if ((mycounter==4 && countertakbeer == 3 && counterruku == 1))
                {
                    vb.vibrate(first_sajda_start, -1);
                    mycounter++;
                }
                else if ((mycounter==3 && countertakbeer == 2 && counterruku==1)) {
                    vb.vibrate(ruku_stop, -1);
                    mycounter++;
                }
                else if (mycounter==2 && countertakbeer == 2) {
                    vb.vibrate(ruku_start, -1);
                    mycounter++;

                }
                else if ( mycounter==1 && countertakbeer == 1 ) {
                    vb.vibrate(takbeer, -1);
                    mycounter++;
                }







                else {

                }


            }


            catch (Exception e) {
                e.printStackTrace();
            }















                                ///////////////////////////////////////////////////////////////////////////////







               //}


           //}

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



    @Override
    public void onDestroy() {
        super.onDestroy();
        if(sr!=null)
            sr.destroy();
        Toast.makeText(this,"Voice Service Stopped", Toast.LENGTH_SHORT).show();
        counterruku = 0;
        salamcounter = 0;
        countertakbeer = 0;
        mycounter=1;
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
