package com.example.joel.shoppinglist;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity
{
    private ShoppingItemDataSource itemDataSource;
    private ItemAdapter ia=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //Get resources
        ListView lv = (ListView) findViewById(R.id.lvView);

        //Populate list view with items
        ShoppingItemDataSource itemDataSource = new ShoppingItemDataSource(this);
        itemDataSource.open();
        List<ShoppingItem> actualItems = itemDataSource.getAll();
        itemDataSource.close();

        ia = new ItemAdapter(ViewActivity.this, (ArrayList) actualItems);
        lv.setAdapter(ia);

        //Hide Empty List label if there are items in the list
        TextView tvEmptyList = (TextView) findViewById(R.id.tvEmptyList);
        tvEmptyList.setTextColor(getResources().getColor(R.color.colorText));
        if (ia.getCount()!=0)
            tvEmptyList.setTextColor(getResources().getColor(R.color.colorTransparent));

    }


}
