package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Splash extends AppCompatActivity {

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuth.getInstance();

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4000);

                } catch (Exception e) {

                } finally {

//                    startActivity(new Intent(Splash.this, Login.class));
//                    finish();
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    if (firebaseUser != null) {

                        Intent intent=new Intent(Splash.this,Homepage.class);
                        startActivity(intent);
                        finish();

                    } else {
                        startActivity(new Intent(Splash.this, Login.class));
                        finish();


                    }


                }
            }//;
        };
        splashTread.start();

    }



}