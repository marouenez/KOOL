package com.supcom.gr39.kool;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

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
            checkBox = (CheckBox) convertView.findViewById( R.id.checkBox1 );
            convertView.setTag(holder);
            /*checkBox.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    Model planet = (Model) cb.getTag();
                    planet.setChecked( cb.isChecked() );
                }
            });*/
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mPrice = (TextView) convertView.findViewById(R.id.price);
        holder.quantity = (TextView) convertView.findViewById(R.id.quantity);
        holder.checkBox = (CheckBox) convertView.findViewById( R.id.checkBox1 );
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        BasketModel item = productList.get(position);
        holder.mProduct.setText(item.getProduct().toString());
        holder.mPrice.setText(item.getPrice().toString());
        holder.quantity.setText(String.valueOf(item.getQuantity()));

        return convertView;
    }
}