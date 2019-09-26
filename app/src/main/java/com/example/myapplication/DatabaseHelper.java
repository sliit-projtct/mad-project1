package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "HouseRent2.db";
    private static final String TABLE_NAME = "Reservation";
    private static final String COL_1 = "Rno";
    private static final String COL_2 = "Name";
    private static final String COL_3 = "City";
    private static final String COL_4 = "HouseNo";
    private static final String COL_5 = "Duration";


    public static final String TABLE_NAME_FB = "feedback_table";
    public static final String COLUMN_FB1 = "ID";
    public static final String COLUMN_FB2 = "NAME";
    public static final String COLUMN_FB3 = "FEEDBACK";

    public static final String TABLE_NAME_AD = "advertisement_table";
    public static final String COLUMN_AD1 = "ID";
    public static final String COLUMN_AD2 = "LOCATION";
    public static final String COLUMN_AD3 = "DESCRIPTION";
    public static final String COLUMN_AD4 = "MOBILE";
    public static final String COLUMN_AD5 = "EMAIL";


    //****************Udara**************************
    private static final String TABLE_ONE_NAME = "Pay_table";
    private static final String COL_1_1 = "PayID";
    private static final String COL_1_2 = "HouseID";
    private static final String COL_1_3 = "Time";
    private static final String COL_1_4 = "Phone";

    private static final String TABLE_TWO_NAME = "CreditCard_table";
    private static final String COL_2_1 = "CID";
    private static final String COL_2_2 = "CNUMBER";
    private static final String COL_2_3 = "CDATE";
    private static final String COL_2_4 = "CKEY";
    private static final String COL_2_5 = "CNAME";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(" create table " + TABLE_NAME + " (Rno INTEGER PRIMARY KEY AUTOINCREMENT , Name TEXT , City TEXT , HouseNo INTEGER , Duration INTEGER  ) ");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME_FB + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, FEEDBACK TEXT)");
        sqLiteDatabase.execSQL("create table " + TABLE_NAME_AD + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, LOCATION TEXT, DESCRIPTION TEXT, MOBILE INTEGER, EMAIL TEXT)");
        sqLiteDatabase.execSQL("create table CreditCard_table (CID INTEGER PRIMARY KEY AUTOINCREMENT, CNUMBER TEXT, CDATE TEXT ,CKEY TEXT ,CNAME TEXT )");
        sqLiteDatabase.execSQL("create table Pay_table (PayID INTEGER PRIMARY KEY AUTOINCREMENT, HouseID TEXT, Time TEXT , Phone TEXT)");


        sqLiteDatabase.execSQL("CREATE TABLE user(email text  primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FB);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_AD);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TWO_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ONE_NAME);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");

        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String city, String hNo, String duration) {


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, city);
        contentValues.put(COL_4, hNo);
        contentValues.put(COL_5, duration);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    //Feedback Insert

    public boolean sendFeedback(String name, String feedback) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FB2, name);
        contentValues.put(COLUMN_FB3, feedback);
        long results = sqLiteDatabase.insert(TABLE_NAME_FB, null, contentValues);
        if (results == -1)
            return false;
        else
            return true;

    }

    public boolean createAD(String location, String description, String mobile, String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_AD2, location);
        contentValues.put(COLUMN_AD3, description);
        contentValues.put(COLUMN_AD4, mobile);
        contentValues.put(COLUMN_AD5, email);
        long results = sqLiteDatabase.insert(TABLE_NAME_AD, null, contentValues);
        if (results == -1)
            return false;
        else
            return true;

    }


    public Cursor getAllData() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    //suneth get fb
    public Cursor getAllFeedback() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME_FB, null);
        return res;
    }

    public Cursor getAllAD() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME_AD, null);
        return res;
    }


    public boolean updateData(String rno, String name, String city, String hNo, String duration) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, rno);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, city);
        contentValues.put(COL_4, hNo);
        contentValues.put(COL_5, duration);

        sqLiteDatabase.update(TABLE_NAME, contentValues, "Rno = ?", new String[]{rno});

        return true;
    }

    //Suneth Update Fb
    public boolean updateFeedback(String id, String name, String feedback) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FB1, id);
        contentValues.put(COLUMN_FB2, name);
        contentValues.put(COLUMN_FB3, feedback);
        sqLiteDatabase.update(TABLE_NAME_FB, contentValues, "ID=?", new String[]{id});
        return true;

    }

    public boolean updateAD(String id, String location, String description, String mobile, String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_AD1, id);
        contentValues.put(COLUMN_AD2, location);
        contentValues.put(COLUMN_AD3, description);
        contentValues.put(COLUMN_AD4, mobile);
        contentValues.put(COLUMN_AD5, email);
        sqLiteDatabase.update(TABLE_NAME_AD, contentValues, "ID=?", new String[]{id});
        return true;

    }

    public boolean deleteData(String rno, String name, String city, String hNo, String duration) {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, rno);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, city);
        contentValues.put(COL_4, hNo);
        contentValues.put(COL_5, duration);

        sqLiteDatabase.delete(TABLE_NAME, "Rno = ?", new String[]{rno});

        return true;
    }

    //SUneth FB Delete
    public Integer deleteFeedback(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME_FB, "ID = ?", new String[]{id});


    }

    public Integer deleteAD(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME_AD, "ID = ?", new String[]{id});


    }


    //#####################chanaka####################

    public boolean insert(String email, String password) {

        SQLiteDatabase db = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);
        if (ins == -1)
            return false;
        else
            return true;


    }

    //checking email if exists;
    public boolean chkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email=?", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;

    }

    //checking the email and password ;

    public Boolean emailpassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email=? and password=?", new String[]{email, password});

        if (cursor.getCount() > 0) return true;
        else return false;


    }


//  ***********************Udara***********************

    public boolean insertDataPay(String HouseID , String Time, String Phone )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1_2,HouseID);
        contentValues.put(COL_1_3,Time);
        contentValues.put(COL_1_4,Phone);
        long result = db.insert(TABLE_ONE_NAME , null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataCard(String CNUMBER ,String CDATE , String CKEY , String CNAME){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_2,CNUMBER);
        contentValues.put(COL_2_3,CDATE);
        contentValues.put(COL_2_4,CKEY);
        contentValues.put(COL_2_5,CNAME);
        long result = db.insert(TABLE_TWO_NAME , null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAllDataCard()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_TWO_NAME,null );
        return res;
    }

    public Cursor getAllDataPay()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_ONE_NAME,null );
        return res;
    }


    public boolean updateDataCard( String CID , String CNUMBER ,String CDATE , String CKEY , String CNAME){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_1,CID);
        contentValues.put(COL_2_2,CNUMBER);
        contentValues.put(COL_2_3,CDATE);
        contentValues.put(COL_2_4,CKEY);
        contentValues.put(COL_2_5,CNAME);
        db.update(TABLE_TWO_NAME, contentValues ,"CID = ?",  new String[] {CID});
        return true;
    }

    public boolean updateDataPay(String PayID, String HouseID , String Time, String Phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1_1,PayID);
        contentValues.put(COL_1_2,HouseID);
        contentValues.put(COL_1_3,Time);
        contentValues.put(COL_1_4,Phone);
        db.update(TABLE_ONE_NAME, contentValues ,"PayID = ?",  new String[] {PayID});
        return true;
    }

    public Integer deleteDataCar(String CID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_TWO_NAME, "CID = ?", new String[] {CID});
    }

    public Integer deleteDataPay(String PayID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_ONE_NAME, "PayID = ?", new String[] {PayID});
    }
}