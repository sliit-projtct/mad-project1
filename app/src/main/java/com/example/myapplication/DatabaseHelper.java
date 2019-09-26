package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "HouseRent.db";
    private static final String TABLE_NAME ="Reservation";
    private static final String COL_1 ="Rno";
    private static final String COL_2 ="Name";
    private static final String COL_3 ="City";
    private static final String COL_4 ="HouseNo";
    private static final String COL_5 ="Duration";

    //Suneth

    public static final String TABLE_NAME_FB="feedback_table";
    public static final String COLUMN_FB1="ID";
    public static final String COLUMN_FB2="NAME";
    public static final String COLUMN_FB3="FEEDBACK";




    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(" create table " + TABLE_NAME + " (Rno INTEGER PRIMARY KEY AUTOINCREMENT , Name TEXT , City TEXT , HouseNo INTEGER , Duration INTEGER  ) ");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME_FB + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, FEEDBACK TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE user(email text  primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_FB);




        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");

        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name ,String city ,String hNo ,String duration ){


        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,city);
        contentValues.put(COL_4,hNo);
        contentValues.put(COL_5,duration);

         long result=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

         if(result==-1) {
             return false;
         }
         else {
             return true;
         }
    }
    //Feedback Insert

    public boolean sendFeedback(String name, String feedback){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_FB2, name);
        contentValues.put(COLUMN_FB3, feedback);
        long results=sqLiteDatabase.insert(TABLE_NAME_FB, null, contentValues);
        if (results==-1)
            return false;
        else
            return true;

    }

    public Cursor getAllData(){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+ TABLE_NAME,null);
        return res;
    }
    //suneth get fb
    public Cursor getAllFeedback(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME_FB, null);
        return res;
    }


    public boolean updateData(String rno,String name ,String city ,String hNo ,String duration ){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,rno);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,city);
        contentValues.put(COL_4,hNo);
        contentValues.put(COL_5,duration);

        sqLiteDatabase.update(TABLE_NAME , contentValues,"Rno = ?", new String[] { rno });

        return true;
    }

    //Suneth Update Fb
    public boolean updateFeedback(String id, String name, String feedback){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FB1, id);
        contentValues.put(COLUMN_FB2, name);
        contentValues.put(COLUMN_FB3, feedback);
        sqLiteDatabase.update(TABLE_NAME_FB, contentValues, "ID=?", new String[] {id});
        return true;

    }

    public boolean deleteData(String rno,String name ,String city ,String hNo ,String duration ){

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,rno);
       contentValues.put(COL_2,name);
        contentValues.put(COL_3,city);
        contentValues.put(COL_4,hNo);
        contentValues.put(COL_5,duration);

        sqLiteDatabase.delete(TABLE_NAME,"Rno = ?", new String[] { rno });

        return true;
    }
    //SUneth FB Delete
    public Integer deleteFeedback(String id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME_FB, "ID = ?", new String[] {id});


    }



    //#####################chanaka####################

    public boolean insert(String email,String password){

        SQLiteDatabase db = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long ins = db.insert("user", null,contentValues);
        if(ins == -1)
            return false;
        else
            return  true;


    }

    //checking email if exists;
    public boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email=?",new String[]{email});
        if(cursor.getCount()>0) return  false;
        else return  true;

    }

    //checking the email and password ;

    public Boolean emailpassword(String email,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email=? and password=?",new String[]{email,password});

        if(cursor.getCount()>0) return true;
        else return false;


    }
}

//  ***********************Udara***********************

