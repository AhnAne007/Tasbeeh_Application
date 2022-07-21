package com.example.tasbeehapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;



import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper dbHelper;

    // All Static variables
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = Config.DATABASE_NAME;

    // Constructor
    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }


    public static synchronized DBHelper getInstance(Context context){
        if(dbHelper==null){
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }



    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create tables SQL execution
        String CREATE_TASBEEH_TABLE = "CREATE TABLE " + Config.TABLE_TASBEEH + "("
                + Config.COLUMN_IMAGE_ID + " TEXT PRIMARY KEY, "
                + Config.COLUMN_IMAGE_COUNTER + " TEXT DEFAULT '0' "//default setting it to zero.
                + ")";

        Logger.d("Table create SQL: " + CREATE_TASBEEH_TABLE);

        sqLiteDatabase.execSQL(CREATE_TASBEEH_TABLE);

        Logger.d("DB created!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_TASBEEH);

        // Create tables again
        onCreate(sqLiteDatabase);

    }
}
