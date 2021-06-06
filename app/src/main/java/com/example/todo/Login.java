package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private EditText email, password;
    // private TextView signup;
    private FirebaseAuth firebaseAuth;
    boolean isEmailValid, isPasswordValid;
     FirebaseDatabase firebaseDatabase;
//    DatabaseReference databaseReference;

    String Rid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        firebaseAuth = FirebaseAuth.getInstance();
      //  firebaseDatabase = FirebaseDatabase.getInstance();
      //  databaseReference = firebaseDatabase.getReference("todo");
      //  UserRecord userRecord = FirebaseAuth. getInstance(). getUser(uid);
     //   Rid=FirebaseAuth. getInstance(). getUser(uid);

//        Intent intent = getIntent();
//        Rid = intent.getStringExtra("reg_id");



    }

    public void logIn(View view) {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();


      //  Toast.makeText(this, ""+Rid, Toast.LENGTH_LONG).show();


        if (Email.isEmpty()) {
            email.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else {
            isEmailValid = true;
        }

        if (password.getText().toString().isEmpty()) {
            password.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            password.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else {
            isPasswordValid = true;
        }
        if (isEmailValid && isPasswordValid) {
            firebaseAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(Login.this, Homepage.class);
                     //   intent.putExtra("reg_log_id", Rid);
                        startActivity(intent);
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Login.this, "Login Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    public void signup(View view) {
        finish();
        startActivity(new Intent(Login.this, Registration.class));
    }


}