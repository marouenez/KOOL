package com.supcom.gr39.kool;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by marouenez on 29/10/16.
 */

public class listviewAdapter extends BaseAdapter {

    public ArrayList<BasketModel> productList;
    Activity activity;


    public listviewAdapter(Activity activity, ArrayList<BasketModel> productList) {
        super();
        //super();
        this.activity = activity;
        this.productList = productList;
    }



    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private class ViewHolder {
        TextView quantity;
        TextView mProduct;
        TextView mPrice;
        CheckBox checkBox;
        LinearLayout relativeLayout ;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CheckBox checkBox;
        final ViewHolder holder;

        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder = new ViewHolder();
            holder.mProduct = (TextView) convertView.findViewById(R.id.product);

            holder.mPrice = (TextView) convertView.findViewById(R.id.price);
            holder.checkBox = (CheckBox) convertView.findViewById( R.id.checkBox1 );
            holder.mProduct = (TextView) convertView.findViewById(R.id.product);
            holder.relativeLayout = (LinearLayout) convertView.findViewById(R.id.relativeLayout1);

            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mPrice = (TextView) convertView.findViewById(R.id.price);
        holder.quantity = (TextView) convertView.findViewById(R.id.quantity);
        holder.checkBox = (CheckBox) convertView.findViewById( R.id.checkBox1 );

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.tab.set(position, holder.checkBox.isChecked());
            }
        });
        final BasketModel item = productList.get(position);
        holder.mProduct.setText(item.getProduct().toString());
        holder.mPrice.setText(item.getPrice().toString());
        holder.quantity.setText(String.valueOf(item.getQuantity()));

        holder.relativeLayout = (LinearLayout) convertView.findViewById(R.id.relativeLayout1);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.checkBox.setChecked(!holder.checkBox.isChecked());

                MainActivity.tab.set(position, holder.checkBox.isChecked());
            }
        });




        return convertView;
    }



}