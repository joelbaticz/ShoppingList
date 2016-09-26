package com.example.joel.shoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<ShoppingItem> {

    public ItemAdapter(Context context, ArrayList<ShoppingItem> items) {
        super(context, 0, items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Get item from list view
        ShoppingItem item = getItem(position);

        //Create view from xml
        View v = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);

        //Get resources
        CheckBox cbItem = (CheckBox) v.findViewById(R.id.cbItem);
        TextView tvItemName = (TextView) v.findViewById(R.id.tvItemName);
        TextView tvItemQuantity = (TextView) v.findViewById(R.id.tvItemQuantity);

        //Fill in values in view
        cbItem.setTag(item);
        cbItem.setChecked(item.isPurchased());
        tvItemName.setText(item.getName());
        tvItemQuantity.setText(Integer.toString(item.getQuantity()));

        //Set onClickListener for checkbox
        cbItem.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                //Get resources
                CheckBox cbItem = (CheckBox) v;

                //Set checkbox animation
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.checkboxanim);
                v.startAnimation(animation);

                //Update status in database
                ShoppingItemDataSource itemDataSource = new ShoppingItemDataSource(getContext());
                itemDataSource.open();
                //ShoppingItem currentShoppingItem = itemDataSource.getById( ((ShoppingItem)cbItem.getTag()).getId() );
                ShoppingItem currentItem = (ShoppingItem)cbItem.getTag();
                itemDataSource.setChecked( currentItem.getId() , cbItem.isChecked());
                itemDataSource.close();

                //Prepare toast message
                String itemName=((ShoppingItem)cbItem.getTag()).getName().toString();
                String itemStatusMsg;

                if (cbItem.isChecked())
                    itemStatusMsg=" acquired.";
                else
                    itemStatusMsg=" removed.";

                Toast.makeText(getContext(),itemName + itemStatusMsg,Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }

}
