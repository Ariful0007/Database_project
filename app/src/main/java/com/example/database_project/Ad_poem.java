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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Ad_poem extends AppCompatActivity {
    EditText poem_name,poet_name,poem;
    Button regis;
    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_poem);
        firebaseFirestore=FirebaseFirestore.getInstance();
        poem_name=findViewById(R.id.poem_name);
        poet_name=findViewById(R.id.poet_name);
        poem=findViewById(R.id.poem);
        regis=findViewById(R.id.regis);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String poemname,poetname,mainpoem;
                poemname=poem_name.getText().toString();
                poetname=poet_name.getText().toString();
                mainpoem=poem.getText().toString();
                if (TextUtils.isEmpty(poemname)||TextUtils.isEmpty(poetname)||TextUtils.isEmpty(mainpoem)) {
                    regis.setError("Some Fields are empty");
                    return;
                }
                else {
                    Poem_model poem_model=new Poem_model(poemname,poetname,mainpoem);
                    firebaseFirestore.collection("poem").document(poemname)
                            .set(poem_model)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),Poem_history.class));
                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Ad_poem.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });


                }
            }
        });
    }
}