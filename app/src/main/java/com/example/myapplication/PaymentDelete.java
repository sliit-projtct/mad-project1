package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.dmoral.toasty.Toasty;

public class PaymentDelete extends AppCompatActivity {

    Button buttonPay;
    EditText date,houseId,phone,paymentID;
    DatabaseHelper mydb;
    //Test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_delete);

        buttonPay = findViewById(R.id.btnDelPay);
        date = findViewById(R.id.date);
        houseId = findViewById(R.id.houseID);
        phone = findViewById(R.id.phone);
        paymentID = findViewById(R.id.PaymentID);
        mydb = new DatabaseHelper(this);
        deleteData();
    }

    public void deleteData()
    {
        buttonPay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = mydb.deleteDataPay(
                                paymentID.getText().toString()
                        );
                        if(deletedRows > 0)
                        {
                            Toasty.success(PaymentDelete.this, "Data deleted", Toast.LENGTH_SHORT).show();
                            openActivity();
                        }
                        else
                        {
                            Toasty.error(PaymentDelete.this, "Not deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
    private void openActivity() {
        Intent intent = new Intent(this , PaymentList.class);
        startActivity(intent);
    }

 /*   @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }*/
}
