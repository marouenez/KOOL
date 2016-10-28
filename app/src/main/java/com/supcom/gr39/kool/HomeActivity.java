package com.supcom.gr39.kool;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public void signupClick(View view){
        Intent i = new Intent(getApplicationContext(),SignupActivity.class);
        startActivity(i);

    }
    public void loginClick (View view){

        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.i("email", user.getEmail());


                }
                // ...
            }
        };
        setContentView(R.layout.activity_home);

        Intent i = getIntent();

        int flag =i.getIntExtra("flag",0);
        Log.i("flag",String.valueOf(flag));
        if (flag ==1){
            Log.i("g",String.valueOf(flag));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

}
