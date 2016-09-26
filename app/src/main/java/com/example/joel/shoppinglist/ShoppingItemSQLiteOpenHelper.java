package com.example.joel.shoppinglist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ShoppingItemSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "ItemSQLiteOpenHelper";

    //Database name & version
    private static final String DATABASE_NAME = "shoppingitem.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_SHOPPINGITEM = "shoppingitem";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_ISPURCHASED = "ispurchased";

    public static final String SELECT_FROM = "SELECT * FROM ";
    public static final String WHERE = "WHERE ";


    //Create table string
    private static final String CREATE_TABLE = "create table " + TABLE_SHOPPINGITEM + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_QUANTITY + " integer not null, "
            + COLUMN_ISPURCHASED + " integer not null);";

    //Customized constructor
    public ShoppingItemSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        //Drop existing table
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPINGITEM);

        //Recreate the table
        onCreate(sqLiteDatabase);
        Log.d(TAG, "Database updated!");
    }
}
