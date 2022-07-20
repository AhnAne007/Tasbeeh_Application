package com.example.tasbeehapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseQueryClass {
    private Context context;


    //Constructor
    public DatabaseQueryClass(Context context) {
        this.context = context;
    }

    public long insertTasbeeh(Tasbeeh tasbeeh){

        long id = -1;
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_IMAGE_ID, String.valueOf(tasbeeh.getImage()));
        contentValues.put(Config.COLUMN_IMAGE_COUNTER, tasbeeh.getCount());
        try {
            id = sqLiteDatabase.insertOrThrow(Config.TABLE_TASBEEH, null, contentValues);
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return id;
    }


    public List<Tasbeeh> getAllTasbeehs(){

        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_TASBEEH, null, null, null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<Tasbeeh> tasbeehList = new ArrayList<>();
                    do {
                        String id = cursor.getString(cursor.getColumnIndexOrThrow(Config.COLUMN_IMAGE_ID));
                        String count = cursor.getString(cursor.getColumnIndexOrThrow(Config.COLUMN_IMAGE_COUNTER));
                        tasbeehList.add(new Tasbeeh(Integer.parseInt(id), count));
                    }   while (cursor.moveToNext());

                    return tasbeehList;
                }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }


    public long updateTasbeehCounter(Tasbeeh tasbeeh){

        long rowCount = 0;
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_IMAGE_ID, String.valueOf(tasbeeh.getImage()));
        contentValues.put(Config.COLUMN_IMAGE_COUNTER, tasbeeh.getCount());

        try {
            rowCount = sqLiteDatabase.update(Config.TABLE_TASBEEH, contentValues,
                    Config.COLUMN_IMAGE_ID + " = ? ",
                    new String[] {String.valueOf(tasbeeh.getImage())});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowCount;
    }


    public Tasbeeh getTasbeehByImageId(String registrationNum){

        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Tasbeeh tasbeeh = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_TASBEEH, null,
                    Config.COLUMN_IMAGE_ID + " = ? ", new String[]{String.valueOf(registrationNum)},
                    null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above sqLiteDatabase.query() method.

             String SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_STUDENT, Config.COLUMN_STUDENT_REGISTRATION, String.valueOf(registrationNum));
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor.moveToFirst()){
                String id = cursor.getString(cursor.getColumnIndexOrThrow(Config.COLUMN_IMAGE_ID));
                String count = cursor.getString(cursor.getColumnIndexOrThrow(Config.COLUMN_IMAGE_COUNTER));
                tasbeeh = new Tasbeeh(Integer.parseInt(id), count);
            }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return tasbeeh;
    }


}
