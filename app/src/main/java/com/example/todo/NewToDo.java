package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.ModelToDo;

public class NewToDo extends AppCompatActivity {
    //  Long selectedDate;
    EditText title, description;
    Button create_todo;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String date;
    FirebaseAuth firebaseAuth;
    //  Boolean isDatevalid;
    //   String register_id;
    FirebaseUser user;
    String Rid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);

//        Intent intent=getIntent();
//        register_id=intent.getStringExtra("reg_id_home");

        title = findViewById(R.id.title_id);
        description = findViewById(R.id.description_id);
        create_todo = findViewById(R.id.btn_newtodo_create);
        firebaseDatabase = FirebaseDatabase.getInstance();

        //  databaseReference = firebaseDatabase.getReference("todo");
        databaseReference = firebaseDatabase.getReference("Registered data").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("todo");
        //   Log.d("Rid", String.valueOf(reference));


        CalendarView calendarView = findViewById(R.id.calendarView);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                if (!view.isSelected()) {
                    String curDate = String.valueOf(dayOfMonth);
                    String Year = String.valueOf(year);
                    String Month = String.valueOf(month);

                    date = curDate + "/" + Month + "/" + Year;
                } else {
                    Toast.makeText(NewToDo.this, "select date", Toast.LENGTH_SHORT).show();
                }

            }
        });


        create_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Title = title.getText().toString().trim();
                String Description = description.getText().toString().trim();
                final String ID = databaseReference.push().getKey();

                if (date != null) {
                    ModelToDo model_todo = new ModelToDo(Title, Description, date, ID);

                    databaseReference.child(ID).setValue(model_todo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(NewToDo.this, "new todo created", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(NewToDo.this, Homepage.class));
                            Intent intent = new Intent(NewToDo.this, Homepage.class);
                            //     intent.putExtra("todoId",ID);
                            startActivity(intent);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(NewToDo.this, "Can't create task..." + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(NewToDo.this, "Please select date", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}