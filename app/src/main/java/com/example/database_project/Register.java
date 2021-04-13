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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
EditText email,password,class_a;
Button signUp;
FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseFirestore=FirebaseFirestore.getInstance();
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signUp=findViewById(R.id.signUp);
        class_a =findViewById(R.id.a);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String email_sign,password_sign,class_sign;
               email_sign=email.getText().toString().trim();
               password_sign=password.getText().toString();
               class_sign=class_a.getText().toString().trim();
               if (TextUtils.isEmpty(email_sign)||TextUtils.isEmpty(password_sign)||TextUtils.isEmpty(class_sign)) {
                   signUp.setError("Some Fields are empty");
                   return;
               }
               else {
               user_data user_data=new user_data(email_sign,password_sign,class_sign);
               firebaseFirestore.collection("All_Account")
                       .document(email_sign)
                       .set(user_data)
                       .addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if (task.isSuccessful()) {
                                   Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();
                                   startActivity(new Intent(getApplicationContext(),Home_Activity.class));
                               }
                           }
                       }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(Register.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });


               }
            }
        });

    }

    private void data_stored(String email_sign, String password_sign) {


    }
}