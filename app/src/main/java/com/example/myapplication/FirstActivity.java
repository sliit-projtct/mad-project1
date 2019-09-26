package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class FirstActivity extends AppCompatActivity {

    private EditText CardNumber, CardDate, CardKey, CardName;
    private Button buttonBuy;
    DatabaseHelper mydb;

    DatePickerDialog picker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mydb = new DatabaseHelper(this);

        CardNumber =  findViewById(R.id.CNUMBER);
        CardKey =  findViewById(R.id.CKEY);
        CardName =  findViewById(R.id.CNAME);
        buttonBuy  =  findViewById(R.id.byn);

        CardDate = findViewById(R.id.CDATE);
        CardDate.addTextChangedListener(mDateEntryWatcher);

        addData();

        CardDate.setInputType(InputType.TYPE_NULL);
        CardDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(FirstActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                CardDate.setText((monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


    }



    public void addData() {

        buttonBuy.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkEntries() == true) {
                            boolean isInserted = mydb.insertDataCard(
                                    CardNumber.getText().toString(),
                                    CardDate.getText().toString(),
                                    CardKey.getText().toString(),
                                    CardName.getText().toString());

                            if (isInserted == true) {
                                Toast.makeText(FirstActivity.this, "Payment is Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(FirstActivity.this , Home.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(FirstActivity.this, "Card is not Added", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            checkEntries1();
                        }
                    }



                }
        );

    }


    public boolean checkEntries() {
        String cardName = CardName.getText().toString();
        String cardNumber = CardNumber.getText().toString();
        String cardCVV = CardKey.getText().toString();

        if (TextUtils.isEmpty(cardName)) {
            return false;
        }else if (TextUtils.isEmpty(cardNumber) || !isValid(cardNumber)){
            return false;
        }else if (TextUtils.isEmpty(cardCVV)||cardCVV.length()<3){
            return false;
        } else
            return true;
    }

    public void checkEntries1() {
        String cardName = CardName.getText().toString();
        String cardNumber = CardNumber.getText().toString();
        String cardCVV = CardKey.getText().toString();

        if (TextUtils.isEmpty(cardName)) {
            Toast.makeText(FirstActivity.this, "Enter Valid Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cardNumber) || !isValid(cardNumber)) {
            Toast.makeText(FirstActivity.this, "Enter Valid card number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cardCVV)||cardCVV.length()<3) {
            Toast.makeText(FirstActivity.this, "Enter valid security number", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(FirstActivity.this, "Your card is added", Toast.LENGTH_LONG).show();

    }

    public static boolean isValid(String cardNumber) {
        if (!TextUtils.isEmpty(cardNumber) && cardNumber.length() >= 4)
            if ((cardNumber.length() == 13 || cardNumber.length() == 16))
                return true;
            else if (cardNumber.length() == 16)
                return true;
            else if (cardNumber.length() == 15)
                return true;
            else if (cardNumber.length() == 14)
                return true;
        return false;
    }

    private TextWatcher mDateEntryWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String working = s.toString();
            boolean isValid = true;
            if (working.length()==2 && before ==0) {
                if (Integer.parseInt(working) < 1 || Integer.parseInt(working)>12) {
                    isValid = false;
                } else {
                    working+="/";
                    CardDate.setText(working);
                    CardDate.setSelection(working.length());
                }
            }
            else if (working.length()==7 && before ==0) {
                String enteredYear = working.substring(3);
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                if (Integer.parseInt(enteredYear) < currentYear) {
                    isValid = false;
                }
            } else if (working.length()!=7) {
                isValid = false;
            }

            if (!isValid) {
                CardDate.setError("Enter a valid date: MM/YYYY");
            } else {
                CardDate.setError(null);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {}

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    };

 /*   @Override
    public void onBackPressed() {
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

