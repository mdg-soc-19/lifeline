package com.example.lifeline;

import android.content.Intent;
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
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class tokenStatusFragment extends AppCompatActivity {


    private TextView doctor, token;
//    private String ID;

    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;
    private ArrayList<Token> list;
    private Token_Adapter adapter;
    FirebaseAuth fAuth;
    FirebaseUser current_user;
    private DatabaseReference fDatabase , pDatabase ,dDatabase;
    ProgressBar progressBar;
    int tok , Status_token;
    String doc;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_token_status2);

        fAuth = FirebaseAuth.getInstance();
        current_user = fAuth.getCurrentUser();
        final String current = current_user.getEmail();


        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        doctor = findViewById(R.id.mydoctor);
        token = findViewById(R.id.mytoken);
        progressBar = findViewById(R.id.progressBar5);


        fDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_user.getUid());
        fDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                doc = (dataSnapshot.getValue(User.class).getMy_doctor());
                Log.e("dhjj", "pappu" + doc);
                tok = dataSnapshot.getValue(User.class).getMy_token();
                Log.e("dhjj", "pappu" + tok);

                doctor.setText(doc);
                token.setText(Integer.toString(tok));
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        pDatabase = FirebaseDatabase.getInstance().getReference().child("Doctors_Status");
//        pDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
//                    if(doc.equals(dataSnapshot1.getValue(Token.class).getDoctor_name().toString())){
//                        Status_token = (dataSnapshot1.getValue(Token.class).getToken_No());
//                        Log.e("","123456"+Status_token);
//
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


//            Log.e("",""+"if is done");
////            dDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_user.getUid());
////            dDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
////                @Override
////                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                    if (Status_token > tok) {
////                        dDatabase.child("my_doctor").setValue("Doctor...");
////                        dDatabase.child("my_token").setValue(0);
////                    }
////                }
////                @Override
////                public void onCancelled(@NonNull DatabaseError databaseError) {
////
////                }
////            });




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
