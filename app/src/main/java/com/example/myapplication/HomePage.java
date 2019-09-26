package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePage extends AppCompatActivity{
    TextView Loguser;




    Button rent,pay,feedback,ad;
    ImageView imageView2;
    TextView textView;
    Animation fromtopbottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        fromtopbottom= AnimationUtils.loadAnimation(this,R.anim.fromtopbottom);

        rent=(Button)findViewById(R.id.rent);
        pay=(Button)findViewById(R.id.pay);
        feedback=(Button)findViewById(R.id.feedback1);
        ad=(Button)findViewById(R.id.ad1);
        textView=(TextView)findViewById(R.id.textView);
        imageView2=(ImageView) findViewById(R.id.imageView2);


        imageView2.startAnimation(fromtopbottom);
        textView.startAnimation(fromtopbottom);
        rent.startAnimation(fromtopbottom);
        pay.startAnimation(fromtopbottom);
        feedback.startAnimation(fromtopbottom);
        ad.startAnimation(fromtopbottom);



        //Chanaka******************************

        Loguser= findViewById(R.id.txtlogedUser);

        Intent intent = getIntent();
        String LoggedUser = intent.getExtras().getString("email");


        Loguser.setText("User : "+LoggedUser);

        //****************************************************








        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){


                Intent a = new Intent( HomePage.this, MainActivity.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });

        //Feedback
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){


                Intent a = new Intent( HomePage.this, FeedbackActivity.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });

        //AD
        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent a = new Intent( HomePage.this, AddAdActivity.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });

        //Pay
        pay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent a = new Intent(HomePage.this, Home.class);
                        a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(a);
                    }
                }
        );


    }



}
