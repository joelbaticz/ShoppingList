package com.example.joel.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ClearItemAdapter extends ArrayAdapter<ShoppingItem> {

    private Context context;
    private ArrayAdapter ia = null;
    private ArrayList<ShoppingItem> items;

    public ClearItemAdapter(Context context, ArrayList<ShoppingItem> items)
    {
        super(context, 0, items);
        this.context=context;
        //this.ia=this;
        this.items=items;

    }
    @Override
    public View getView(int position, View convertView, final ViewGroup parent)
    {
        //Get item from list view
        ShoppingItem item = getItem(position);

        //Create view from xml
        View v = LayoutInflater.from(getContext()).inflate(R.layout.clear_row_layout, parent, false);

        //Get resources
        TextView tvItemName = (TextView) v.findViewById(R.id.tvItemName);
        TextView tvItemQuantity = (TextView) v.findViewById(R.id.tvItemQuantity);
        Button btnDelete = (Button) v.findViewById(R.id.btnItemDelete);

        //Fill in values in view
        tvItemName.setText(item.getName());
        tvItemQuantity.setText(Integer.toString(item.getQuantity()));
        btnDelete.setTag(item);

        //Set onClickListener for delete button
        btnDelete.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                //Get resources
                final Button btnClickedDelete=(Button) v;

                //Set up delete button animation
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.buttonanim);
                btnClickedDelete.startAnimation(animation);

                animation.setAnimationListener(new Animation.AnimationListener(){
                    public void onAnimationStart(Animation a){}
                    public void onAnimationRepeat(Animation a){}
                    public void onAnimationEnd(Animation a){

                        //Set up row animation
                        Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.rowanim);
                        ((View)btnClickedDelete.getParent()).startAnimation(animation2);


                        animation2.setAnimationListener(new Animation.AnimationListener(){
                            public void onAnimationStart(Animation a){}
                            public void onAnimationRepeat(Animation a){}
                            public void onAnimationEnd(Animation a)
                            {
                                //Notify view about data change
                                notifyDataSetChanged();
                            }
                        });

                    }
                });

                //Get shopping item to be deleted
                ShoppingItem itemToBeDeleted = (ShoppingItem) btnClickedDelete.getTag();

                //Delete item from database
                ShoppingItemDataSource itemDataSource = new ShoppingItemDataSource(getContext());
                itemDataSource.open();
                itemDataSource.deleteById( itemToBeDeleted.getId() );
                itemDataSource.close();

                //Delete from list view's dataset as well
                items.remove(itemToBeDeleted);

                //Prepare toast message
                String itemName=itemToBeDeleted.getName().toString();
                String itemStatusMsg=itemName+" has been removed.";
                Toast.makeText(getContext(),itemStatusMsg,Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

}
