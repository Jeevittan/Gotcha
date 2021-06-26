package com.example.gotcha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class ForgotPassword extends AppCompatActivity {

    Button Reset,back;
    EditText Email;
    ProgressBar loading;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Email = (EditText) findViewById(R.id.resetemail);
        Reset = (Button) findViewById(R.id.reset);
        back = (Button) findViewById(R.id.backreset);
        loading = (ProgressBar)  findViewById(R.id.loading);

        mAuth = FirebaseAuth.getInstance();


        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getApplicationContext(), Login.class);
                startActivity(intent5);
            }
        });

    }

    private void resetPassword(){
        String email = Email.getText().toString();

        if(email.isEmpty()){
            Email.setError("Email is required!");
            Email.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this,"Reset Link Have Been Sent To Your Email!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(ForgotPassword.this, "Try Again Later, Email Is Not Sent!", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });
    }
}