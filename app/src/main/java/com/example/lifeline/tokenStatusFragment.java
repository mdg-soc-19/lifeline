package com.example.lifeline;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class tokenStatusFragment extends Fragment {


//    private TextView Doctor, token;

    private DatabaseReference mDatabase;
    private CardView cardView;
    private RecyclerView recyclerView;
    private ArrayList<Token> list;
    private Token_Adapter adapter;


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_token_status2, container, false);


//        Doctor = (TextView) v.findViewById(R.id.token);
//        token = (TextView) v.findViewById(R.id.token);



//        cardView = (CardView) v.findViewById(R.id.current_token_cardview);
//        cardView.setAdapter(adapter);


        recyclerView = (RecyclerView) v.findViewById(R.id.token_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        list = new ArrayList<Token>();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Doctors");

//        Query query = FirebaseDatabase.getInstance().getReference().child("Doctors")
//                .orderByChild("Doctor_name")
//                .equalTo("Doctor_name");
//        query.addListenerForSingleValueEvent(ValueEventListener);


//        ValueEventListener valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        }
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
                Toast.makeText(getActivity(), "opss.. Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });


        return v;
    }
}
