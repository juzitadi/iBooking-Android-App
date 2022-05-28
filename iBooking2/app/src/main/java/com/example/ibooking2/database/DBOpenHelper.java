package com.example.ibooking2.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ibooking2.R;
import com.example.ibooking2.object.Order;
import com.example.ibooking2.object.Room;
import com.example.ibooking2.object.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static SQLiteDatabase db;
    public DBOpenHelper(Context context){
        super(context,"db_test",null,1);
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // 创建 USER 表
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT)");

        // 创建 ROOM 表
        db.execSQL("CREATE TABLE IF NOT EXISTS room(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "location TEXT," +
                "full_capacity INTEGER," +
                "image_url INTEGER," +
                "score INTEGER," +
                "description TEXT)");

        /* ROOM 数据 */
        db.execSQL("INSERT INTO room (name, location, full_capacity, image_url, score, description) " +
                "  VALUES('SA169','SA', 50," + R.drawable.test1 +", 4, 'test word')");
        db.execSQL("INSERT INTO room (name, location, full_capacity, image_url, score, description) " +
                "  VALUES('BS169','BS', 50," + R.drawable.test3 +", 2, 'test word')");
        db.execSQL("INSERT INTO room (name, location, full_capacity, image_url, score, description) " +
                "  VALUES('CB169','CB', 50," + R.drawable.test2 + ", 1, 'test word')");
        db.execSQL("INSERT INTO room (name, location, full_capacity, image_url, score, description) " +
                "  VALUES('SD554','SD', 50," + R.drawable.test1 +", 5, 'test word')");
        db.execSQL("INSERT INTO room (name, location, full_capacity, image_url, score, description) " +
                "  VALUES('SD169','SD', 50,"+ R.drawable.test3 +", 3, 'test word')");
        db.execSQL("INSERT INTO room (name, location, full_capacity, image_url, score, description) " +
                "  VALUES('FB169','FB', 50,"+ R.drawable.test1+", 1, 'test word')");
        db.execSQL("INSERT INTO room (name, location, full_capacity, image_url, score, description) " +
                "  VALUES('SD102','SD', 50,"+ R.drawable.test2 +", 2, 'test word')");
        db.execSQL("INSERT INTO room (name, location, full_capacity, image_url, score, description) " +
                "  VALUES('CB554','CB', 50,"+ R.drawable.test3 +", 5, 'test word')");
        db.execSQL("INSERT INTO room (name, location, full_capacity, image_url, score, description) " +
                "  VALUES('SB123','SB', 50,"+ R.drawable.test1 +", 2, 'test word')");
        db.execSQL("INSERT INTO room (name, location, full_capacity, image_url, score, description) " +
                "  VALUES('BS111','BS', 50,"+ R.drawable.test2 +", 1, 'test word')");
        db.execSQL("INSERT INTO room (name, location, full_capacity, image_url, score, description) " +
                "  VALUES('SA322','SA', 50,"+ R.drawable.test3 +", 1, 'test word')");
        db.execSQL("INSERT INTO room (name, location, full_capacity, image_url, score, description) " +
                "  VALUES('SD454','SD', 50,"+ R.drawable.test1 +", 3, 'test word')");
        db.execSQL("INSERT INTO room (name, location, full_capacity, image_url, score, description) " +
                "  VALUES('PB102','PB', 50,"+ R.drawable.test2+", 3, 'test word')");
        db.execSQL("INSERT INTO room (name, location, full_capacity, image_url, score, description) " +
                "  VALUES('PB502','PB', 50,"+ R.drawable.test3 +", 4, 'test word')");
        db.execSQL("INSERT INTO room (name, location, full_capacity, image_url, score, description) " +
                "  VALUES('G13W','CB', 50,"+ R.drawable.test1 +", 4, 'test word')");

        // 创建 TIMETABLE 表
        db.execSQL("CREATE TABLE IF NOT EXISTS timetable(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "roomName TEXT," + "weekday TEXT," + "current_capacity INTEGER," +
                "time_9 INTEGER," + "time_10 INTEGER," + "time_11 INTEGER," + "time_12 INTEGER," +
                "time_13 INTEGER," + "time_14 INTEGER," + "time_15 INTEGER," + "time_16 INTEGER," +
                "time_17 INTEGER," + "time_18 INTEGER," + "time_19 INTEGER," + "time_20 INTEGER," +
                "time_21 INTEGER)");

        // 创建 ORDER 表
        db.execSQL("CREATE TABLE IF NOT EXISTS orderTable(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userName TEXT," +
                "roomName TEXT," +
                "ordered_date TEXT," +
                "create_date TEXT)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SA169','Monday', 32, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SA169','Tuesday', 14, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD554','Monday', 18, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD102','Monday', 15, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SB123','Monday', 9, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('PB102','Monday', 37, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD102','Tuesday', 20, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16," +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SA169','Monday', 14, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SA169','Tuesday', 17, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SA169','Wednesday', 30, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SA169','Thursday', 5, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21)" +
                "  VALUES('SA169','Friday', 17, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SA169','Saturday', 2, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SA169','Sunday', 2, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1)");

