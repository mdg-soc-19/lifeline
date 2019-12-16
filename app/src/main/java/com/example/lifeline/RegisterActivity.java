package com.example.lifeline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.jar.Attributes;

public class RegisterActivity extends AppCompatActivity {
    private EditText mName;
    private EditText mEnrollment;
    private EditText mEmail;
    private EditText mPassword;
    private Button mRegisterBtn;
    private TextView mLoginBtn;
    private FirebaseAuth fAuth;
    private ProgressBar progressBar;


    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName = findViewById(R.id.Name);
        mEnrollment = findViewById(R.id.Enrollment_no);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mRegisterBtn = findViewById(R.id.RegisterBtn);
        mLoginBtn = findViewById(R.id.LoginBtn);

        fAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        progressBar = findViewById(R.id.progressBar3);
        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();


        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = mName.getText().toString().trim();
                final String enrollment = mEnrollment.getText().toString().trim();
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    return;
                }
                if(password.length() <6) {
                    mPassword.setError("Password must be >= 6 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);


                //Register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            User user = new User(
                                    name,
                                    email,
                                    enrollment

                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                    }
                                }
                            });


                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });




    }
}








//package com.example.lifeline;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.jar.Attributes;
//
//public class RegisterActivity extends AppCompatActivity {
//    private EditText mName;
//    private EditText mEnrollment;
//    private EditText mEmail;
//    private EditText mPassword;
//    private Button mRegisterBtn;
//    private TextView mLoginBtn;
//    private FirebaseAuth fAuth;
//    private ProgressBar progressBar;
//
//    private DatabaseReference mDatabase;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        mName = findViewById(R.id.Name);
//        mEnrollment = findViewById(R.id.Enrollment_no);
//        mEmail = findViewById(R.id.Email);
//        mPassword = findViewById(R.id.Password);
//        mRegisterBtn = findViewById(R.id.RegisterBtn);
//        mLoginBtn = findViewById(R.id.LoginBtn);
//
//        fAuth = FirebaseAuth.getInstance();
//
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
//
//        progressBar = findViewById(R.id.progressBar3);
//        if(fAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//
//
//        }
//
//        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 final String name = mName.getText().toString().trim();
//                 final String enrollment = mEnrollment.getText().toString().trim();
//                 final String email = mEmail.getText().toString().trim();
//                 String password = mPassword.getText().toString().trim();
//
//                if(TextUtils.isEmpty(email)){
//                    mEmail.setError("Email is required");
//                    return;
//                }
//                if(TextUtils.isEmpty(password)){
//                    mPassword.setError("Password is required");
//                    return;
//                }
//                if(password.length() <6) {
//                    mPassword.setError("Password must be >= 6 characters");
//                    return;
//                }
//                progressBar.setVisibility(View.VISIBLE);
//
//
//            //Register the user in firebase
//                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//
//                            User user = new User(
//                                    name,
//                                    email,
//                                    enrollment
//
//                                    );
//
//                            FirebaseDatabase.getInstance().getReference("Users")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()){
//                                        Toast.makeText(RegisterActivity.this, "", Toast.LENGTH_SHORT).show();.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
//                                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//                                    }
//                                }
//                            });
//
//
////                            String user_id = fAuth.getCurrentUser().getUid();
////                            DatabaseReference current_user_db = mDatabase.child(user_id);
////                            current_user_db.child("name").setValue(name);
////                            current_user_db.child("enrollment_no.").setValue(enrollment_no);
//
//
//
//                        }
//                        else{
//                            Toast.makeText(RegisterActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
//                        }
//                    }
//                });
//
//
//
//
//
//
//
//            }
//        });
//
//
//
//
//    }
//}
