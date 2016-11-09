package com.supcom.gr39.kool;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;


public class DishActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    public static String categoryName;
    DatabaseReference mRoot = FirebaseDatabase.getInstance().getReference();

    /*public void popupmenu (View view){


        PopupMenu popup = new PopupMenu(DishActivity.this, view);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.menu_album, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(
                        DishActivity.this,
                        "You Clicked : " + item.getTitle(),
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            }
        });

        popup.show();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        categoryName = getIntent().getStringExtra("categoryName");

        mAdapter = new DishesAdapter(getApplicationContext(),getDataSet());
        mRecyclerView.setAdapter(mAdapter);



        final ImageView backdrop2 = (ImageView) findViewById(R.id.backdrop2);
        final TextView restoName = (TextView) findViewById(R.id.love_music2) ;
        DatabaseReference resto1 = mRoot.child(CategoryActivity.restoId).child("Dishes").child(DishActivity.categoryName);
        resto1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Picasso.with(getApplicationContext()).load(dataSnapshot.child("imgUrl").getValue().toString()).into(backdrop2);
                restoName.setText(categoryName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BasketActivty.class));
            }
        });





        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ((DishesAdapter) mAdapter).setOnItemClickListener(new DishesAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);

                Intent i = new Intent(getApplicationContext(),ItemActivity.class);
                i.putExtra("item",String.valueOf(position));
                startActivity(i);

            }
        });

        /*final ImageView overflow = (ImageView) findViewById(R.id.overflow);

        overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(DishActivity.this, overflow);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.menu_album, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                DishActivity.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });*/
    }

    private ArrayList<Dish> getDataSet() {
        final ArrayList results = new ArrayList<Dish>();
        DatabaseReference resto = mRoot.child(CategoryActivity.restoId).child("Dishes").child(categoryName);
        resto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot mekla : dataSnapshot.getChildren()){
                    String s = mekla.getKey();

                    if (s.charAt(0)<'9' && s.charAt(0)> '0'){
                        String meklaName = mekla.child("name").getValue().toString();
                        String meklaUrl = mekla.child("imgUrl").getValue().toString();
                        String meklaCost = mekla.child("cost").getValue().toString();
                        Dish a = new Dish(meklaName, meklaCost, meklaUrl);
                        results.add(a);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return results;
    }
}