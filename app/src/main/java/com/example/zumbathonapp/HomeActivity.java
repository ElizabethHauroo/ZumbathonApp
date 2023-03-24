package com.example.zumbathonapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zumbathonapp.Adapter.AllUserAdapter;
import com.example.zumbathonapp.Model.Model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    Button logoutBtn, showBTN;
  //  RecyclerView recyclerView;
  //  DatabaseReference database;
   // MyAdapter myAdapter;
   // ArrayList<UserAdapter> list;
    private RecyclerView recyclerView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference root = db.collection("Users");
    private AllUserAdapter adapter;
    private ArrayList<Model> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //recyclerView = findViewById(R.id.recycleView);
        //database = FirebaseDatabase.getInstance().getReference("Users");
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new AllUserAdapter(list, this);
        recyclerView.setAdapter(adapter);

       logoutBtn = findViewById(R.id.home_LogoutBTN);
       showBTN = findViewById(R.id.home_showBTN);



       logoutBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               FirebaseAuth.getInstance().signOut();
               startActivity(new Intent(getApplicationContext(), MainActivity.class));
               finish();
           }
       });

       showBTN.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(HomeActivity.this, ShowActivity.class));
           }
       });

        root.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot document : snapshot.getDocuments()){
                    Map<String, Object> data = document.getData();
                    String FullName = (String) data.get("FullName");
                    String account = (String) data.get("Account");

                    Model model = new Model(FullName, account);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }
        });
/*
       database.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                   UserAdapter user = dataSnapshot.getValue(UserAdapter.class);
                   list.add(user);
               }
               myAdapter.notifyDataSetChanged();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       }); */



    }
}