package com.example.lifeline;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.common.util.SharedPreferencesUtils;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Doctor> mylist;
    RecyclerView.Adapter adapter;


    private DrawerLayout drawer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();





        mylist = new ArrayList<Doctor>();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Jograj", "MBBS, MS(Eye)", "Medical Officer"));
        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Ashish", "MBBS, MD", "Physician"));
        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Praveen", "MBBS, MD", "Medicine"));
        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Rao Farman ", "MBBS, MD", "Medicine"));
        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Vibhu Sharma", "MBBS", "Institute Medical Officer"));
        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Ritu Sharma", "MBBS, DCH", "Pedistrician & Child Spe."));
        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Alok Anand", "MBBS", "Cardiology"));
        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Anant", "MBBS", ""));
        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Alok Jha", "MBBS, MD", "Physician"));
        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Raja Dey", "MBBS, MD", "Physician"));
        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Anjula Roy", "MBBS, MD", "Obs. & Gynae."));
        adapter = new Doc_Adapter(mylist, getApplicationContext());
        recyclerView.setAdapter(adapter);



    }





    @Override
        public void onBackPressed () {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                finish();

//                super.onBackPressed();
            }
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.logout: {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

//    public void logout(View view) {
//        FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//        finish();
//    }

}
