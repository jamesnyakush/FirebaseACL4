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

public class Register extends AppCompatActivity {
    EditText mUser,mEmail,mPhone,mPass;
    private FirebaseAuth mAuth;
    private static final String TAG = "Register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        mAuth = FirebaseAuth.getInstance();

        mUser  = findViewById(R.id.register_username);
        mEmail = findViewById(R.id.register_email);
        mPhone = findViewById(R.id.register_phone);
        mPass  = findViewById(R.id.register_password);

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_trans();
            }
        });
        findViewById(R.id.login_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
    }

    private void register_trans() {
        String username = mUser.getText().toString().trim();
        String email    = mEmail.getText().toString().trim();
        String phone    = mPhone.getText().toString().trim();
        String password = mPass.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(username,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");

                            FirebaseUser user = mAuth.getCurrentUser();

                            Toasty.success(Register.this,"Login in was ",Toasty.LENGTH_LONG,true).show();
                        } else {

                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());

                            Toasty.error(Register.this,"Authentication failed.",Toasty.LENGTH_LONG,true).show();
                        }
                    }
                });

    }
}
