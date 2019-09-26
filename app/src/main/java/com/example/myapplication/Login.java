package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText e1,e2;
    Button b1;
    TextView register;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper( this);


        e1 =(EditText)findViewById(R.id.email);
        e2 = (EditText)findViewById(R.id.pass);
        b1 = (Button)findViewById(R.id.btn1);
        register = (TextView)findViewById(R.id.register);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = e1.getText().toString();
                String password = e2.getText().toString();
                Boolean checkEmailPass = db.emailpassword(email,password);

                if(checkEmailPass == true) {
                    Toast.makeText(getApplicationContext(), "successfully login", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(Login.this,HomePage.class);
                    i.putExtra("email",email);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(),"Wrong username or password",Toast.LENGTH_SHORT).show();

            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login.this,register.class);
                startActivity(registerIntent);
            }
        });



    }
}
