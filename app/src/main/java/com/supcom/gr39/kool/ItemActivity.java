package com.supcom.gr39.kool;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {


public static ArrayList<BasketModel> productList = new ArrayList<>();

    private Button  btn_order ;
    public static String titleDish;
    public static String priceDish;


    public int quantity = 1;

    public void increase(View view) {
        if (quantity >= 1 && quantity < 100) {
            quantity = quantity + 1;
        }
        displayInt(quantity);
    }

    private void displayInt(int quantity) {
        TextView view = (TextView) findViewById(R.id.quantity_text_view);
        view.setText("" + quantity);
    }

    public void decrease(View view) {
        if (quantity > 1 && quantity <= 100) {
            quantity = quantity - 1;
        }
        displayInt(quantity);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


        btn_order = (Button) findViewById(R.id.btn_order) ;

        String item = getIntent().getStringExtra("item");
        int it = new Integer(item)+1;
        item = String.valueOf(it);

        Log.i("table",MainActivity.tableId);

        final TextView titleItem = (TextView) findViewById(R.id.titleItem);
        final TextView descItem = (TextView) findViewById(R.id.desc);
        final ImageView imageItem = (ImageView)  findViewById(R.id.imageItem);


        Button plus = (Button) findViewById(R.id.plus);
        Button minus = (Button) findViewById(R.id.minus);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase(v);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrease(v);
            }
        });
        DatabaseReference mRoot = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference itemRef = mRoot.child(CategoryActivity.restoId).child("Dishes").child(DishActivity.categoryName).child(item);
        final DatabaseReference orderRef = mRoot.child(CategoryActivity.restoId).child("Orders");
        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                titleItem.setText(dataSnapshot.child("name").getValue().toString());
                descItem.setText(dataSnapshot.child("description").getValue().toString());
                priceDish = dataSnapshot.child("cost").getValue().toString();
                Picasso.with(getApplicationContext()).load(dataSnapshot.child("imgUrl").getValue().toString()).into(imageItem);
                titleDish = dataSnapshot.child("name").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int priceInt = Integer.parseInt(priceDish);
                int totalPrice = quantity*priceInt;
                BasketModel order = new BasketModel(quantity, titleDish, String.valueOf(totalPrice)+" DT");

                productList.add(order);
                MainActivity.tab.add(false);

                //finish();
            }
        });

       /* btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order = new Order();
                //order.setNote(table.getText().toString());
                order.setMeal(titleDish.toString());
                order.setQuantity(quantity);
                order.setTable(MainActivity.tableId);
                orderRef.push().setValue(order);
            }
        });*/


    }
}
