package com.riinvest.helloworldriinvest_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context,
                "RiinvestDB", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Users(" +
                "Id integer primary key autoincrement," +
                Users.Name + " text," +
                Users.Surname + " text," +
                "Email text,"+
                "Password text)");

        ContentValues cv = new ContentValues();
        cv.put("Name", "Filan");
        cv.put("Surname", "Fisteku");
        cv.put("Email", "filan@gmail.com");
        cv.put("Password", "123");

        sqLiteDatabase.insert("Users", null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        ContentValues cv = new ContentValues();
//        cv.put("Name", "Filan");
//        cv.put("Surname", "Fisteku");
//        cv.put("Email", "filan@gmail.com");
//        cv.put("Password", "123");
//
//        sqLiteDatabase.insert("Users", null, cv);
        //sqLiteDatabase.execSQL("CREATE UNIQUE INDEX ux_username ON Users(Email);");
    }
}