// BS169
        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('BS169','Monday', 11, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('BS169','Tuesday', 25, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16," +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('BS169','Wednesday', 36, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('BS169','Thursday', 17, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('BS169','Friday', 6, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                " VALUES('BS169','Saturday', 42, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('BS169','Sunday', 10, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0)");

// CB169
        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('CB169','Monday', 9, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                " VALUES('CB169','Tuesday', 19, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('CB169','Wednesday', 6, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('CB169','Thursday', 17, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('CB169','Friday', 5, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16," +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('CB169','Saturday', 18, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('CB169','Sunday', 26, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0)");

// SD554
        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD554','Monday', 49, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21)" +
                "  VALUES('SD554','Tuesday', 13, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD554','Wednesday', 3, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD554','Thursday', 37, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD554','Friday', 27, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21)" +
                "  VALUES('SD554','Saturday', 6, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD554','Sunday', 9, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0)");
// SD169
        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD169','Monday', 7, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16," +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD169','Tuesday', 16, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD169','Wednesday', 2, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD169','Thursday', 28, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD169','Friday', 14, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16," +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD169','Saturday', 7, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD169','Sunday', 33, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0)");

// FB169
        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('FB169','Monday', 19, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                " VALUES('FB169','Tuesday', 18, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('FB169','Wednesday', 17, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('FB169','Thursday', 25, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('FB169','Friday', 37, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21)" +
                "  VALUES('FB169','Saturday', 25, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('FB169','Sunday', 12, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1)");

// SD102
        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD102','Monday', 14, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD102','Tuesday', 28, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16," +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD102','Wednesday', 27, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD102','Thursday', 15, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD102','Friday', 17, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                " VALUES('SD102','Saturday', 5, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD102','Sunday', 2, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1)");

// CB554
        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('CB554','Monday', 34, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('CB554','Tuesday', 21, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('CB554','Wednesday', 37, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('CB554','Thursday', 16, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16," +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('CB554','Friday', 12, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('CB554','Saturday', 14, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('CB554','Sunday', 22, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1)");

// SB123
        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SB123','Monday', 31, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16," +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SB123','Tuesday', 11, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SB123','Wednesday', 17, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SB123','Thursday', 36, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SB123','Friday', 49, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SB123','Saturday', 11, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SB123','Sunday', 22, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0)");

// BS111
        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16," +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('BS111','Monday', 30, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('BS111','Tuesday', 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('BS111','Wednesday', 7, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('BS111','Thursday', 30, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('BS111','Friday', 40, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('BS111','Saturday', 17, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('BS111','Sunday', 19, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1)");

// SA322
        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16," +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SA322','Monday', 14, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SA322','Tuesday', 17, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SA322','Wednesday', 10, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SA322','Thursday', 5, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SA322','Friday', 27, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SA322','Saturday', 35, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16," +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SA322','Sunday', 42, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1)");

// SD454
        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD454','Monday', 5, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD454','Tuesday', 18, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD454','Wednesday', 20, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD454','Thursday', 35, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16," +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD454','Friday', 28, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD454','Saturday', 39, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('SD454','Sunday', 40, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1)");

// PB102
        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('PB102','Monday', 15, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('PB102','Tuesday', 8, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16," +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('PB102','Wednesday', 27, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('PB102','Thursday', 34, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('PB102','Friday', 23, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('PB102','Saturday', 38, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('PB102','Sunday', 43, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1)");

// PB502
        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('PB502','Monday', 16, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('PB502','Tuesday', 18, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16," +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('PB502','Wednesday', 25, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('PB502','Thursday', 14, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('PB502','Friday', 21, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('PB502','Saturday', 32, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('PB502','Sunday', 23, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0)");

// G13W
        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('G13W','Monday', 12, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('G13W','Tuesday', 15, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('G13W','Wednesday', 22, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('G13W','Thursday', 24, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('G13W','Friday', 45, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('G13W','Saturday', 36, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0)");

        db.execSQL("INSERT INTO timetable (roomName, weekday, current_capacity, time_9, time_10, time_11, time_12, time_13, time_14, time_15, time_16, " +
                "time_17, time_18, time_19, time_20, time_21) " +
                "  VALUES('G13W','Sunday', 27, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    // USER表 Operations
    public void add(String name,String password){
        db.execSQL("INSERT INTO user (name,password) VALUES(?,?)",new Object[]{name,password});

    }
    public void delete(String name,String password){
        db.execSQL("DELETE FROM user WHERE name = AND password ="+name+password);
    }
    public void update(String password){
        db.execSQL("UPDATE user SET password = ?",new Object[]{password});
    }
    public ArrayList<User> getAllData(){
        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(name,password));
        }
        return list;
    }

    // ROOM表 Operations
    /* 0. 加载rooms信息 */
    public static void roomTableInitialize(String xls_path, SQLiteDatabase db){



    }

    /* 1. 根据room名获取room信息 */
    public static Room getRoomByName(String inputName){
        Cursor cursor = db.rawQuery("select * from room where name=?", new String[]{inputName});  //check
        Room room = null;
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String location = cursor.getString(cursor.getColumnIndex("location"));
            Integer full_capacity = cursor.getInt(cursor.getColumnIndex("full_capacity"));
            Integer image_url = cursor.getInt(cursor.getColumnIndex("image_url"));
            Integer score = cursor.getInt(cursor.getColumnIndex("score"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            room=new Room(name,location,image_url,full_capacity,score, description);
        }
        return room;
    }

    /* 2. 主页调用这个,返回全部room*/
    public static ArrayList<Room> getAvailableRoom(String currentDay){
        ArrayList<Room> list = new ArrayList<Room>();
        Cursor cursor = db.rawQuery("select roomName from timetable where weekday = ?", new String[]{currentDay});
        while (cursor.moveToNext()){
            Cursor cursor1 = db.rawQuery("select * from room where name = ?", new String[]{cursor.getString(cursor.getColumnIndex("roomName"))});
            if (cursor1.moveToNext()){
                String name = cursor1.getString(cursor1.getColumnIndex("name"));
                String location = cursor1.getString(cursor1.getColumnIndex("location"));
                Integer full_capacity = cursor1.getInt(cursor1.getColumnIndex("full_capacity"));
                Integer image_url = cursor1.getInt(cursor1.getColumnIndex("image_url"));
                Integer score = cursor1.getInt(cursor1.getColumnIndex("score"));
                String description = cursor1.getString(cursor1.getColumnIndex("description"));
                list.add(new Room(name, location, image_url, full_capacity, score, description));
            }
        }
        return list;
    }

    /* 3. 返回指定location的room: e.g FB, SA, EE; Capacity要手动调用getCapacityByRoomAndWeekday */
    public ArrayList<Room> getRoomByLocation(String inputLocation){
        ArrayList<Room> list = new ArrayList<Room>();
        Cursor cursor = db.rawQuery("select * from room where location =?", new String[]{inputLocation});
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String location = cursor.getString(cursor.getColumnIndex("location"));
            Integer full_capacity = cursor.getInt(cursor.getColumnIndex("full_capacity"));
            Integer image_url = cursor.getInt(cursor.getColumnIndex("image_url"));
            Integer score = cursor.getInt(cursor.getColumnIndex("score"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            list.add(new Room(name, location, image_url, full_capacity, score, description));
        }
        return list;
    }

    /* 4. 搜索调用, 返回匹配关键字的room: 仅在name栏匹配; Capacity要手动调用getCapacityByRoomAndWeekday */
    public static ArrayList<Room> getRoomByKeyword(String keyword) {
        ArrayList<Room> list = new ArrayList<Room>();
        Cursor cursor = db.query("room", null, "name like ?", new String[]{"%"+keyword+"%"},
                null, null, null);
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String location = cursor.getString(cursor.getColumnIndex("location"));
            Integer full_capacity = cursor.getInt(cursor.getColumnIndex("full_capacity"));
            Integer image_url = cursor.getInt(cursor.getColumnIndex("image_url"));
            Integer score = cursor.getInt(cursor.getColumnIndex("score"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            list.add(new Room(name, location, image_url, full_capacity, score, description));
        }
        return list;
    }

    /* 5. 返回全部的room, 并按score降序排列; Capacity要手动调用getCapacityByRoomAndWeekday */
    public static ArrayList<Room> sortRoomByScore(String currentDay) {
        ArrayList<Room> list = new ArrayList<Room>();
        Cursor cursor = db.rawQuery("select * from room order by score desc", null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String location = cursor.getString(cursor.getColumnIndex("location"));
            Integer full_capacity = cursor.getInt(cursor.getColumnIndex("full_capacity"));
            Integer image_url = cursor.getInt(cursor.getColumnIndex("image_url"));
            Integer score = cursor.getInt(cursor.getColumnIndex("score"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            list.add(new Room(name, location, image_url, full_capacity, score, description));
        }
        return list;
    }

    /* 6. 返回全部room, 按location排列*/
    public static ArrayList<Room> sortRoomByLocation(String currentDay){
        ArrayList<Room> list = new ArrayList<Room>();
        Cursor cursor = db.rawQuery("select * from room order by location", null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String location = cursor.getString(cursor.getColumnIndex("location"));
            Integer full_capacity = cursor.getInt(cursor.getColumnIndex("full_capacity"));
            Integer image_url = cursor.getInt(cursor.getColumnIndex("image_url"));
            Integer score = cursor.getInt(cursor.getColumnIndex("score"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            list.add(new Room(name, location, image_url, full_capacity, score, description));
        }
        return list;
    }

    /* 7. 返回全部room, 按capacity升序*/
    public static ArrayList<Room> sortByCapacity(String currentDay){
        ArrayList<Room> list = new ArrayList<Room>();
        Cursor cursor = db.rawQuery("select roomName from timetable where weekday = ? order by current_capacity", new String[]{currentDay});
        while (cursor.moveToNext()){
            Cursor cursor1 = db.rawQuery("select * from room where name = '" + cursor.getString(cursor.getColumnIndex("roomName")) +"'", null);
            while (cursor1.moveToNext()){
                String name = cursor1.getString(cursor1.getColumnIndex("name"));
                String location = cursor1.getString(cursor1.getColumnIndex("location"));
                Integer full_capacity = cursor1.getInt(cursor1.getColumnIndex("full_capacity"));
                Integer image_url = cursor1.getInt(cursor1.getColumnIndex("image_url"));
                Integer score = cursor1.getInt(cursor1.getColumnIndex("score"));
                String description = cursor1.getString(cursor1.getColumnIndex("description"));
                list.add(new Room(name, location, image_url, full_capacity, score, description));
            }
        }
        return list;
    }

    //timetable表 operations
    /* 0. 加载timetables信息 */
    public static void timeTableInitialize(String xls_path){


    }

    /* 1. 根据room名获取其timetable信息 */
    public static HashMap<String, ArrayList<String>> getTimeByRoom(String inputRoomName){
        HashMap<String, ArrayList<String>> timeMap = new HashMap<>();
        Cursor cursor = db.rawQuery("select * from timetable where roomName =?", new String[]{inputRoomName});
        while (cursor.moveToNext()){
            ArrayList<String> timeList = new ArrayList<>();
            String weekday = cursor.getString(cursor.getColumnIndex("weekday"));
            for(int i = 9; i <= 21; i++){
                int flag = cursor.getInt(cursor.getColumnIndex("time_" + i));
                if (flag == 1) {
                    timeList.add(String.valueOf(i));
                    }
                }
            timeMap.put(weekday, timeList);
        }
        return timeMap;
    }

    /* 2. 根据room名&&具体星期几获取特定weekday的time table信息 */
    public static ArrayList<String> getTimeByRoomAndWeekday(String inputRoomName, String inputWeekday){
        ArrayList<String> timeList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from timetable where roomName =? And weekday =?", new String[]{inputRoomName, inputWeekday});
        if (cursor.moveToNext()){
            for(int i = 9; i <= 21; i++){
                int flag = cursor.getInt(cursor.getColumnIndex("time_" + i));
                if (flag == 1) {
                    timeList.add(String.valueOf(i));
                }
            }
        }
        return timeList;
    }

    /* 3. 根据room名 && 具体星期几获取特定weekday的current_capacity信息*/
    public static Integer getCapacityByRoomAndWeekday(String inputRoomName, String inputWeekday){
        Cursor cursor = db.rawQuery("select * from timetable where roomName =? And weekday =?", new String[]{inputRoomName, inputWeekday});
        if (cursor.moveToNext()){
            return cursor.getInt(cursor.getColumnIndex("current_capacity"));
        }
        return null;
    }

    /* 4. 定期update capacity: 重置, 变更等*/
    public static void updateCapacity(String inputRoomName, String inputWeekday, int value) {
        db.execSQL("UPDATE timetable SET current_capacity = ? " +
                "where roomName ='"  + inputRoomName+ "' and weekday = '" + inputWeekday + "'", new Integer[]{value});
        System.out.println(inputRoomName+"人数修改成功！变成"+ value);
    }



    //order表
    /* 0. date <=> time*/
    public static Object timeAndDateTranslation(String date, int mode) throws ParseException {
        //format
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        switch (mode){
            case 0 :
                //reminder界面筛选时，将ordered_time的"yyyy-MM-dd"转化为int，与create_time作比较
                int ordered_time= (int) sdf.parse(date).getTime();  //转换成毫秒的int
                return ordered_time;
            case 1:
                //预约时，获取当前''年月日分秒''，存为create_time
                Calendar calendar1 = Calendar.getInstance();
                sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String create_time1=sdf.format(calendar1.getTime());
                return create_time1;
            case 2:
                //reminder界面筛选时，获取当前''年月日''，转化为int，与ordered_time作比较
                Calendar calendar2 = Calendar.getInstance();
                int create_time2=(int) sdf.parse(sdf.format(calendar2.getTime())).getTime();
                return create_time2;
        }
        return null;
    }

    /* 1. 生成一条order*/                                               //"yyyy-MM-dd"
    public static void createAnOrder(String userName, String roomName, String ordered_date) throws ParseException {
        String create_date= (String) timeAndDateTranslation(null,1);  //转换为"yyyy-MM-dd"的string

        db.execSQL("INSERT INTO orderTable (userName,roomName,ordered_date,create_date) VALUES(?,?,?,?)",
                new Object[]{userName,roomName,ordered_date,create_date});
    }

    /* 2. 根据userName返回order list*/
    public static ArrayList getOrderListByUserName(String userName) {
        ArrayList<Order> orderList=new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from orderTable where userName=?", new String[]{userName});
        while(cursor.moveToNext()) {
            Integer order_id = cursor.getInt(cursor.getColumnIndex("_id"));
            String roomName=cursor.getString(cursor.getColumnIndex("roomName"));
            String ordered_date=cursor.getString(cursor.getColumnIndex("ordered_date"));
            String create_date=cursor.getString(cursor.getColumnIndex("create_date"));

            orderList.add(new Order(order_id,userName,roomName,ordered_date,create_date));
        }
        return orderList;
    }

    /* 3. 根据userName和当前时间返回reminder里面的order list*/
    public static ArrayList getOrderListForReminder(String userName) throws ParseException {
        ArrayList<Order> preOrderList=getOrderListByUserName(userName);
        ArrayList<Order> orderList=new ArrayList<>();
        int currentTime= (int) timeAndDateTranslation(null,2);
        int orderedTime;
        for(Order order:preOrderList){
            orderedTime=(int) timeAndDateTranslation(order.getOrdered_date(),0);
            if (orderedTime>currentTime) orderList.add(order);
        }
        return orderList;
    }

    /* 4. 根据id删除对应的order*/
    public static void deleteOrderById(int order_id){
        db.execSQL("DELETE FROM orderTable WHERE _id ="+order_id);
    }

    /* 5. 根据username和ordered date查询order*/
    public static ArrayList<Order> getOrderByUsernameAndDate(String username, String given_ordered_date){
        ArrayList<Order> orderList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from orderTable where userName=? and ordered_date=?", new String[]{username, given_ordered_date});
        while (cursor.moveToNext()) {
            Integer order_id = cursor.getInt(cursor.getColumnIndex("_id"));
            String roomName=cursor.getString(cursor.getColumnIndex("roomName"));
            String ordered_date=cursor.getString(cursor.getColumnIndex("ordered_date"));
            String create_date=cursor.getString(cursor.getColumnIndex("create_date"));

            Order myOrder = new Order(order_id,username,roomName,ordered_date,create_date);
            orderList.add(myOrder);
        }
        return orderList;
    }



}
