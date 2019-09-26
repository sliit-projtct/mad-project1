package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChoosePay extends AppCompatActivity {

    private TextView txt1,txt2;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pay);



        mydb = new DatabaseHelper(this);

        txt1 = findViewById(R.id.credit);

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoosePay.this, FirstActivity.class);
                startActivity(intent);
            }
        });

        txt2 = findViewById(R.id.paypal);
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoosePay.this, PayPalPay.class);
                startActivity(intent);
            }
        });




    }


 /*   @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Payment is cancel", Toast.LENGTH_SHORT).show();
        try {
            Thread.sleep(1000);
            Intent intent = new Intent(this,Home.class);
            startActivity(intent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}
