package com.example.lifeline;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;


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
    Button button;
    private DatabaseReference mDatabase;
    SharedPreferenceConfig preferenceConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_info);

        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());

//        if (preferenceConfig.readButtonStatus()){
//            Toast.makeText(this, "You can't take more than 1 appointment", Toast.LENGTH_SHORT).show();
//            finish();
//
//        }
        doc_info = findViewById(R.id.doc_info);
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
        
        Picasso.with(this).load(Docimage).into(Image);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Doctors_List");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                    String value = dataSnapshot2.child("doc_info").getValue().toString();
                    doc_info.setText(value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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

//                preferenceConfig.writebuttonStatus(true);
//                button.setEnabled(false);
//                finish();
                Toast.makeText(Doc_InfoActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }

        });
    }
}


