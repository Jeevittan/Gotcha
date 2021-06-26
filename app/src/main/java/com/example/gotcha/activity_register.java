package com.example.gotcha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class activity_register extends AppCompatActivity {
    EditText emailId,Password,RPassword,PhoneNo;
    Button Register;
    ProgressBar Loading;
    private FirebaseAuth mAuth;
    private Button back;

    FirebaseFirestore fStore;
    //FirebaseDatabase rootNode;
    //DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);


        mAuth = FirebaseAuth.getInstance();
        //fStore = FirebaseFirestore.getInstance();
        emailId = findViewById(R.id.emailAd);
        Password = findViewById(R.id.Regpassword);
        RPassword = findViewById(R.id.Regpassword2);
        PhoneNo = findViewById(R.id.hpno);
        Register = findViewById(R.id.register);
        Loading = findViewById(R.id.loadingreg);
        Loading.setVisibility(View.GONE);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rootNode = FirebaseDatabase.getInstance();
                //reference = rootNode.getReference("Phoneno");
                String Phone = "+6" + PhoneNo.getText().toString();
                String Email = emailId.getText().toString();
                String email = emailId.getText().toString();
                String pwd = Password.getText().toString();
                String Rpwd = RPassword.getText().toString();
                String Hp = PhoneNo.getText().toString();
                String checkPassword = "^" +

                   "(?=.*[@#$%^&+=_])" +     // at least 1 special character

                        "(?=.*[0-9])" +     // at least 1 numeric character

                        "(?=.*[a-z])" +     // at least 1 alphabet character

                        "(?=.*[A-Z])" +     // at least 1 Alphabet character

                   "(?=\\S+$)" +                     // no white spaces

                   ".{8,}" +                              // at least 4 characters

                   "$";

                String checkspaces = "[0][0-9]{9}";

                String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
                if(email.isEmpty()) {
                    emailId.setError("Please enter email address");
                    emailId.requestFocus();
                }

                else if (!email.matches(checkEmail)) {
                    emailId.setError("Invalid Email!");
                    emailId.requestFocus();
                }

                else if(pwd.isEmpty()){
                    Password.setError("Please enter a password");
                    Password.requestFocus();
                }

                else if (!pwd.matches(checkPassword)) {
                    Password.setError("Password does not fullfill criteria");
                    Password.requestFocus();
                }

                else if(Rpwd.isEmpty()){
                    RPassword.setError("Please re-enter your password");
                    RPassword.requestFocus();
                }

                else if(!Rpwd.equals(pwd)){
                    RPassword.setError("Password does not match!");
                    RPassword.requestFocus();
                }

                else if(Hp.isEmpty()){
                    PhoneNo.setError("Please enter a phone number");
                    PhoneNo.requestFocus();
                }

                else if (!Hp.matches(checkspaces)) {
                    PhoneNo.setError("No White spaces are allowed!");
                    PhoneNo.requestFocus();
                }

                else if(email.isEmpty() && pwd.isEmpty() && Rpwd.isEmpty() && Hp.isEmpty()){
                    Toast.makeText(activity_register.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
                }

                else if(!(email.isEmpty() && pwd.isEmpty() && Rpwd.isEmpty() && Hp.isEmpty())){
                    Intent intent5 = new Intent(getApplicationContext(), verify_phone_no.class);
                    intent5.putExtra("Email",Email);
                    intent5.putExtra("Password",pwd);
                    intent5.putExtra("Hp",Phone);

                    startActivity(intent5);
                    /*mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(activity_register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(activity_register.this, "Registration Unsuccesfull, Please try again!", Toast.LENGTH_SHORT).show();
                            }

                            else{
                                Toast.makeText(activity_register.this, "Registration Succesfull!", Toast.LENGTH_SHORT).show();
                                //reference.child(Hp).setValue(helperclass);
                                String userId = mAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fStore.collection("users").document(userId);
                                Map<String,Object> user = new HashMap<>();
                                user.put("Email", Email);
                                user.put("PhoneNo",Phone);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d("TAG","User registered for" + userId);
                                    }
                                });
                                openLogin();
                            }
                        }
                    }
                    );*/
                }
            }

        });


        back = (Button) findViewById(R.id.backreg);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPrevRegister();
            }
        });

    }
    public void openPrevRegister(){
        Intent intent3 = new Intent(activity_register.this, MainActivity.class);
        startActivity(intent3);
        finish();
    }

    public void openLogin(){
        Intent intent4 = new Intent(activity_register.this, Login.class);
        startActivity(intent4);
        finish();
    }
}