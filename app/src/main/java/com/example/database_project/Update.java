package com.example.database_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Update extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText email,password;
    Button login;
    FirebaseFirestore firebaseFirestore;
    String[] menu={"username","password","class_sign"};
    Spinner spinner;
    String valueFromSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        firebaseFirestore=FirebaseFirestore.getInstance();
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        spinner=findViewById(R.id.spinnerTextSize1111);
        spinner.setOnItemSelectedListener(this);

        String[] textSizes = getResources().getStringArray(R.array.font_sizes);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username=email.getText().toString().trim();
                final String updating=password.getText().toString().trim();
                AlertDialog.Builder builder=new AlertDialog.Builder(Update.this);
                builder.setTitle("Select One");
                builder.setItems(menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if (which==0) {
                            firebaseFirestore.collection("All_Account")
                                    .document(username)
                                    .update("username",updating)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Update.this, "Updated", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Update.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else if(which==1) {
                            firebaseFirestore.collection("All_Account")
                                    .document(username)
                                    .update("password",updating)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Update.this, "Updated", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Update.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else if (which==2){
                            firebaseFirestore.collection("All_Account")
                                    .document(username)
                                    .update("class_sign",updating)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Update.this, "Updated", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Update.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }



                    }
                });
builder.create().show();

            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinnerTextSize1111) {
            valueFromSpinner = parent.getItemAtPosition(position).toString();
            Toast.makeText(this, ""+valueFromSpinner, Toast.LENGTH_SHORT).show();
            //txtHelloWorld.setTextSize(Float.parseFloat(valueFromSpinner));
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}