package com.example.joel.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AddActivity extends AppCompatActivity {

    private ShoppingItemDataSource itemDataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);
    }

    public void onBtnAddClick(View v)
    {
        //Get resources
        EditText etName = (EditText) findViewById(R.id.etName);
        EditText etQuantity = (EditText) findViewById(R.id.etQuantity);

        if (!etName.getText().toString().equals("") && !etQuantity.getText().toString().equals(""))
        {

            String name = etName.getText().toString();
            int quantity;

            try {
                quantity = Integer.parseInt(etQuantity.getText().toString());
            }
            catch (Exception e)
            {
                Toast.makeText(AddActivity.this, "Quantity needs to be a number", Toast.LENGTH_SHORT).show();
                return;
            }

            //Add item to database
            itemDataSource = new ShoppingItemDataSource(this);
            itemDataSource.open();
            itemDataSource.insert(name, quantity, false);
            itemDataSource.close();

            //Clear fields
            etName.setText("");
            etQuantity.setText("");

            //Navigate back to MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            //Show toast
            Toast.makeText(AddActivity.this, name + " added successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //Empty fields

            //Show toast
            Toast.makeText(AddActivity.this, "Please fill in both fields.", Toast.LENGTH_SHORT).show();

        }


    }

    public void onBtnAddBasicClick(View v)
    {
        //Set up basic items list
        List<ShoppingItem> basicList = new ArrayList<ShoppingItem>();

        basicList.add(new ShoppingItem("Bread", 2, false));
        basicList.add(new ShoppingItem("Milk", 4, false));
        basicList.add(new ShoppingItem("Eggs", 1, false));
        basicList.add(new ShoppingItem("Bacon", 2, false));
        basicList.add(new ShoppingItem("Yoghurt", 2, false));
        basicList.add(new ShoppingItem("Tomato", 1, false));
        basicList.add(new ShoppingItem("Cheese", 1, false));
        basicList.add(new ShoppingItem("Pasta", 1, false));
        basicList.add(new ShoppingItem("Tomato sauce", 2, false));

        //Add items to database
        itemDataSource = new ShoppingItemDataSource(this);
        itemDataSource.open();

        for (int i=0; i<basicList.size(); i++)
        {
            itemDataSource.insert(basicList.get(i).getName(), basicList.get(i).getQuantity(), basicList.get(i).isPurchased());
        }

        itemDataSource.close();

        //Navigate back to MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        //Show toast
        Toast.makeText(AddActivity.this, basicList.size() + " items added successfully",Toast.LENGTH_SHORT).show();

    }
}
