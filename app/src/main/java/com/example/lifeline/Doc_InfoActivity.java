package com.example.lifeline;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Doc_InfoActivity extends AppCompatActivity {
    ImageView image;
    TextView name,graduate,dpt;
    String Doctorname, Doctorgraduate, Doctordpt;
    int Docimage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_info);
        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        graduate = findViewById(R.id.graduate);
        dpt = findViewById(R.id.dpt);
        Doctorname = getIntent().getStringExtra("name");
        Doctorgraduate = getIntent().getStringExtra("graduate");
        Doctordpt = getIntent().getStringExtra("dpt");
        Docimage = getIntent().getIntExtra("image",0);
        name.setText(Doctorname);
        graduate.setText(Doctorgraduate);
        dpt.setText(Doctordpt);
        image.setImageResource(Docimage);


    }
}


