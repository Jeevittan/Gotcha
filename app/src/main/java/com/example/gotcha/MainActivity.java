package com.example.gotcha;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    private Button login,reg;

    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo,slogan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitylogin();
            }
        });

        reg = (Button) findViewById(R.id.registerbtn);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRegister();
            }
        });



        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image = findViewById(R.id.imageView2);
        logo = findViewById(R.id.textView4);
        slogan = findViewById(R.id.textView5);

        //image.setAnimation(bottomAnim);
        // logo.setAnimation(topAnim);
        // slogan.setAnimation(topAnim);
    }
        public void openActivitylogin() {
            Intent intent1 = new Intent(MainActivity.this, Login.class);
            startActivity(intent1);

        }

        public void openActivityRegister(){
                Intent intent2 = new Intent(MainActivity.this, activity_register.class);
                startActivity(intent2);

                }





        }
