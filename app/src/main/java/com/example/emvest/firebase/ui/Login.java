package com.example.emvest.firebase.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.emvest.firebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.dmoral.toasty.Toasty;

public class Login extends AppCompatActivity {
    EditText mEmail,mPass;
    private FirebaseAuth mAuth;
    private static final String TAG = "Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //firebase instance
        mAuth = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.login_email);
        mPass  = findViewById(R.id.login_password);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_trans();
            }
        });

        findViewById(R.id.create_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }
    private void login_trans() {

        String email    = mPass.getText().toString().trim();
        String password = mEmail.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");

                            FirebaseUser user = mAuth.getCurrentUser();

                            Toasty.success(Login.this,"Login in was ",Toasty.LENGTH_LONG,true).show();
                        } else {

                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());

                            Toasty.error(Login.this,"Authentication failed.",Toasty.LENGTH_LONG,true).show();
                        }

                    }
                });
    }
}
