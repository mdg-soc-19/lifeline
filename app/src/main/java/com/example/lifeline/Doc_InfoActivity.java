package com.example.lifeline;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Doc_InfoActivity extends AppCompatActivity {
    ImageView Image;
    TextView name, graduate, dpt, doc_info;
    String Doctorname, Doctorgraduate, Doctordpt, Docimage, Docinfo;
    //    int Docimage;
    Button button;
    //    private ArrayList<Token> list;
//    private Token_Adapter adapter;
    private DatabaseReference mDatabase;

//    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_info);

//        FragmentManager manager = getSupportFragmentManager();
//        final tokenStatusFragment m4 = new tokenStatusFragment();
//        FragmentManager manager = getSupportFragmentManager();
//        final tokenStatusFragment m4 = new tokenStatusFragment();
////        manager.beginTransaction().replace(R.id.fram1234,tokenStatusFragment).commit();
//        final FragmentTransaction t = manager.beginTransaction();


//        list = new ArrayList<Token>();

//        Intent intent = getIntent();
//        textView = (TextView) findViewById(R.id.doc_info);
        Image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        graduate = findViewById(R.id.graduate);
        dpt = findViewById(R.id.dpt);
        Doctorname = getIntent().getStringExtra("name");
        Doctorgraduate = getIntent().getStringExtra("graduate");
        Doctordpt = getIntent().getStringExtra("dpt");
        Docinfo = getIntent().getStringExtra("doc_info");
        Docimage = getIntent().getStringExtra("image");
        name.setText(Doctorname);
        Log.e("jhfklsdajflk;", "dsfnhkdjsh" + Doctorname);
        graduate.setText(Doctorgraduate);
        dpt.setText(Doctordpt);
//        doc_info.setText(Docinfo);
//        textView.setText((CharSequence) doc_info);

//        mDatabase = FirebaseDatabase.getInstance().getReference().child("Doctors_List");
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    String value = dataSnapshot1.getValue(String.class);
//                    textView.setText(value);
//                }
////                textView.setText(doc_info);
////                adapter = new Doc_Adapter(Doc_InfoActivity.this, doc_info);
////                String doc_info = dataSnapshot.child("doc_info").getValue().toString();
////                textView.setText(doc_info);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        button = findViewById(R.id.bookbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatabase = FirebaseDatabase.getInstance().getReference().child("Doctors_last_reg");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            if (Doctorname.equals(dataSnapshot1.getValue(Last_token.class).getLast_Doctor_name())) {
                                Log.e("doc_name", "ahkfjhkj" + dataSnapshot1.getValue(Last_token.class).getLast_Doctor_name());
                                Log.e("doc_token", "ahkfjhkj" + dataSnapshot1.getValue(Last_token.class).getLast_Token_No());
                                String name = dataSnapshot1.getValue(Last_token.class).getLast_Doctor_name();
                                int Tok = dataSnapshot1.getValue(Last_token.class).getLast_Token_No() + 1;
                                Log.e("a", "a" + Tok);
                                Intent transfer = new Intent(Doc_InfoActivity.this, tokenStatusFragment.class);
                                transfer.putExtra("name", dataSnapshot1.getValue(Last_token.class).getLast_Doctor_name());
                                transfer.putExtra("token", dataSnapshot1.getValue(Last_token.class).getLast_Token_No());
                                startActivity(transfer);

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}


