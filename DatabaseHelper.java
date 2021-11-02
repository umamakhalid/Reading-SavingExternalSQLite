package com.innova.ustethapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "foo.db";
    public static final String TABLE_NAME = "MyTable";
    private SQLiteDatabase sqLiteDatabase;
    public static String DB_FILEPATH = "/data/data/com.example.MyApplication/databases/foo.db";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME,null , 1);
    }

    public DatabaseHelper(Context context, String yes) {
        super(context, DATABASE_NAME,null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE MyTable(" +
        /*0*/   "    Id INTEGER PRIMARY KEY AUTOINCREMENT , \n"+
        /*1*/   "    Col_1 TEXT , \n"+
        /*2*/  "    Col_2 TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public Cursor getTableData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor c = db.rawQuery(sql, null);
        return c;
    }


    public void deleteAllTables(){

    }

    public void exportDB() {
        try {
            File dbFile = new File(DB_FILEPATH);
            String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HHmm").format(Calendar.getInstance().getTime());;

            if(dbFile.exists()){
                Log.e("DatabaseHelper", "exists -> "+dbFile.getPath());
            }

            FileInputStream fis = new FileInputStream(dbFile);

            File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "PathToFolder" );
            if (!directory.exists()){
                directory.mkdirs();
            }

            String outFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "PathToFolder" + File.separator +
                    timeStamp+"_"+DATABASE_NAME;

            // Open the empty db as the output stream
            OutputStream output = new FileOutputStream(outFileName);

            // Transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            // Close the streams
            output.flush();
            output.close();
            fis.close();


        } catch (IOException e) {
            Log.e("dbBackup:", e.getMessage());
        }
    }

}