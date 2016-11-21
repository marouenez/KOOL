package com.supcom.gr39.kool;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class RatingActivity extends AppCompatActivity {


    DatabaseReference mRoot = FirebaseDatabase.getInstance().getReference();
    DatabaseReference resto1 = mRoot.child("Rests").child(CategoryActivity.restoId);
    ImageView backdrop;
    TextView restoName;
    RatingBar ratingBar;
    TextView comment ;
    Button submit;
    EditText input;
    Float lastRate = 0f;

    public void click ( View view ) {

                input = (EditText) findViewById(R.id.input_comment) ;
        Log.i("input",input.getText().toString());
        if (!input.getText().toString().isEmpty())
            resto1.child("lastComment").setValue(input.getText().toString());
                resto1.child("vote").setValue((ratingBar.getRating()+lastRate)/2f);



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

         backdrop = (ImageView) findViewById(R.id.restoImg);
        restoName = (TextView) findViewById(R.id.restoNameRate) ;
         ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        comment = (TextView) findViewById(R.id.comment);
        submit =(Button) findViewById(R.id.submit);


        resto1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Picasso.with(getApplicationContext()).load(dataSnapshot.child("imgUrl").getValue().toString()).into(backdrop);
                restoName.setText(dataSnapshot.child("name").getValue().toString());
                ratingBar.setRating(dataSnapshot.child("vote").getValue(Integer.class));
                comment.setText("{ "+dataSnapshot.child("lastComment").getValue().toString()+" }");
                lastRate = dataSnapshot.child("vote").getValue(Float.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    @Override
    protected void onPause() {
        super.onPause();

    }

}
