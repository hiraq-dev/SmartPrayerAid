package com.qaiser.hyra.islamiccalendar;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Qaiser on 8/29/2016.
 */

public class CurrentTimeAPI extends AsyncTask<String, Void, String> {
       // TextView tResult;

        public static class global1{
                public static String result2=null;
                public static String apitime=null;
                public static String mylocation=null;

        }


public CurrentTimeAPI(){
        // vResult=global1.result2;
     //   tResult.setText("");
        }



@Override
protected String doInBackground(String... params) {



        String queryString =
                "http://api.geonames.org/timezoneJSON?lat=" + CaldroidGridAdapter.myGlobal.strLat
                        + "&lng=" + CaldroidGridAdapter.myGlobal.strLon + "&username=hyra.qaiser";


        String s = "";
        String s1="";
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

        //tResult.setText(s);
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
        objectString = object.getString("timezoneId");
        global1.mylocation=objectString;
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

        return global1.mylocation;
        }
        }
