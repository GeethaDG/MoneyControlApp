package com.example.Bachat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Expense.db";
    public static final String TABLE_NAME = "expense_table";
    public static final String TABLE_NAME_EARNING = "earn_table";
    public static final String TABLE_NAME_CATEGORY_THRESHOLD = "threshold_table";
    public static final String Eid = "ID";
    public static final String Emode = "Mode";
    public static final String EAmount = "Amount";
    public static final String ENote = "Note";
    public static final String EDate = "Date";
    public static final String id = "ID";
    public static final String Category = "Category";
    public static final String Modeofpayment = "Modeofpayment";
    public static final String Subcategory = "SubCategory";
    public static final String Amount = "Amount";
    public static final String Date = "Date";
    public static final String Note = "Note";
    public static final String Threshold = "Threshold";
    public static final String ThresholdCategory = "Category";
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME_EARNING +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,MODE TEXT,AMOUNT INTEGER,DATE DATE,NOTE TEXT)");
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,CATEGORY TEXT,SUBCATEGORY TEXT,MODEOFPAYMENT TEXT,AMOUNT INTEGER,DATE DATE,NOTE TEXT)");
        db.execSQL("create table " + TABLE_NAME_CATEGORY_THRESHOLD+" (CATEGORY TEXT,THRESHOLD INTEGER)");
        Log.d(TAG, "onCreate: created all tables");
        db.execSQL("INSERT INTO threshold_table (Category,Threshold) values('"+ "Health" + "','"+  "Not Set"+ "')");
        db.execSQL("INSERT INTO threshold_table (Category,Threshold) values('"+ "Donations" + "','"+  "Not Set"+ "')");
        db.execSQL("INSERT INTO threshold_table (Category,Threshold) values('"+ "Bills" + "','"+  "Not Set"+ "')");
        db.execSQL("INSERT INTO threshold_table (Category,Threshold) values('"+ "Shopping" + "','"+  "Not Set"+ "')");
        db.execSQL("INSERT INTO threshold_table (Category,Threshold) values('"+ "Dining out" + "','"+  "Not Set"+ "')");
        db.execSQL("INSERT INTO threshold_table (Category,Threshold) values('"+ "Entertainment" + "','"+  "Not Set"+ "')");
        db.execSQL("INSERT INTO threshold_table (Category,Threshold) values('"+ "Groceries" + "','"+  "Not Set"+ "')");
        db.execSQL("INSERT INTO threshold_table (Category,Threshold) values('"+ "Pet" + "','"+  "Not Set"+ "')");
        db.execSQL("INSERT INTO threshold_table (Category,Threshold) values('"+ "Transport" + "','"+  "Not Set"+ "')");
        db.execSQL("INSERT INTO threshold_table (Category,Threshold) values('"+ "Loans" + "','"+  "Not Set"+ "')");
        db.execSQL("INSERT INTO threshold_table (Category,Threshold) values('"+ "Personal Care" + "','"+  "Not Set"+ "')");
        db.execSQL("INSERT INTO threshold_table (Category,Threshold) values('"+ "Miscellaneous" + "','"+  "Not Set"+ "')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EARNING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CATEGORY_THRESHOLD);
        onCreate(db);
    }

    public boolean insertData(String category, String subcategory, String modeofpayment, String amount, String date, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Category, category);
        contentValues.put(Subcategory, subcategory);
        contentValues.put(Modeofpayment, modeofpayment);
        contentValues.put(Amount, amount);
        contentValues.put(Date, date);
        contentValues.put(Note, note);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME, null);
        return res;
    }
    public Cursor getNote(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select note from " +TABLE_NAME+ " where id like " + "'" + id +"'", null);
        return res;
    }
    public boolean updateData(String id,String category, String subcategory, String modeofpayment, String amount, String date, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Category, category);
        contentValues.put(Subcategory, subcategory);
        contentValues.put(Modeofpayment, modeofpayment);
        contentValues.put(Amount, amount);
        contentValues.put(Date, date);
        contentValues.put(Note, note);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
    public boolean insertDataEarning(String emode, String eamount, String edate, String enote) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Emode, emode);
        contentValues.put(EAmount, eamount);
        contentValues.put(EDate, edate);
        contentValues.put(ENote, enote);
        long result = db.insert(TABLE_NAME_EARNING,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataEarning(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME_EARNING, null);
        return res;
    }
    public Cursor getNoteEarning(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select note from " +TABLE_NAME_EARNING+ " where id like " + "'" + id +"'", null);
        return res;
    }
    public boolean updateDataEarning(String id,String emode, String eamount, String edate, String enote) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Emode, emode);
        contentValues.put(EAmount, eamount);
        contentValues.put(EDate, edate);
        contentValues.put(ENote, enote);
        db.update(TABLE_NAME_EARNING, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteDataEarning(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_EARNING, "ID = ?",new String[] {id});
    }


    public boolean updateThreshold(String category, String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Threshold, amount);
        db.update(TABLE_NAME_CATEGORY_THRESHOLD, contentValues, "CATEGORY = ?",new String[] { category });
        return true;
    }

    public Cursor getAllDataThreshold(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME_CATEGORY_THRESHOLD, null);
        return res;
    }

    //To get expense for the filtering function
    public Cursor getAllExpenseCategory () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select DISTINCT CATEGORY from " + TABLE_NAME, null);
        return res;
    }

    //To get date for the filtering function
    public Cursor getSelectedAllDate(int yearInput, int monthInput) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (monthInput == 0) {
            Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where date like " + "'" + yearInput + "/%'", null);
            return res;
        } else {
            Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where date like " + "'" + yearInput + "/" + monthInput + "/%'", null);
            return res;
        }
    }

    //To get date for the filtering function with day input
    public Cursor getSelectedAllDateWithDay(int yearInput, int monthInput, int dayInput) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (monthInput == 0) {
            Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where date like " + "'" + yearInput + "/%/" + dayInput + "'", null);
            return res;
        } else {
            Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where date = " + "'" + yearInput + "/" + monthInput + "/" + dayInput + "'", null);
            return res;
        }
    }

    //To get date for the filtering function with day input
    public Cursor getSelectedCategoryAndDateWithDay(int yearInput, int monthInput, int dayInput, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (category == "All") {
            if (monthInput == 0) {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where category = '" + category + "'" + " and date like " + "'" + yearInput + "/%/" + dayInput + "'", null);
                return res;
            } else {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where category = '" + category + "'" + " and date = " + "'" + yearInput + "/" + monthInput + "/" + dayInput + "'", null);
                return res;
            }
        } else {
            if (monthInput == 0) {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where category = '" + category + "'" + " and date like " + "'" + yearInput + "/%/" + dayInput + "'", null);
                return res;
            } else {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where category = '" + category + "'" + " and date = " + "'" + yearInput + "/" + monthInput + "/" + dayInput + "'", null);
                return res;
            }
        }
    }

    //To filter based on category
    public Cursor getSelectedCategory(String category){
        SQLiteDatabase db = this.getWritableDatabase();
        if (category == "All"){
            Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
            return res;
        }
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where category = '" + category + "'",null);
        return res;
    }

    //To filter based on category and date
    public Cursor getSelectedCategoryAndDate(int yearInput, int monthInput, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (category == "All"){
            if (monthInput == 0) {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where date like " + "'" + yearInput + "/%'", null);
                return res;
            } else {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where date like " + "'" + yearInput + "/" + monthInput + "/%'", null);
                return res;
            }
        }
        else {
            if (monthInput == 0) {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where category = '" + category + "'" + " and date like " + "'" + yearInput + "/%'", null);
                return res;
            } else {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where category = '" + category + "'" + " and date like " + "'" + yearInput + "/" + monthInput + "/%'", null);
                return res;
            }
        }
    }








    //To get date for the filtering function for Earning
    public Cursor getEarningAllDate(int yearInput, int monthInput) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (monthInput == 0) {
            Cursor res = db.rawQuery("select * from " + TABLE_NAME_EARNING + " where date like " + "'" + yearInput + "/%'", null);
            return res;
        } else {
            Cursor res = db.rawQuery("select * from " + TABLE_NAME_EARNING + " where date like " + "'" + yearInput + "/" + monthInput + "/%'", null);
            return res;
        }
    }

    //To get date for the filtering function with day input
    public Cursor getEarningAllDateWithDay(int yearInput, int monthInput, int dayInput) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (monthInput == 0) {
            Cursor res = db.rawQuery("select * from " + TABLE_NAME_EARNING + " where date like " + "'" + yearInput + "/%/" + dayInput + "'", null);
            return res;
        } else {
            Cursor res = db.rawQuery("select * from " + TABLE_NAME_EARNING + " where date = " + "'" + yearInput + "/" + monthInput + "/" + dayInput + "'", null);
            return res;
        }
    }

    //To get date for the filtering function with day input
    public Cursor getEarningModeAndDateWithDay(int yearInput, int monthInput, int dayInput, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (category == "All") {
            if (monthInput == 0) {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME_EARNING + " where mode = '" + category + "'" + " and date like " + "'" + yearInput + "/%/" + dayInput + "'", null);
                return res;
            } else {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME_EARNING + " where mode = '" + category + "'" + " and date = " + "'" + yearInput + "/" + monthInput + "/" + dayInput + "'", null);
                return res;
            }
        } else {
            if (monthInput == 0) {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME_EARNING + " where mode = '" + category + "'" + " and date like " + "'" + yearInput + "/%/" + dayInput + "'", null);
                return res;
            } else {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME_EARNING + " where mode = '" + category + "'" + " and date = " + "'" + yearInput + "/" + monthInput + "/" + dayInput + "'", null);
                return res;
            }
        }
    }

    //To filter based on mode
    public Cursor getEarningMode(String category){
        SQLiteDatabase db = this.getWritableDatabase();
        if (category == "All"){
            Cursor res = db.rawQuery("select * from " + TABLE_NAME_EARNING,null);
            return res;
        }
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_EARNING + " where mode = '" + category + "'",null);
        return res;
    }

    //To filter based on category and date
    public Cursor getEarningModeAndDate(int yearInput, int monthInput, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (category == "All"){
            if (monthInput == 0) {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME_EARNING + " where date like " + "'" + yearInput + "/%'", null);
                return res;
            } else {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME_EARNING + " where date like " + "'" + yearInput + "/" + monthInput + "/%'", null);
                return res;
            }
        }
        else {
            if (monthInput == 0) {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME_EARNING + " where mode = '" + category + "'" + " and date like " + "'" + yearInput + "/%'", null);
                return res;
            } else {
                Cursor res = db.rawQuery("select * from " + TABLE_NAME_EARNING + " where mode = '" + category + "'" + " and date like " + "'" + yearInput + "/" + monthInput + "/%'", null);
                return res;
            }
        }
    }
}

