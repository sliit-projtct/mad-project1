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

public class FeedbackActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editFbID,editName,editFeedback;
    Button buttonSend,buttonShow,buttonUpdate,buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        myDb = new DatabaseHelper(this);

        editFbID=findViewById(R.id.editFbId);
        editName=findViewById(R.id.editName);
        editFeedback=findViewById(R.id.editFeedback);
        buttonSend=findViewById(R.id.btnSend);
        buttonShow=findViewById(R.id.btnShow);
        buttonUpdate=findViewById(R.id.btnUpdate);
        buttonDelete=findViewById(R.id.btnDelete);
        sendFeedback();
        viewAll();
        updateFeedback();
        deleteFeedback();

        //test


//        final EditText editName=(EditText)findViewById(R.id.editName);
//        final EditText editFeedback=(EditText)findViewById(R.id.editFeedback);
//
//        Button buttonSend=(Button)findViewById(R.id.btnSend);
//
//
//        buttonSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                final String Name=editName.getText().toString();
//                if (editName.getText().toString().equals("")){
//                    editName.setError("Mandatory Field");
//                }
//                else if(!Name.matches("[a-zA-Z]+")){
//                    editName.requestFocus();
//                    editName.setError("Please Enter Alphabetic Characters Only");
//                }
//                else if (editFeedback.getText().toString().equals("")){
//                    editFeedback.setError("Mandatory Field");
//                }
//
//
//            }
//        });
    }
    private void clearControls(){
        editName.setText("");
        editFeedback.setText("");
    }
    public void sendFeedback(){
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String Name=editName.getText().toString();
                if (editName.getText().toString().equals("")){
                    editName.setError("Mandatory Field");
                }
                else if (editFeedback.getText().toString().equals("")){
                    editFeedback.setError("Mandatory Field");
                }
                else{if(!Name.matches("[a-zA-Z]+")){
                    editName.requestFocus();
                    editName.setError("Please Enter Alphabetic Characters Only");
                }

//                    Toast.makeText(FeedbackActivity.this, "Feedback Fail",Toast.LENGTH_LONG).show();
                    Toasty.error(FeedbackActivity.this, "Feedback Fail", Toast.LENGTH_LONG).show();


                    boolean isSend=myDb.sendFeedback(editName.getText().toString(),editFeedback.getText().toString());
                    if (isSend==true)
//                    Toast.makeText(FeedbackActivity.this, "Feedback Sent",Toast.LENGTH_LONG).show();
                        Toasty.success(FeedbackActivity.this, "Feedback Sent",Toast.LENGTH_LONG).show();
                    else
                        Toasty.error(FeedbackActivity.this, "Feedback Sending Failed!!",Toast.LENGTH_LONG).show();

                    clearControls();
                }
            }
        });
    }
    public void viewAll(){
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllFeedback();
                if (res.getCount()==0){
                    //show msg
                    showMessage("Error", "No Data Found!!!");
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id: " + res.getString(0)+ "\n");
                    buffer.append("Name: " + res.getString(1)+ "\n");
                    buffer.append("Feedback: " + res.getString(2)+ "\n\n");

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
    public void updateFeedback(){
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Name=editName.getText().toString();
                if (editName.getText().toString().equals("")){
                    editName.setError("Mandatory Field");
                }
                else if (editFeedback.getText().toString().equals("")){
                    editFeedback.setError("Mandatory Field");
                }
                else {
                    if (!Name.matches("[a-zA-Z]+")) {
                        editName.requestFocus();
                        editName.setError("Please Enter Alphabetic Characters Only");
                    }

                    boolean isUpdated = myDb.updateFeedback(editFbID.getText().toString(), editName.getText().toString(), editFeedback.getText().toString());

                    if (isUpdated == true)
                        Toasty.success(FeedbackActivity.this, " Update Successfully", Toast.LENGTH_LONG).show();
                    else
                        Toasty.warning(FeedbackActivity.this, "something went wrong", Toast.LENGTH_LONG).show();

                    clearControls();
                }

            }
        });

    }
    public void deleteFeedback(){
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows=myDb.deleteFeedback(editFbID.getText().toString());
                if (deletedRows > 0)
                    Toasty.success(FeedbackActivity.this, "Delete Successfully", Toast.LENGTH_LONG).show();
                else
                    Toasty.warning(FeedbackActivity.this, "something went wrong", Toast.LENGTH_LONG).show();

                clearControls();

            }
        });
    }
}
