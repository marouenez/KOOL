package com.supcom.gr39.kool;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.design.internal.ForegroundLinearLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BasketActivty extends AppCompatActivity {
    ListView listview ;
    SparseBooleanArray sparseBooleanArray ;
    private ArrayList<BasketModel> productL;

    DatabaseReference mRoot = FirebaseDatabase.getInstance().getReference();
    DatabaseReference orderRef;
    NotificationManager mNotificationManager;
    FloatingActionButton myFab;
    ArrayList<Boolean> tab = MainActivity.tab;
    String tableId = MainActivity.tableId;
    String restoId = CategoryActivity.restoId;
    final String[] name = new String[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        mNotificationManager=(NotificationManager) this.getApplicationContext().getSystemService(NOTIFICATION_SERVICE);


        productL= new ArrayList<BasketModel>();
        listview = (ListView) findViewById(R.id.basketList);
        productL = ItemActivity.productList;
        final listviewAdapter adapter = new listviewAdapter(BasketActivty.this, productL);

        listview.setAdapter(adapter);
        productL = ItemActivity.productList;

        adapter.notifyDataSetChanged();

        orderRef = mRoot.child("Rests").child(restoId).child("Orders");
        mRoot.child("Rests").child(restoId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name[0] = dataSnapshot.child("name").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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

    @Override
    protected void onResume() {
        super.onResume();


        myFab= (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        if (productL.size() > 0) {


            myFab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    AlertDialog.Builder builder1 = new AlertDialog.Builder(BasketActivty.this);

                    builder1.setTitle("Order");
                    builder1.setMessage("Would you like to send the order ?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    for (int i = 0; i < tab.size(); i++) {
                                        if (tab.get(i)) {
                                            orderRef.child(tableId).push().setValue(productL.get(i));
                                        }
                                    }

                                    for (int i = 0; i < tab.size(); i++) {
                                        tab.set(i, false);
                                    }
                                    ItemActivity.productList.clear();

                                    final Intent resultIntent = new Intent(BasketActivty.this, RatingActivity.class);
                                    final Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Creating a artifical activity stack for the notification activity
                                            TaskStackBuilder stackBuilder = TaskStackBuilder.create(BasketActivty.this);
                                            stackBuilder.addParentStack(DishActivity.class);
                                            stackBuilder.addNextIntent(resultIntent);

                                            // Pending intent to the notification manager
                                            PendingIntent resultPending = stackBuilder
                                                    .getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
                                            Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


                                            // Building the notification
                                            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(BasketActivty.this)
                                                    .setSmallIcon(R.drawable.logo) // notification icon
                                                    .setContentTitle("Rating") // main title of the notification
                                                    .setContentText("Please rate " + name[0]) // notification text
                                                    .setContentIntent(resultPending)
                                                    .setAutoCancel(true)
                                                    .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                                                    .setLights(Color.RED, 3000, 3000)
                                                    .setSound(uri); // notification intent

                                            // mId allows you to update the notification later on.
                                            mNotificationManager.notify(10, mBuilder.build());
                                        }
                                    }, 5000);

                                    finish();
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();


                    Log.i("tab", MainActivity.tab.toString());


                }
            });
        }
        else {

            myFab.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
