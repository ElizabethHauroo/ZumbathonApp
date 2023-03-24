package com.example.zumbathonapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.zumbathonapp.Adapter.AllUserAdapter;
import com.example.zumbathonapp.Model.Model;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class ShowActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    //private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //private DatabaseReference root = db.getReference().child("Users");

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference root = db.collection("Users");
    //private FirebaseDatabase db = FirebaseDatabase.getInstance();
    //private DatabaseReference root = db.getReference().child("Users");
    private AllUserAdapter adapter;
    private ArrayList<Model> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        recyclerView = findViewById(R.id.showall_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new AllUserAdapter(list, this);

        recyclerView.setAdapter(adapter);

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

    }
}