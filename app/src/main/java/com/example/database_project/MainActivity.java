package com.example.database_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button login,regis;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseFirestore=FirebaseFirestore.getInstance();
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_sign,password_sign;
                email_sign=email.getText().toString().trim();
                password_sign=password.getText().toString();
                if (TextUtils.isEmpty(email_sign)||TextUtils.isEmpty(password_sign)) {
                    login.setError("Some Fields are empty");
                    return;
                }
                else {
                   firebaseFirestore.collection("All_Account")
                           .document(email_sign)
                           .get()
                           .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                               @Override
                               public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                   if (task.isSuccessful()) {
                                       if (task.getResult().exists()) {
                                           Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
                                           startActivity(new Intent(getApplicationContext(),Home_Activity.class));
                                       }
                                       else {
                                           Toast.makeText(MainActivity.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               }
                           }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   });

                }
            }
        });
        regis=findViewById(R.id.regis);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }
}