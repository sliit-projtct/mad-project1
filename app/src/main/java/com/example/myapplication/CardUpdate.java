package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CardUpdate extends AppCompatActivity {

    private EditText et1, et2, et3, et4, et5, et6;
    private Button b1,b3,b4;
    DatabaseHelper mydb;

    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_update);

        mydb = new DatabaseHelper(this);

        et1 =  findViewById(R.id.CNUMBER);
        et2 =  findViewById(R.id.CDATE);
        et3 =  findViewById(R.id.CKEY);
        et4 =  findViewById(R.id.CNAME);
        et5 =  findViewById(R.id.editText5);
        b3  =  findViewById(R.id.btnUp);
        b4  = findViewById(R.id.btnDel);


        updateData();
        deleteData();

        et2.setInputType(InputType.TYPE_NULL);
        et2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(CardUpdate.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                et2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

    }

    private void openActivity() {
        Intent intent = new Intent(this , Home.class);
        startActivity(intent);
    }


    public  void updateData()
    {
        b3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = mydb.updateDataCard(
                                et5.getText().toString(),
                                et1.getText().toString(),
                                et2.getText().toString(),
                                et3.getText().toString(),
                                et4.getText().toString()
                        );

                        if(isUpdated == true)
                        {
                            Toast.makeText(CardUpdate.this, "Update successful", Toast.LENGTH_SHORT).show();
                            openActivity();
                        }
                        else
                        {
                            Toast.makeText(CardUpdate.this, "Not successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    public void deleteData()
    {
        b4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = mydb.deleteDataCar(
                                et5.getText().toString()
                        );
                        if(deletedRows > 0)
                        {
                            Toast.makeText(CardUpdate.this, "Data deleted", Toast.LENGTH_SHORT).show();
                            openActivity();
                        }
                        else
                        {
                            Toast.makeText(CardUpdate.this, "Not deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

/*    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Update is cancel", Toast.LENGTH_SHORT).show();
        try {
            Thread.sleep(1000);
            Intent intent = new Intent(this,Home.class);
            startActivity(intent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}

