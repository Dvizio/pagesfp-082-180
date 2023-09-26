package com.example.pagesfp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class dbHelper extends SQLiteOpenHelper {
    public dbHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table on database
        db.execSQL(Constants.CREATE_SUBJECT_TABLE);
        db.execSQL(Constants.CREATE_LOG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //upgrade table if any structure change in db

        // drop table if exists
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_SUBJECT_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_LOG_NAME);

        // create table again
        onCreate(db);
    }

    // insert subject
    public void insertSubject(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + Constants.TABLE_SUBJECT_NAME);
        db.execSQL(Constants.INSERT_SUBJECT_TABLE);
        db.close();
    }

    public long insertSubject(String name, String code, String className, String time) {
        SQLiteDatabase db = getWritableDatabase();
        long newRowId = -1; // Initialize newRowId to -1 (indicating failure).

        try {
            ContentValues values = new ContentValues();
            values.put(Constants.S_NAME, name);
            values.put(Constants.S_CODE, code);
            values.put(Constants.S_CLASS, className);
            values.put(Constants.S_TIME, time);

            // Insert the data into the SUBJECT_TABLE.
            newRowId = db.insertOrThrow(Constants.TABLE_SUBJECT_NAME, null, values);
        } catch (SQLException e) {
            // Handle the exception, e.g., log an error message or throw it.
            e.printStackTrace();
        } finally {
            db.close(); // Close the database connection.
        }

        return newRowId; // Return the ID of the newly inserted row.
    }

    // get name subject fron id
    public String getSubjectName(String id){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT "+ Constants.S_NAME + " FROM " + Constants.TABLE_SUBJECT_NAME + " WHERE " + Constants.S_ID + " == " + id;
        Cursor res = db.rawQuery(query,null);
        if(res.getCount()>0){
            res.moveToFirst();
            String temp = res.getString(res.getColumnIndexOrThrow(Constants.S_NAME));
            db.close();
            return temp;
        }
        else {
            db.close();
            return "";
        }
    }
    // Insert log
    public long insertLog(String name,String timestamp){

        //get writable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValue class object to save data
        ContentValues contentValues = new ContentValues();

        // id will save automatically as we write query
        contentValues.put(Constants.L_NAME,name);
        contentValues.put(Constants.L_TIMESTAMP,timestamp);

        //insert data in row, It will return id of record
        long id = db.insert(Constants.TABLE_LOG_NAME,null,contentValues);

        // close db
        db.close();

        //return id
        return id;
    }

    // get data
    public ArrayList<SubjectModel> getAllSubjectData(){
        insertSubject();
        //create arrayList
        ArrayList<SubjectModel> arrayList = new ArrayList<>();
        //sql command query
        String selectQuery = "SELECT * FROM "+Constants.TABLE_SUBJECT_NAME;

        //get readable db
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        Log.d("debug", "brp "+cursor.getCount());

        // looping through all record and add to list
        if (cursor.moveToFirst()){
            do {
                SubjectModel subjectModel = new SubjectModel(
                        // only id is integer type
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.S_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.S_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.S_CODE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.S_CLASS)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.S_TIME))
                );
                arrayList.add(subjectModel);
            }while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

    public ArrayList<LogModel> getAllLogData(){
        //create arrayList
        ArrayList<LogModel> arrayList = new ArrayList<>();
        //sql command query
        String selectQuery = "SELECT * FROM "+Constants.TABLE_LOG_NAME;

        //get readable db
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all record and add to list
        if (cursor.moveToFirst()){
            do {
                LogModel logModel = new LogModel(
                        // only id is integer type
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.L_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.L_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.L_TIMESTAMP))
                );
                arrayList.add(logModel);
            }while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

}
