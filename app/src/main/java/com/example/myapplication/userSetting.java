package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class userSetting extends AppCompatActivity {


    DatabaseHelper myDB;

    EditText ed1, ed2, ed3, ed4;

    Button b1;

    Text Loguser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);



        myDB = new DatabaseHelper(this);

        ed1 = (EditText) findViewById(R.id.fullname);
        ed2 = (EditText) findViewById(R.id.nic);
        ed3 = (EditText) findViewById(R.id.address);
        ed4 = (EditText) findViewById(R.id.phone);
        b1 = (Button) findViewById(R.id.submit);



        addAccountSettings();

    }





    public void addAccountSettings() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = ed1.getText().toString();
                String nic = ed2.getText().toString();
                String address = ed3.getText().toString();
                String phone = ed4.getText().toString();


                boolean isSend = myDB.insertaccountSettings(name,nic,address,phone);
                if (isSend == true)
                    Toast.makeText(userSetting.this, "profile details added successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(userSetting.this, "profile details added Failed!!", Toast.LENGTH_LONG).show();


            }


        });


    }
}
