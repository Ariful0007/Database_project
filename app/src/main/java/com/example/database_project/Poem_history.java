package com.example.database_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class Poem_history extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    RecyclerView recyclerView;
    PoemAdapter getDataAdapter1;
    List<Poem_model> getList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_history);
        getList=new ArrayList<>();
        getDataAdapter1=new PoemAdapter(getList);
        firebaseFirestore=FirebaseFirestore.getInstance();
        documentReference=firebaseFirestore.collection("poem").document();
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(getDataAdapter1);
        reciveData();
    }
    private void reciveData() {
        firebaseFirestore.collection("poem").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange ds:queryDocumentSnapshots.getDocumentChanges()) {
                    if (ds.getType()== DocumentChange.Type.ADDED) {
                 /*String first;
                 first = ds.getDocument().getString("name");
                 Toast.makeText(MainActivity2.this, "" + first, Toast.LENGTH_SHORT).show();*/
                        Poem_model get=ds.getDocument().toObject(Poem_model.class);
                        getList.add(get);
                        getDataAdapter1.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}