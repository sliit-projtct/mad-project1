package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class register extends AppCompatActivity {

    DatabaseHelper db;

    EditText ed1,ed2,ed3;

    TextView login;

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        ed1 = (EditText)findViewById(R.id.email);
        ed2 = (EditText)findViewById(R.id.password);
        ed3 = (EditText)findViewById(R.id.conpass);
        b1 = (Button) findViewById(R.id.btn1);


        login = (TextView)findViewById(R.id.textView_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(register.this,Login.class);
                startActivity(loginIntent);
            }
        });









        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = ed1.getText().toString();
                String s2 = ed2.getText().toString();
                String s3 = ed3.getText().toString();

                if(s1.equals("")||s2.equals("")||s3.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields empty",Toast.LENGTH_SHORT).show();
                }




                else {

                    if(validateEmail(s1) == true) {

                        if((validatePassword(s2)) == true) {

                            if((validatePassword(s3)) == true) {


                                if (s2.equals(s3)) {
                                    Boolean chekemail = db.chkemail(s1);
                                    if (chekemail == true) {
                                        Boolean insert = db.insert(s1, s2);

                                        if (insert == true) {
                                            Toast.makeText(getApplicationContext(), "Registerd successfully", Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
                                }

                            }else{ ed3.setError("Password must be between 4 and 8 digits long and include at least one numeric digit.");}

                        }else{ ed2.setError("Password must be between 4 and 8 digits long and include at least one numeric digit.");}


                    }else{ed1.setError("Enter valid email"); }



                }


            }
        });







        //************************Validations**************************************//
        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String email = ed1.getText().toString();
                validateEmail(email);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        //************************End of the Validations**************************************//











    }







    //*******************************Vallidation Functions**********************************//

    public Boolean validateEmail(String email){

        Pattern digitCase = Pattern.compile("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$");



        if(!digitCase.matcher(email).find()){
            //ed1.setError("Enter valid email");
            return  false;
        }else return  true;
    }



    public boolean validatePassword(String password){

        Pattern digitCase = Pattern.compile("^(?=.*\\d).{4,8}$");



        if(!digitCase.matcher(password).find()){
            //ed1.setError("Password must be between 4 and 8 digits long and include at least one numeric digit.");
            return false;
        }else return true;
    }


    //*******************************End of the Vallidation Functions**********************************//




}