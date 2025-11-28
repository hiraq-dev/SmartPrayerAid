package com.qaiser.hyra.islamiccalendar;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Qaiser on 9/11/2016.
 */
public class HijriTime extends AsyncTask<String, Void, String> {
     TextView tResult1;

    public static class global4{
       public static String myresult=null,mydate=null;

    }

    public HijriTime(){

    }






    @Override
    protected String doInBackground(String... params) {

        String queryString =
                "http://api.aladhan.com/gToH?date="+CurrentTimeAPI.global1.apitime;


        String s = "";
        try {
            s = sendQuery(queryString);

        } catch (IOException e) {
            e.printStackTrace();
            s = e.getMessage();

        }
        return s;
    }

    @Override
    protected void onPostExecute(String s) {

       // tResult1.setText(s);
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


                String line = null;


            //    StringBuilder sb = new StringBuilder();


                while ((line = bufferedReader.readLine()) != null) {

                    result += line;


                }
                //JSONObject object = new JSONObject(result);
            //    objectString = object.getString("date");
              //  global4.mydate=objectString;

                //String[] separated = objectString.split(":");
              //  xx=separated[0].substring(separated[0].length() - 5);
                // result2=xx.substring(0,2);


                    global4.myresult=result;
            global4.myresult=result.substring(58,60);





            bufferedReader.close();
        }

        return global4.myresult;
    }

}
