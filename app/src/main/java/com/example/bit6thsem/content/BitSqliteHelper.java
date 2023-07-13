package com.example.bit6thsem.content;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bit6thsem.CustomerModel.CustomerData;
import com.example.bit6thsem.utils.MyConstants;

import java.util.ArrayList;
import java.util.List;

public class BitSqliteHelper extends SQLiteOpenHelper {

    public BitSqliteHelper(Context context) {
        super(context, MyConstants.DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create table<table name>(columns..);
        //create table
        sqLiteDatabase.execSQL("create table "
                + MyConstants.CUSTOMER_TABLE_NAME
                + "(id integer primary key autoincrement,"
                + MyConstants.CUSTOMER_FNAME + " text,"
                + MyConstants.CUSTOMER_LNAME + " text,"
                + MyConstants.CUSTOMER_MOBILE_EMAIL + " text,"
                + MyConstants.CUSTOMER_PASSWORD + " text,"
                + MyConstants.CUSTOMER_YEAR + " integer,"
                + MyConstants.CUSTOMER_MONTH + " integer,"
                + MyConstants.CUSTOMER_DAY + " integer,"
                + MyConstants.CUSTOMER_GENDER + " text,"
                + MyConstants.CUSTOMER_COUNTRY + " text)"

        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "
                + MyConstants.CUSTOMER_TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    //insert data into table
    public boolean insertCustomerRecord(List<CustomerData> customerData) {
        //create table <table_name> (columns...)
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < customerData.size(); i++) {
            contentValues.put(MyConstants.CUSTOMER_FNAME, customerData.get(i).getFirstname());
            contentValues.put(MyConstants.CUSTOMER_LNAME, customerData.get(i).getLastname());
            contentValues.put(MyConstants.CUSTOMER_MOBILE_EMAIL, customerData.get(i).getMobile_email());
            contentValues.put(MyConstants.CUSTOMER_PASSWORD, customerData.get(i).getPassword());
            contentValues.put(MyConstants.CUSTOMER_YEAR, customerData.get(i).getYear());
            contentValues.put(MyConstants.CUSTOMER_MONTH, customerData.get(i).getMonth());
            contentValues.put(MyConstants.CUSTOMER_DAY, customerData.get(i).getDay());
            contentValues.put(MyConstants.CUSTOMER_GENDER, customerData.get(i).getGender());
            contentValues.put(MyConstants.CUSTOMER_COUNTRY, customerData.get(i).getCountry());
        }
        database.insert(MyConstants.CUSTOMER_TABLE_NAME, null, contentValues);
        Log.d("SqLiteDBHelper", "Records added successfully!");
        return true;


    }


    /*
    //read all records from table
        public ArrayList<CustomerData> getALlCustomerRecords() {
            //select * from <table_name> where id=1;

            ArrayList<CustomerData> testDataArrayList = new ArrayList<>();

            ArrayList<CustomerData> Records = new ArrayList<>();
            SQLiteDatabase database = this.getReadableDatabase();
            Cursor cursor = database.rawQuery("SELECT * FROM " + MyConstants.CUSTOMER_TABLE_NAME, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                boolean add = testDataArrayList.add(new CustomerData(
                        cursor.getString(MyConstants.CUSTOMER_FNAME,
                                MyConstants.CUSTOMER_LNAME,
                                MyConstants.CUSTOMER_MOBILE_EMAIL,
                                MyConstants.CUSTOMER_PASSWORD,
                                MyConstants.CUSTOMER_YEAR,
                                MyConstants.CUSTOMER_MONTH,
                                MyConstants.CUSTOMER_DAY,
                                MyConstants.CUSTOMER_GENDER,
                                MyConstants.CUSTOMER_COUNTRY ,


                                ));
            }
            return testDataArrayList;
        }
    */
//read all records from table
    public List<CustomerData> getALlCustomerRecords() {
        //select * from <table_name> where id=1;

        ArrayList<CustomerData> testDataArrayList = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase(); //MyConstants.DB_NAME

        Cursor cursor = database.rawQuery("SELECT * FROM "
                + MyConstants.CUSTOMER_TABLE_NAME
                + " WHERE "
                + MyConstants.CUSTOMER_FNAME
                + " NOT NULL ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            testDataArrayList.add(new CustomerData(cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9)));
            cursor.moveToNext();
        }

        cursor.close();
        for (CustomerData data:testDataArrayList){
            Log.d("BitSqliteHelper", "Firstname:"+data.getFirstname());
            Log.d("BitsqliteHelper", "Lastname:"+data.getLastname());
            Log.d("BitsqliteHelper", "MobileEmail:"+data.getMobile_email());
            Log.d("BitsqliteHelper", "Password:"+data.getPassword());
            Log.d("BitsqliteHelper", "Year:"+data.getYear());
            Log.d("BitsqliteHelper", "Month:"+data.getMonth());
            Log.d("BitsqliteHelper", "Day:"+data.getDay());
            Log.d("BitsqliteHelper", "Gender:"+data.getGender());
            Log.d("BitsqliteHelper", "Country:"+data.getCountry());

        }
        return testDataArrayList;
    }


    //update record in table
//    public boolean updateCustomerRe
    //insert data into table
    public boolean updateCustomerRecord(List<CustomerData> customerData) {
        //create table <table_name> (columns...)
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < customerData.size(); i++) {
            contentValues.put(MyConstants.CUSTOMER_FNAME, customerData.get(i).getFirstname());
            contentValues.put(MyConstants.CUSTOMER_LNAME, customerData.get(i).getLastname());
            contentValues.put(MyConstants.CUSTOMER_MOBILE_EMAIL, customerData.get(i).getMobile_email());
            contentValues.put(MyConstants.CUSTOMER_PASSWORD, customerData.get(i).getPassword());
            contentValues.put(MyConstants.CUSTOMER_YEAR, customerData.get(i).getYear());
            contentValues.put(MyConstants.CUSTOMER_MONTH, customerData.get(i).getMonth());
            contentValues.put(MyConstants.CUSTOMER_DAY, customerData.get(i).getDay());
            contentValues.put(MyConstants.CUSTOMER_GENDER, customerData.get(i).getGender());
            contentValues.put(MyConstants.CUSTOMER_COUNTRY, customerData.get(i).getCountry());
        }
        database.insert(MyConstants.CUSTOMER_TABLE_NAME, null, contentValues);
        Log.d("SqLiteDBHelper", "Records Updated successfully!");
        return true;


    }


}
