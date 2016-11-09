package com.supcom.gr39.kool;

import android.support.design.internal.ForegroundLinearLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BasketActivty extends AppCompatActivity {
    ListView listview ;
    SparseBooleanArray sparseBooleanArray ;
    private ArrayList<BasketModel> productL;

    DatabaseReference mRoot = FirebaseDatabase.getInstance().getReference();
    DatabaseReference orderRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);


        productL= new ArrayList<BasketModel>();
        listview = (ListView) findViewById(R.id.basketList);
        productL = ItemActivity.productList;
        final listviewAdapter adapter = new listviewAdapter(BasketActivty.this, productL);

        listview.setAdapter(adapter);
        productL = ItemActivity.productList;

        adapter.notifyDataSetChanged();

        orderRef = mRoot.child(CategoryActivity.restoId).child("Orders");


        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                for (int i = 0;i< MainActivity.tab.size();i++){
                    if (MainActivity.tab.get(i)){
                        orderRef.child(MainActivity.tableId).child(String.valueOf(i+1)).setValue(productL.get(i));
                    }
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        for (int i = 0;i< MainActivity.tab.size();i++){
            MainActivity.tab.set(i,false);
        }
    }
}
