package com.supcom.gr39.kool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main2);
        final ProgressDialog progressDialog = new ProgressDialog(Main2Activity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                   /* Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();*/

                    Log.i("email", user.getEmail());

                    //Log.i("username", user.getDisplayName());
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    ArrayList<String> info = new ArrayList<>();
                    info.add(user.getEmail());
                    if (user.getDisplayName()!=null)
                    info.add(user.getDisplayName());
                    if (user.getPhotoUrl()!=null)
                    info.add(user.getPhotoUrl().toString());

                    i.putExtra("info",info);
                    startActivity(i);
                    finish();

                } else {
                    // User is signed out
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    finish();
                }
                // ...
            }
        };
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
