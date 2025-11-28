package com.qaiser.hyra.tasbeehtcounter;

import android.os.AsyncTask;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Qaiser on 10/5/2016.
 */
public class Translator extends AsyncTask<String, Void, String> {


    String xx1=null;



    public static class global1{
        public static String transtext=null,myresult=null;

    }
    EditText input;
public CustomZikar activity;


    public Translator(EditText vResult){
        input= vResult;
        //global1.myresult=xx1;
        input.setText("");





        //xx1="";

    }







    @Override
    protected String doInBackground(String... params) {



        String queryString=null;




            queryString = "http://api.mymemory.translated.net/get?q=" + MainActivity.myvar.myTransText + "&langpair=en|ar";



        String s = "";
        String s1="";
        try {
            s = sendQuery(queryString);
            global1.myresult=s;



        } catch (IOException e) {
            e.printStackTrace();
            s = "Network Error";





        }
        return s;

    }



    @Override
    protected void onPostExecute(String s) {
       // global1.myresult=s;
        //global1.myresult=s;
     // xx1=s;


     //  global1.myresult=xx1;
       input.setText(s);
        global1.myresult=s;
        MainActivity.myvar.selectedZikar=s;



}



    private String sendQuery(String query) throws IOException {
        String result = "";
        String objectString="",xx="";




        URL searchURL = new URL(query);

        HttpURLConnection httpURLConnection = (HttpURLConnection)searchURL.openConnection();
        if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader,
                    8192);

            try {
                String line = null;


                StringBuilder sb = new StringBuilder();


                while ((line = bufferedReader.readLine()) != null) {

                    result += line;


                }
                JSONObject object = new JSONObject(result);
                objectString = object.getString("responseData");
                global1.transtext=objectString;

                JSONObject object1 = new JSONObject(global1.transtext);
                global1.myresult=object1.getString("translatedText");



       /* String year=objectString.substring(0,4);
        String month=objectString.substring(5,7);
        String date=objectString.substring(8,10);

      //  global1.apitime=date+"-"+month+"-"+year;
        global1.apitime=objectString.substring(0,10);
        String[] separated = objectString.split(":");
        xx=separated[0].substring(separated[0].length() - 5);
        // result2=xx.substring(0,2);


        if (xx.charAt(0)=='0')
        {
                char yy= xx.charAt(1);
                global1.result2+= yy;

        }
        else
        {
                global1.result2=xx.substring(0,2);
        }
*/



            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }




            bufferedReader.close();
        }

        return global1.myresult;
    }
}
