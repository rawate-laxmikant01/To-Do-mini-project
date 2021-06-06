package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Thread.sleep;

public class Detail extends AppCompatActivity {

    TextView date, title, description;
    EditText up_date, up_title, up_description;
    String id, D_data, D_titile, D_Description;
    String newTitle, newDate, newDescription;
    Button btn_update;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseRef = database.getReference("Registered data").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("todo");
   // DatabaseReference mDatabaseRef;
 //   Button btn_update;


//    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        btn_update=findViewById(R.id.btn_update_task);
        date = findViewById(R.id.detail_date_id);
        title = findViewById(R.id.detail_title_id);
        description = findViewById(R.id.detail_description_id);
        up_date = findViewById(R.id.edittext_detail_date_id);
        up_title = findViewById(R.id.edittext_detail_title_id);
        btn_update=findViewById(R.id.btn_update_task);
        up_description=findViewById(R.id.edittext_detail_description_id);
        showAllUserDate();



    }

    private void showAllUserDate() {

        Intent intent = getIntent();
        D_data = intent.getStringExtra("date_detail");
        D_titile = intent.getStringExtra("title_detail");
        D_Description = intent.getStringExtra("description");
        id = intent.getStringExtra("id");


        date.setText(D_data);
        title.setText(D_titile);
        description.setText(D_Description);

        up_date.setText(D_data);
        up_title.setText(D_titile);
        up_description.setText(D_Description);




    }
//
    public void update(View view) {
        date.setVisibility(TextView.INVISIBLE);
        title.setVisibility(TextView.INVISIBLE);
        description.setVisibility(TextView.INVISIBLE);
        up_date.setVisibility(EditText.VISIBLE);
        up_title.setVisibility(EditText.VISIBLE);
        up_description.setVisibility(EditText.VISIBLE);


        newTitle = up_title.getText().toString().trim();
        newDate = up_date.getText().toString().trim();
        newDescription=up_description.getText().toString();

//        if(isDateChanged()){
//           // Toast.makeText(this, "Date is changed", Toast.LENGTH_SHORT).show();
//            date.setText(newDate);
//
//        }
//        if(isTitleChanged()){
//          //  Toast.makeText(this, "Title is changed", Toast.LENGTH_SHORT).show();
//            title.setText(newTitle);
//        }
//        if(isDescriptionChanged()){
//          //  Toast.makeText(this, "Description is changed", Toast.LENGTH_SHORT).show();
//            description.setText(newDescription);
//        }
        if (isDateChanged() || isTitleChanged() || isDescriptionChanged()) {


            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Detail.this, "Data is updated..", Toast.LENGTH_SHORT).show();
                }
            });


        }
        else {

            Toast.makeText(Detail.this, "Data is same and unable to update..", Toast.LENGTH_SHORT).show();

        }


    }


    private boolean isDateChanged() {
        if (!D_data.equals(newDate)) {
            mDatabaseRef.child(id).child("date").setValue(newDate);
            date.setText(newDate);
            return true;
        } else {
            return false;
        }
    }

    private boolean isTitleChanged() {
        if(!D_titile.equals(up_title.getText().toString().trim())){
            mDatabaseRef.child(id).child("title").setValue(newTitle);
            title.setText(newTitle);
            return true;
        }
        else{
            return false;
        }
    }


    private boolean isDescriptionChanged() {
        if(!D_Description.equals(up_description.getText().toString().trim())){
            mDatabaseRef.child(id).child("description").setValue(newDescription);
            description.setText(newDescription);
            return true;
        }
        else{
            return false;
        }
    }


    public void deleteDate(View view) {
        mDatabaseRef.child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Detail.this, "Task deleted...", Toast.LENGTH_SHORT).show();
                Intent intent=(new Intent(Detail.this, Homepage.class));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Detail.this, "Unable to delete Task.."+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}