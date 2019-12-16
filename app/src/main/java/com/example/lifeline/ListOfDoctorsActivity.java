package com.example.lifeline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ListOfDoctorsActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    List<Doctor> mylist;
    RecyclerView.Adapter adapter;


//    private DrawerLayout drawer;
//
//
//    public FragmentManager fragmentManager = getSupportFragmentManager();
//    FragmentTransaction fragmentTransaction;
//    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_doctors);

//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setTitle("List Of Doctors");


//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        drawer = findViewById(R.id.drawer_layout);
//
//        NavigationView navigationView = findViewById(R.id.nev_view);
//        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
//                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new HomeFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_home);
//
//        }


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


//    Bundle bundle = getIntent().getExtras();
//    if(bundle != null) {
//        if (bundle.getString("some") != null) ;{
//
//            Toast.makeText(getApplicationContext(), ""+ (""), Toast.LENGTH_SHORT).show();


            
        }
    }











//    }
//}

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//
//        int id = item.getItemId();
//
//        if (id == R.id.nav_home) {
//            getSupportActionBar().setTitle("Home");
//            fragment = new HomeFragment();
//            fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_container, fragment);
//            fragmentTransaction.commit();
//
//        } else if (id == R.id.nav_token_status) {
//            getSupportActionBar().setTitle("Token Status");
//            fragment = new TokenStatusFragment();
//            fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_container, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//
//        } else if (id == R.id.nav_settings) {
//            getSupportActionBar().setTitle("Settings");
//            fragment = new SettingsFragment();
//            fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_container, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//
//        } else if (id == R.id.nav_logout) {
//
//            FirebaseAuth.getInstance().signOut();
//            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//            finish();
//            return true;
//        }
//            DrawerLayout drawer = findViewById(R.id.drawer_layout);
//            drawer.closeDrawer(GravityCompat.START);
//            return true;
//        }
//
//        @Override
//        public void onBackPressed () {
//            drawer = findViewById(R.id.drawer_layout);
//            if (drawer.isDrawerOpen(GravityCompat.START)) {
//                drawer.closeDrawer(GravityCompat.START);
//            } else {
//
//
//                super.onBackPressed();
//            }
//        }
//    }


