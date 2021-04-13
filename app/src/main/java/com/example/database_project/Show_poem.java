package com.example.database_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Show_poem extends AppCompatActivity {
    String poem_name,poet_name,poem;
    TextView poem_name1,poet_name1,poem1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_poem);
        //getIntent()
        poem_name=getIntent().getStringExtra("poem_name");
        poet_name=getIntent().getStringExtra("poet_name");
        poem=getIntent().getStringExtra("poem");
        poem_name1=findViewById(R.id.poem_name);
        poet_name1=findViewById(R.id.poet_name);
        poem1=findViewById(R.id.poem);
        poem_name1.setText(poem_name);
        poet_name1.setText(poet_name);
        poem1.setText(poem);
    }
}