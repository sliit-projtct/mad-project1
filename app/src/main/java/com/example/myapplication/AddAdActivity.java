package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class AddAdActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editAdID,locEdit,desEdit,mobEdit,emailEdit;
    Button buttonCreate,buttonView,buttonUpdate,buttonDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ad);
        myDb = new DatabaseHelper(this);
        editAdID=findViewById(R.id.editAdId);
        locEdit=findViewById(R.id.editLocation);
        desEdit=findViewById(R.id.editDescription);
        mobEdit=findViewById(R.id.editMobile);
        emailEdit=findViewById(R.id.editEmail);
        buttonCreate=findViewById(R.id.btnCreate);
        buttonView=findViewById(R.id.btnSh);
        buttonUpdate=findViewById(R.id.btnUp);
        buttonDelete=findViewById(R.id.btnDel);

        createAD();
        viewAll();
        updateAD();
        deleteAD();

    }
    public void createAD(){
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (locEdit.getText().toString().equals("")){
                    locEdit.setError("Mandatory Field");
                }
                else if (desEdit.getText().toString().equals("")){
                    desEdit.setError("Mandatory Field");
                }
                else if (mobEdit.getText().toString().equals("")){
                    mobEdit.setError("Mandatory Field");
                }else if
                (mobEdit.length()!=10){
                    mobEdit.setError("Invalid Phone Number");
                }
                else{
                    String Email=emailEdit.getText().toString().trim();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (!Email.matches(emailPattern))
                    {
                        emailEdit.setError("Invalid email address");

                    }
                    /* Toast.makeText(AddAdActivity.this,"Something went Wrong",Toast.LENGTH_SHORT).show();*/

                    boolean isCreate=myDb.createAD(locEdit.getText().toString(), desEdit.getText().toString(), mobEdit.getText().toString(), emailEdit.getText().toString());
                    if (isCreate==true)
                        Toasty.success(AddAdActivity.this, "AD Created Successfully", Toast.LENGTH_LONG).show();
                    else
                        Toasty.error(AddAdActivity.this, "AD Create Failed!!",Toast.LENGTH_LONG).show();

                    clearControls();}

            }
        });

    }
    private void clearControls(){
        locEdit.setText("");
        desEdit.setText("");
        mobEdit.setText("");
        emailEdit.setText("");
    }
    public void viewAll(){
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=myDb.getAllAD();
                if (res.getCount()==0){
                    //show msg
                    showMessage("Error", "No DAta Found");
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id: " + res.getString(0)+ "\n");
                    buffer.append("Location: " + res.getString(1)+ "\n");
                    buffer.append("Description: " + res.getString(2)+ "\n");
                    buffer.append("Mobile: " + res.getString(3)+ "\n");
                    buffer.append("Email: " + res.getString(4)+ "\n\n");

                }
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
    public void updateAD(){
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated=myDb.updateAD(editAdID.getText().toString(),locEdit.getText().toString(), desEdit.getText().toString(), mobEdit.getText().toString(), emailEdit.getText().toString());

                if (isUpdated==true)
                    Toasty.success(AddAdActivity.this, " Update Successfully", Toast.LENGTH_LONG).show();
                else
                    Toasty.warning(AddAdActivity.this, "something went wrong", Toast.LENGTH_LONG).show();

                clearControls();


            }
        });

    }
    public void deleteAD(){
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows=myDb.deleteAD(editAdID.getText().toString());
                if (deletedRows > 0)
                    Toasty.success(AddAdActivity.this, "Delete Successfully", Toast.LENGTH_LONG).show();
                else
                    Toasty.warning(AddAdActivity.this, "something went wrong", Toast.LENGTH_LONG).show();

                clearControls();

            }
        });
    }
}
