package com.example.joel.shoppinglist;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void onClickViewList(View v)
    {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    public void onClickAddItem(View v)
    {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void onClickClearList(View v)
    {
        Intent intent = new Intent(this, ClearActivity.class);
        startActivity(intent);
    }


}
