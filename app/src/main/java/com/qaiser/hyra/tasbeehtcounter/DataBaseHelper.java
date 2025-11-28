package com.qaiser.hyra.tasbeehtcounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Qaiser on 9/23/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TasbeehCounter.db";
    public static final String TABLE_NAME1 = "MasterTable";
    public static final String TABLE_NAME2 = "auto_tasbeeh";
    public static final String TABLE_NAME3 = "manual_tasbeeh";
    public static final String TABLE_NAME4 = "completed_tasbeeh";
    public static final String TABLE_NAME5 = "incompleted_tasbeeh";
    public static final String TABLE_NAME6 = "Wazeefa";
    public static final String TB = "MYCUSTZIKAR";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "ZIKAR_NAME";
    public static final String COL_3 = "SETCOUNTER";
    public static final String COL_4 = "Remaining";
    public static final String COL_5 = "Total";
    public static final String COL_6 = "Status";
    public static final String COL_7 = "Sel_type";
    public static final String COL_8 = "Date";
    public static final String COL_9 = "Time";
    public static final String CO1 = "id";
    public static final String CO2 = "NAMECUST";



    public static class global{
        public static long lastid;
    }


    public static final String Table1="CREATE TABLE " + TABLE_NAME1 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,ZIKAR_NAME TEXT, " +
            "SETCOUNTER Number,Remaining Number,Total Number,Status TEXT ,Sel_type TEXT,Date TEXT,Time TEXT)";

    public static final String T2="CREATE TABLE "+ TB + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAMECUST TEXT)";
    // public static final String Table2="CREATE TABLE " + TABLE_NAME2 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,ZIKAR_NAME TEXT, SETCOUNTER Number,LEFT INTEGER,Total Number,TYPE INTEGER DEFAULT 0,Sel_type TEXT,DateTime TEXT)";


    public static final String Table3="CREATE TABLE " + TABLE_NAME6 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Wazeefa_NAME TEXT, " +
            "SETCOUNTER Number,Remaining Number,Total Number,Status TEXT ,Sel_type TEXT,Date TEXT,Time TEXT)";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Table1);
        db.execSQL(T2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS table1");
        db.execSQL("DROP TABLE IF EXISTS t2");
        onCreate(db);

    }

    public boolean MASTER_DATA(String Z_Name, String Set_Count, int Total, int left, String stat, String Seltype, String Date, String Time ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL_2, Z_Name);
        contentvalues.put(COL_3, Set_Count);
        contentvalues.put(COL_4, left);
        contentvalues.put(COL_5, Total);
        contentvalues.put(COL_6, stat);
        contentvalues.put(COL_7, Seltype);
        contentvalues.put(COL_8, Date);
        contentvalues.put(COL_9, Time);
        long result = db.insert(TABLE_NAME1, null, contentvalues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean custdata(String czikar)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(CO2, czikar);
        long result1 = db.insert(TB, null, contentvalues);

        if (result1 == -1) {
            return false;
        } else {
            return true;
        }
    }






    public Cursor getHistory() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME1, null);
        return res;
    }

    public Cursor getlist() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TB, null);
        return res;
    }

    public Cursor lastinserteddata(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT ZIKAR_NAME,SETCOUNTER ,Remaining,Total,Status from " + TABLE_NAME1 ;
        Cursor c = db.rawQuery(query,null);
        //if (c != null && c.moveToFirst()) {
          //  global.lastid = c.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
       // }
        return c;
    }



    public boolean UpdatelastinsertedData(String zikar, String setcounter, int remaining, int total, String status) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL_2, zikar);
        contentvalues.put(COL_3, setcounter);
        contentvalues.put(COL_4, remaining);
        contentvalues.put(COL_5, total);
        contentvalues.put(COL_6, status);

        long xx= db.update(TABLE_NAME1, contentvalues, "ZIKAR_NAME=?",new String[]{zikar});


        if (xx!=0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    ///////////////////////////////////////////////////////////////////

                    public boolean ResumeUpdate(String Z_Name, String Set_Count, int Total, int left, String stat, String Seltype, String Date, String Time)
                    {
                        SQLiteDatabase db = this.getReadableDatabase();
                        ContentValues contentvalues = new ContentValues();

                        contentvalues.put(COL_2, Z_Name);
                        contentvalues.put(COL_3, Set_Count);
                        contentvalues.put(COL_4, left);
                        contentvalues.put(COL_5, Total);
                        contentvalues.put(COL_6, stat);
                        contentvalues.put(COL_7, Seltype);
                        contentvalues.put(COL_8, Date);
                        contentvalues.put(COL_9, Time);


                        long xx= db.update(TABLE_NAME1, contentvalues, "ZIKAR_NAME=?",new String[]{Z_Name});

                        if (xx!=0)
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }

                    }

    /////////////////////////////////////////////////////////////////////////////////
    public Cursor SelectResume(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT ZIKAR_NAME,SETCOUNTER ,Remaining,Total,Date,Time FROM MasterTable WHERE ZIKAR_NAME= ?", new String[] {name});
        return res;
    }


}
