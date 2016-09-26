package com.example.joel.shoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Pack200;

public class ShoppingItemDataSource {

    private static final String TAG = "ItemDataSource";

    private SQLiteDatabase database;
    private ShoppingItemSQLiteOpenHelper dbHelper;

    private static final String[] itemColumns = {
            ShoppingItemSQLiteOpenHelper.COLUMN_ID,
            ShoppingItemSQLiteOpenHelper.COLUMN_NAME,
            ShoppingItemSQLiteOpenHelper.COLUMN_QUANTITY,
            ShoppingItemSQLiteOpenHelper.COLUMN_ISPURCHASED
    };

    //Constructor
    public ShoppingItemDataSource(Context context)
    {
        dbHelper = new ShoppingItemSQLiteOpenHelper(context);
    }

    //Open
    public void open()
    {
        try
        {
            database = dbHelper.getWritableDatabase();
        }
        catch (SQLException sqle)
        {
            Log.d(TAG,"Could not open database: " + sqle.getMessage());
        }
    }

    //Close
    public void close()
    {
        database.close();
    }

    //Insert
    public void insert(String name, int quanity, boolean isPurchased){

        ContentValues values = new ContentValues();

        //values.put(peopleColumns[0], 1);
        values.put(ShoppingItemSQLiteOpenHelper.COLUMN_NAME, name);
        values.put(ShoppingItemSQLiteOpenHelper.COLUMN_QUANTITY, quanity);
        values.put(ShoppingItemSQLiteOpenHelper.COLUMN_ISPURCHASED, isPurchased);


        //If entering empty rows the system needs a column name
        //Otherwise just give it null
        long id = database.insert(ShoppingItemSQLiteOpenHelper.TABLE_SHOPPINGITEM, null, values);

        Log.d(TAG, "Item " + id + " inserted.");

    }

    //Delete all
    public void deleteAll(){

        database.delete(ShoppingItemSQLiteOpenHelper.TABLE_SHOPPINGITEM, null, null);

    }

    //Get all
    public List<ShoppingItem> getAll(){

        List<ShoppingItem> items = new ArrayList<>();

        Cursor cursor = database.query(ShoppingItemSQLiteOpenHelper.TABLE_SHOPPINGITEM,
                itemColumns,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            items.add(cursorToItem(cursor));

            cursor.moveToNext();
        }

        cursor.close();

        return items;
    }

    public ShoppingItem cursorToItem(Cursor cursor)
    {
        ShoppingItem tempItem = new ShoppingItem(cursor.getString(1), cursor.getInt(2), cursor.getInt(3) == 1);
        tempItem.setId(cursor.getLong(0));
        return tempItem;
    }

    public ShoppingItem getById(long id)
    {
        Cursor cursor = database.query(ShoppingItemSQLiteOpenHelper.TABLE_SHOPPINGITEM,
                itemColumns,
                ShoppingItemSQLiteOpenHelper.SELECT_FROM+ShoppingItemSQLiteOpenHelper.TABLE_SHOPPINGITEM+ShoppingItemSQLiteOpenHelper.WHERE+ShoppingItemSQLiteOpenHelper.COLUMN_ID+"="+id,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        return cursorToItem(cursor);
    }


    public void setChecked(long id, boolean state)
    {
        ContentValues newValues = new ContentValues();
        newValues.put(ShoppingItemSQLiteOpenHelper.COLUMN_ISPURCHASED, state);

        database.update(ShoppingItemSQLiteOpenHelper.TABLE_SHOPPINGITEM, newValues, ShoppingItemSQLiteOpenHelper.COLUMN_ID+"="+id, null);
    }

    public void deleteById(long id)
    {
        database.delete(ShoppingItemSQLiteOpenHelper.TABLE_SHOPPINGITEM, ShoppingItemSQLiteOpenHelper.COLUMN_ID + " = " + id, null);

        Log.d(TAG, "Item " + id + " deleted!");
    }



}
