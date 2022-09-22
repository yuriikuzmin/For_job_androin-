package com.kuzmin.enterpaswodapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static String NAME_DATABASE="db_users";
    public static int VERSION_DATABASE=1;

    public static String USER_TABLE="users";
    public static String NAME_USER="name";
    public static String USER_ID="id";
    public static String EMAIL_USER="email";
    public static String PASS_USER="pass";

    public DBHelper(@Nullable Context context) {
        super(context, NAME_DATABASE, null, VERSION_DATABASE);
    }
    public static final String DATABASE_CREATE_USER=" create table "+ USER_TABLE + " ( "+ USER_ID +
            " integer primary key, "+ NAME_USER +" text, "+ EMAIL_USER + " text, "+ PASS_USER +" text " + " );";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DATABASE_CREATE_USER);
        onCreate(db);
    }
}
