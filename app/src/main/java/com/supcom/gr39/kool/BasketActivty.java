package com.supcom.gr39.kool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class BasketActivty extends AppCompatActivity {
    ListView listview ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        listview = (ListView) findViewById(R.id.listview);

        listviewAdapter adapter = new listviewAdapter(BasketActivty.this, ItemActivity.productList);

        listview.setAdapter(adapter);

    }
}
