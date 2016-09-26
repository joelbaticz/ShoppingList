package com.example.joel.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ClearActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clear);

        //Get resources
        ListView lv = (ListView) findViewById(R.id.lvClear);

        //Populate list view with items
        ShoppingItemDataSource itemDataSource = new ShoppingItemDataSource(this);
        itemDataSource.open();
        List<ShoppingItem> actualItems = itemDataSource.getAll();
        itemDataSource.close();

        ClearItemAdapter ia = new ClearItemAdapter(ClearActivity.this, (ArrayList) actualItems);
        ia.setNotifyOnChange(true);
        lv.setAdapter(ia);

    }

    public void onBtnClearClick(View v)
    {
        //Get resources
        ShoppingItemDataSource itemDataSource = new ShoppingItemDataSource(this);

        //Delete items from database
        itemDataSource.open();
        itemDataSource.deleteAll();
        itemDataSource.close();

        //Navigate back to MainActivity

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        //Show toast message
        Toast.makeText(ClearActivity.this, "List cleared!",Toast.LENGTH_SHORT).show();

    }

}
