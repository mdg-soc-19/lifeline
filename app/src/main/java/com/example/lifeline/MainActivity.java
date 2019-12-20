package com.example.lifeline;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.util.SharedPreferencesUtils;
import com.google.android.material.circularreveal.CircularRevealRelativeLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    RecyclerView recyclerView;
//    List<Doctor> mylist;
//    RecyclerView.Adapter adapter;

//       private Button button;

    private long backPressedTime;
    private Toast backToast;






    private DrawerLayout drawer;


    public FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;
    Fragment fragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        button = (Button) findViewById(R.id.listbtn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),ListOfDoctorsActivity.class));
//
//            }
//        });






        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nev_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_home);

        }





//        mylist = new ArrayList<Doctor>();
//        recyclerView = findViewById(R.id.recycler);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Jograj", "MBBS, MS(Eye)", "Medical Officer"));
//        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Ashish", "MBBS, MD", "Physician"));
//        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Praveen", "MBBS, MD", "Medicine"));
//        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Rao Farman ", "MBBS, MD", "Medicine"));
//        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Vibhu Sharma", "MBBS", "Institute Medical Officer"));
//        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Ritu Sharma", "MBBS, DCH", "Pedistrician & Child Spe."));
//        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Alok Anand", "MBBS", "Cardiology"));
//        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Anant", "MBBS", ""));
//        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Alok Jha", "MBBS, MD", "Physician"));
//        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Raja Dey", "MBBS, MD", "Physician"));
//        mylist.add(new Doctor(R.drawable.ic_account, "Dr. Anjula Roy", "MBBS, MD", "Obs. & Gynae."));
//        adapter = new Doc_Adapter(mylist, getApplicationContext());
//        recyclerView.setAdapter(adapter);



    }

//    public void openListOfDoctorsActivity(){
//
//        Intent intent = new Intent(this, ListOfDoctorsActivity.class);
//        startActivity(intent);
//    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportActionBar().setTitle("LifeLine");
            getSupportActionBar().setHomeButtonEnabled(true);
            fragment = new HomeFragment();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        }
        else if (id == R.id.nav_token_status) {
            getSupportActionBar().setTitle("Token Status");
            getSupportActionBar().setHomeButtonEnabled(true);
            fragment = new tokenStatusFragment();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }else if (id == R.id.nav_settings) {
            getSupportActionBar().setTitle("Settings");
            getSupportActionBar().setHomeButtonEnabled(true);
            fragment = new SettingsFragment();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
        else if (id == R.id.nav_logout) {

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
            return true;

//            fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_container, fragment);
//            fragmentTransaction.commit();
////            getSupportActionBar().hide();
//            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
//            if (fragment !=null) {
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, fragment);
//                fragmentTransaction.commit();
//            }

//        switch (menuItem.getItemId()){
//            case R.id.nev_home:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new HomeFragment()).commit();
//
//                break;
//
//            case R.id.nev_token_status:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new TokenStatusFragment()).commit();
//
//                break;
//
//            case R.id.nev_settings:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new SettingsFragment()).commit();
//
//                break;
//
//            case R.id.nev_logout:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new LogoutFragment()).commit();
//
//                break;




    DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
        public void onBackPressed () {
        drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);

            } else {


                super.onBackPressed();
            }
        }




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu,menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//
//            case R.id.logout: {
//
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                finish();
//                return true;
//            }
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    public void logout(View view) {
//        FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//        finish();
//    }

}
