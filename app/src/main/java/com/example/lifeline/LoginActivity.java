package com.example.lifeline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.text.TextUtils;
import android.util.Patterns;
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

public class LoginActivity extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mLoginBtn = findViewById(R.id.LoginBtn);
        mCreateBtn = findViewById(R.id.createText);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);





        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
               final String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
//                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
//                    return;

                    mEmail.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
//                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
//                    return;
                    mPassword.setError("Password is required");
                    return;
                }
                if(password.length() <6) {
                    mPassword.setError("Password must be >= 6 characters");
                    return;
                }

//                if(password.length() <6) {
//                    mPassword.setError("Password must be >= 6 characters");
//                    return;
//                }
                progressBar.setVisibility(View.VISIBLE);

                // Authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            mLoginBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                }
                            });

                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });


            }
        });

//        mLoginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)  {
//                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
//
//            }
//        });
        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));

            }
        });
//        mCreateBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
//            }
//
//        });


    }
}
