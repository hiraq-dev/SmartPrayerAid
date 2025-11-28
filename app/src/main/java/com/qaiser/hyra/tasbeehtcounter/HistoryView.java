package com.qaiser.hyra.tasbeehtcounter;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.qaiser.hyra.tasbeehtcounter.R.layout.activity_history_view;

public class HistoryView extends AppCompatActivity {


    DataBaseHelper db;
    ArrayAdapter<String> adapter;
    ArrayList<String> history = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_history_view);
        final DataBaseHelper db = new DataBaseHelper(this);
       // GridView gv = (GridView) findViewById(R.id.gridView);
        getdata();


    }



    public void getdata() {

        final DataBaseHelper db = new DataBaseHelper(this);
        /////////////////////////////////////////////////////////////////////////////////////
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        //  tv.removeAllViewsInLayout();
        int flag = 1;

        TableRow tr = new TableRow(HistoryView.this);


        Cursor res = db.getHistory();
        if (res.getCount() == 0) {

        } else {
            TableRow rowHeader = new TableRow(HistoryView.this);
            rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
            rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT));
            String[] headerText={"Zikar","Counter","Left","Total" ,"Status"};
            for(String c:headerText) {
                TextView tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv.setGravity(Gravity.LEFT);
                tv.setTextSize(16);
                tv.setPadding(6, 6, 6, 6);
                tv.setText(c);
                rowHeader.addView(tv);
            }
            tableLayout.addView(rowHeader);

            }

            // history.clear();
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                //  buffer.append(res.getString(0) );

               // buffer.append(res.getString(1));
                //buffer.append(res.getString(2));
                //buffer.append(res.getString(3));
               // buffer.append(res.getString(3));

                String id = res.getString(1) + "  " + res.getString(2) + "   " + res.getString(3) +"   " + res.getString(4) +"   " + res.getString(5)+ "\n";
                history.add(id);
                // data.add(datanum);
                try {


                    // this will be executed once


/////////////////////////////////////////////////////////////////////////////////////////

                    TableRow row = new TableRow(HistoryView.this);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.MATCH_PARENT));
                    String[] colText = {res.getString(1) + "", res.getString(2), res.getString(3),res.getString(4),res.getString(5)};
                    for (String text : colText) {
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv.setGravity(Gravity.LEFT);
                        tv.setTextSize(15);
                        tv.setPadding(5, 5, 5, 5);
                        tv.setWidth(20);
                        tv.setText(text);
                        row.addView(tv);

                    }
                    tableLayout.addView(row);


                }

                 catch (Exception e) {
                    Log.e("log_tag", "Error parsing data " + e.toString());
                    Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
                }


            }
        }
    }
