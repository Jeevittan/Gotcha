package com.example.gotcha;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Login extends AppCompatActivity {
    String PhoneNo;
    //String PhoneNo="+60166572174";  use this number to test OTP = 123456
    EditText emailId,Password;
    Button login,forgotpass;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private  FirebaseAuth.AuthStateListener mAuthList;
    private Button back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login2);

        mAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.emailid);
        Password = findViewById(R.id.Mpassword);
        login = findViewById(R.id.login);
        forgotpass = findViewById(R.id.pass);
        PhoneNo = "+60166572174";


        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgotPassword();
            }
        });

        mAuthList = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull @NotNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(Login.this, "You're Logged-in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, Home.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Login.this, "Invalid Credential!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        /*private void isUser() {

            String userEnteredUsername = emailId.getText().toString();
            String userEnteredPassword = Password.getText().toString();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
            Query checkUser = reference.orderByChild("P")
        }*/


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = Password.getText().toString();
                String checkPassword = "^" +
                        "(?=.*[@#$%^&+=_])" +     // at least 1 special character

                        "(?=.*[0-9])" +     // at least 1 numeric character

                        "(?=.*[a-z])" +     // at least 1 alphabet character

                        "(?=.*[A-Z])" +     // at least 1 Alphabet character

                        "(?=\\S+$)" +                     // no white spaces

                        ".{8,}" +                              // at least 4 characters

                        "$";

                String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
                if (email.isEmpty()) {
                    emailId.setError("Please enter email address");
                    emailId.requestFocus();
                } else if (!email.matches(checkEmail)) {
                    emailId.setError("Invalid Email!");
                    emailId.requestFocus();
                }
                    //else if ()
                else if (pwd.isEmpty()) {
                    Password.setError("Please enter a password");
                    Password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(Login.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String userId = mAuth.getCurrentUser().getUid();
                                //FirebaseAuth.getInstance().signOut();


                                /*db.collection("dynamic_menu").addSnapshotListener(new EventListener<QuerySnapshot>() {
                                    @Override
                                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                                        if (e !=null)
                                        {

                                        }

                                        for (DocumentChange documentChange : documentSnapshots.getDocumentChanges())
                                        {
                                            String   PhoneNo =  documentChange.getDocument().getData().get("PhoneNo").toString();


                                        }
                                    }
                                });*/
                                /*documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                                        PhoneNo = documentSnapshot.getString("PhoneNo");
                                    }
                                });
                                fStore.collection("users").document(userId);*/



                                FirebaseAuth.getInstance().signOut();
                                Intent intent5 = new Intent(getApplicationContext(), verify_login.class);
                                intent5.putExtra("Email", email);
                                intent5.putExtra("Password", pwd);
                                intent5.putExtra("Hp", PhoneNo);
                                startActivity(intent5);
                    /*mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(Login.this, "Login Unsuccesfull, Please try again!", Toast.LENGTH_SHORT).show();
                                }

                                else{
                                    Toast.makeText(Login.this, "Login Succesfull!", Toast.LENGTH_SHORT).show();
                                    Intent ToHome = new Intent(Login.this,Hometab.class);
                                    startActivity(ToHome);
                                    finish();
                                }
                            }
                        });*/

                            } else {
                                Toast.makeText(Login.this, "Invalid Credential!", Toast.LENGTH_SHORT).show();
                            }
                        }


                    });
                }
            }
        });

        back = (Button) findViewById(R.id.backlog);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPrevLogin();
            }
        });
    }



            public void openPrevLogin() {
                Intent intent3 = new Intent(Login.this, MainActivity.class);
                startActivity(intent3);
                finish();
            }

            public void openForgotPassword() {
                Intent intent4 = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent4);
                finish();
            }



        }
