package com.example.lifeline;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class tokenStatusFragment extends AppCompatActivity {


    private TextView doctor, token;

    private DatabaseReference mDatabase;
    private CardView cardView;
    private RecyclerView recyclerView;
    private ArrayList<Token> list;
    private Token_Adapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_token_status2);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        doctor =  findViewById(R.id.mydoctor);
        token =  findViewById(R.id.mytoken);


        String Last_Doctor_name = getIntent().getStringExtra("name");
        Log.e("name", "123" + Last_Doctor_name);
        int Last_Token_no = getIntent().getIntExtra("token", 0);
        Log.e("token", "123" + Last_Token_no);

        doctor.setText("" + Last_Doctor_name);

        token.setText("" + ((Last_Token_no)+1));




        recyclerView = findViewById(R.id.token_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));


        list = new ArrayList<Token>();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Doctors_Status");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Token u = dataSnapshot1.getValue(Token.class);
                    list.add(u);
                }

                adapter = new Token_Adapter(tokenStatusFragment.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(tokenStatusFragment.this, "opss.. Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
