package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import es.dmoral.toasty.Toasty;

public class userSetting extends AppCompatActivity {

    String username;

    DatabaseHelper myDB;

    EditText ed1, ed2, ed3, ed4;

    Button b2,b3;

    TextView Loguser;


    public userSetting(){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);



        myDB = new DatabaseHelper(this);

        ed1 = (EditText) findViewById(R.id.fullname);
        ed2 = (EditText) findViewById(R.id.nic);
        ed3 = (EditText) findViewById(R.id.address);
        ed4 = (EditText) findViewById(R.id.phone);

        b2=(Button)findViewById(R.id.show);
        b3=(Button)findViewById(R.id.Update);







        Loguser= findViewById(R.id.txtlogedUser);

        Intent intent = getIntent();
        final String LoggedUser = intent.getExtras().getString("email");

        this.username = LoggedUser;

        Loguser.setText("User : "+LoggedUser);






        viewAll();
        updateUserDetails();

    }











    public void viewAll(){
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDB.getuserSetting(username);
                if (res.getCount()==0){
                    //show msg
                    showMessage("Error", "No Data Found!!!");
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Email: " + res.getString(0)+ "\n");
                    buffer.append("Password: " + res.getString(1)+ "\n");
                    buffer.append("Name: " + res.getString(2)+ "\n");
                    buffer.append("Nic: " + res.getString(3)+ "\n\n");

                }
                //show all data
                showMessage("Data", buffer.toString());

            }
        });
    }


    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



    public void updateUserDetails(){
        b3.setOnClickListener(new View.OnClickListener() {
            //private String username;

            @Override
            public void onClick(View view) {


                String name = ed1.getText().toString();
                String nic = ed2.getText().toString();

                String phone = ed4.getText().toString();




                Intent intent2 = getIntent();
                 String LoggedUser1 = intent2.getExtras().getString("email");


               // this.username =  LoggedUser;






                    boolean isUpdated = myDB.updateUserSetting(LoggedUser1,name,nic,phone);

                    if (isUpdated == true)
                        Toasty.success(userSetting.this, " Update Successfully", Toast.LENGTH_LONG).show();
                    else
                        Toasty.warning(userSetting.this, "something went wrong", Toast.LENGTH_LONG).show();


                }


        });

    }



}
