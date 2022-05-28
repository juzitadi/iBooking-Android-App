package com.example.ibooking2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadFile {
    public static void initDataTable(Context context, String fileName, SQLiteDatabase db){
        try{
            InputStream inputStream = context.getAssets().open(fileName+".txt");
            InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bfr = new BufferedReader(isr);
            String sql;
            while((sql=bfr.readLine())!=null){
                db.execSQL(sql);
            }

        }catch (IOException e1){
            e1.printStackTrace();
        }
    }
}
