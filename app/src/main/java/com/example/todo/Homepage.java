package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapter.RecyclerViewAdapter;
import model.ModelToDo;

public class Homepage extends AppCompatActivity {
    RecyclerView rvdata;
    RecyclerViewAdapter recyclerViewAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    ArrayList<ModelToDo> list;
  //  FirebaseAuth firebaseAuth;
    String usertodo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_activity);
//
//        Intent intent=getIntent();
//        reg_id=intent.getStringExtra("reg_log_id");
      //  usertodo=getIntent().getStringExtra("todoId");


        rvdata=findViewById(R.id.rv_data);
        firebaseDatabase=FirebaseDatabase.getInstance();
        //firebaseAuth=FirebaseAuth.getInstance();
      //  String DataID = reference.push().getKey();
        reference=firebaseDatabase.getReference("Registered data").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("todo");
      //  reference=firebaseDatabase.getReference("Registered data").child(reg_id).child("todo");

        list=new ArrayList<>();
        rvdata.setLayoutManager(new LinearLayoutManager(this));

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // list.clear();

                for (DataSnapshot dSnapshot :snapshot.getChildren()){
                    ModelToDo mt=dSnapshot.getValue(ModelToDo.class);
                    list.add(mt);
                }
                recyclerViewAdapter=new RecyclerViewAdapter(list, Homepage.this);
                rvdata.setAdapter(recyclerViewAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Homepage.this, "failed"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });





    }

    public void createNew(View view)
    {
        Intent intent=new Intent(Homepage.this, NewToDo.class);
        //intent.putExtra("reg_id_home",reg_id);
        startActivity(intent);
    }

    public void logOut(View view) {
//
//
        finish();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Homepage.this,Login.class));



    }
}