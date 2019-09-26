package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.text.TextWatcher;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements android.text.TextWatcher , AdapterView.OnItemSelectedListener/*,View.OnClickListener*/ {

    DatabaseHelper myDb;

    String[] city={"Colombo","Matara","Galle","Kandy"};

    String[] duration={"6 Month","1 Year","2 Year","Up to 3 Year"};
    EditText name,  hNo,rno;
    Button submit,back,next,textView7,textView6,textView8,textView9,view1,update,delete;
    String n, c, h, d ,r;
    Animation fromtopbottom;
    Spinner spin;
    Spinner spin1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        fromtopbottom= AnimationUtils.loadAnimation(this,R.anim.fromtopbottom);

        name = (EditText) findViewById(R.id.name);
       // city = (EditText) findViewById(R.id.city);
         spin = (Spinner) findViewById(R.id.city);
     //   String text=spin.getSelectedItem().toString();
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,city);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);


        hNo = (EditText) findViewById(R.id.hNo);
       // duration = (EditText) findViewById(R.id.duration);

        spin1 = (Spinner) findViewById(R.id.duration);
        //   String text=spin.getSelectedItem().toString();
        spin1.setOnItemSelectedListener(this);

        ArrayAdapter aa1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,duration);

        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(aa1);


        rno = (EditText) findViewById(R.id.rno);
        submit = (Button) findViewById(R.id.submit);
        back = (Button) findViewById(R.id.back);
        textView7 = (Button) findViewById(R.id.textView7);
        textView6 = (Button) findViewById(R.id.textView6);
        textView8 = (Button) findViewById(R.id.textView8);
        textView9 = (Button) findViewById(R.id.textView9);
        view1 = (Button) findViewById(R.id.view1);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);

        AddData();

        viewAll();

        updateData();

        deleteData();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){


                Intent a = new Intent( MainActivity.this, HomePage.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });

        textView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){


                Intent a = new Intent( MainActivity.this, Matara.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });

        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){


                Intent a = new Intent( MainActivity.this, Colombo.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });

        textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){


                Intent a = new Intent( MainActivity.this, Galle.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });
        textView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){


                Intent a = new Intent( MainActivity.this, Kandy.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });

      //  submit.setOnClickListener(this);

        name.startAnimation(fromtopbottom);
        spin.startAnimation(fromtopbottom);
        hNo.startAnimation(fromtopbottom);
        spin1.startAnimation(fromtopbottom);
        submit.startAnimation(fromtopbottom);
      /*  back.startAnimation(fromtopbottom);
        next.startAnimation(fromtopbottom);*/

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable s) {

                String filtered=s.toString();

                if(filtered.matches(".*[^a-z^A-Z].*")) {

                    filtered = filtered.replaceAll("[^a-z^A-Z]", "");
                    s.clear();
                    name.setError("Invalid Charcter...!!!");


                }

            }
        });
             /*   city.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                    @Override
                    public void afterTextChanged(Editable s) {

                        String filtered=s.toString();

                        if(filtered.matches(".*[^a-z^A-Z].*")) {

                            filtered = filtered.replaceAll("[^a-z^A-Z]", "");
                            s.clear();
                            city.setError("Invalid Charcter...!!!");


                        }

                    }
                });*/

        hNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable s) {

                String filtered=s.toString();

                if(filtered.matches(".*[^0-9].*")) {

                    filtered = filtered.replaceAll("[^0-9]", "");
                    s.clear();
                    hNo.setError("Invalid Number...!!!");


                }


                else  {
                    if(!hNoValidator(s)){

                        hNo.setError("Please Enter The Range Between (0-5)...!!!");
                    }
                }
            }
        });
     /*   duration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable s) {

                String filtered=s.toString();
                if(filtered.matches(".*[^0-9].*")) {
                    filtered = filtered.replaceAll("[^0-9]", "");
                    s.clear();
                    duration.setError("invalid Number...!!!");
                }
                else  {
                    if(!hNoValidator(s)){

                        duration.setError("Please Enter The Range Between (0-5)...!!!");
                    }
                }
            }
        });*/
    }

    public void AddData(){

        submit.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (view.getId() == R.id.submit) {

                            n = name.getText().toString().trim();
                            c = spin.getSelectedItem().toString().trim();
                            h = hNo.getText().toString().trim();
                            d = spin1.getSelectedItem().toString().trim();

                            if (n.equals("") || c.equals("") || h.equals("") || d.equals("")) {

                                Toast.makeText(MainActivity.this, "Data feild can not be empty", Toast.LENGTH_LONG).show();

                                return;
                            } else {






                       boolean inserted = myDb.insertData(name.getText().toString(), spin.getSelectedItem().toString(),hNo.getText().toString(),spin1.getSelectedItem().toString());

                       if(inserted == true) {
                           Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                       }
                       else {

                           Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                           /*if (view.getId() == R.id.submit) {

                               n = name.getText().toString().trim();
                               c = city.getText().toString().trim();
                               h = hNo.getText().toString().trim();
                               d = duration.getText().toString().trim();

                               if (n.equals("") || c.equals("") || h.equals("") || d.equals("")) {

                                   Toast.makeText(MainActivity.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                                   return;
                               } else {


                                   Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();

                               }
                           }*/

                       }
                    }
                }

                    }
                }
        );





    }

    public void  viewAll(){

        view1.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                       Cursor res = myDb.getAllData();

                       if(res.getCount() == 0){

                           showMessage("Error","Nothing found");
                           return;
                       }

                       StringBuffer buffer =new StringBuffer();

                       while(res.moveToNext()){

                           buffer.append("Rno :"+res.getString(0)+"\n");
                           buffer.append("Name :"+res.getString(1)+"\n");
                           buffer.append("City :"+res.getString(2)+"\n");
                           buffer.append("HouseNo :"+res.getString(3)+"\n");
                           buffer.append("Duration :"+res.getString(4)+"\n");

                       }
                        showMessage("Reservation Details",buffer.toString());

                    }
                }
        );
    }

    public  void showMessage(String title,String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void updateData(){


        update.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (view.getId() == R.id.update) {

                            n = name.getText().toString().trim();
                            c = spin.getSelectedItem().toString().trim();
                            h = hNo.getText().toString().trim();
                            d = spin1.getSelectedItem().toString().trim();

                            if (n.equals("") || c.equals("") || h.equals("") || d.equals("")) {

                                Toast.makeText(MainActivity.this, "Data feild can not be empty", Toast.LENGTH_LONG).show();

                                return;
                            } else {

                                boolean isUpdate = myDb.updateData(rno.getText().toString(), name.getText().toString(), spin.getSelectedItem().toString(), hNo.getText().toString(),spin1.getSelectedItem().toString());

                                if (isUpdate == true) {

                                    Toast.makeText(MainActivity.this, "Data Is Updated", Toast.LENGTH_LONG).show();
                                } else {

                                    Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                }

        );
    }

    public void deleteData(){

        delete.setOnClickListener(


                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (view.getId() == R.id.delete) {

                            r=rno.getText().toString().trim();


                            if (r.equals("") ) {

                                Toast.makeText(MainActivity.this, "Data feild can not be empty", Toast.LENGTH_LONG).show();

                                return;
                            } else {


                                boolean isDelete = myDb.deleteData(rno.getText().toString(), name.getText().toString(),spin.getSelectedItem().toString(), hNo.getText().toString(),spin1.getSelectedItem().toString());

                                if (isDelete == true) {

                                    Toast.makeText(MainActivity.this, "Data Is Deleted", Toast.LENGTH_LONG).show();
                                } else {

                                    Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                }
        );

    }


 /* @Override
    public void onClick(View view) {

        if (view.getId() == R.id.submit) {

            n = name.getText().toString().trim();
            c = city.getText().toString().trim();
            h = hNo.getText().toString().trim();
            d = duration.getText().toString().trim();

            if (n.equals("") || c.equals("") || h.equals("") || d.equals("")) {

                Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                return;
            } else {



                    Toast.makeText(this, "...SAVED...", Toast.LENGTH_SHORT).show();

            }
        }


    }*/

    private  boolean hNoValidator(CharSequence hNo){

        if(hNo.length()<0||hNo.length()>4)
            return false;
        else
            return true;
    }

    private  boolean durationValidator(CharSequence duration){

        if(duration.length()<0||hNo.length()>4)
            return false;
        else
            return true;
    }






    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
    @Override
    public void afterTextChanged(Editable editable) { }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        Toast.makeText(getApplicationContext(), city[position], Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), duration[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }


}
