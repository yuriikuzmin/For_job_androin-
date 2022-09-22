package com.kuzmin.enterpaswodapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registration);

        int i=0;

        EditText name = (EditText) findViewById(R.id.editTextName2);
        EditText email = (EditText) findViewById(R.id.editTextEmail);
        EditText pass = (EditText) findViewById(R.id.editTextPassword2);
        Button button4 = (Button) findViewById(R.id.button4);
        TextView text_summary = (TextView) findViewById(R.id.text_summary);

        DBHelper dbHelper = new DBHelper(this);//создает базу данных и теблицу, заполняет ее контекстом
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor = db.query("users", null, null, null, null, null, null);
        i=cursor.getCount();
        Log.d("myLog", "в начале i = " + i);
        cursor.close();

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                 SQLiteDatabase db = dbHelper.getWritableDatabase(); //открываем базу данных для записи и считывания

                ContentValues cv = new ContentValues();
                cv.put(DBHelper.NAME_USER, name.getText().toString());
                cv.put(DBHelper.EMAIL_USER, email.getText().toString());
                cv.put(DBHelper.PASS_USER, pass.getText().toString());

                db.insert(DBHelper.USER_TABLE, null, cv);// запись в таблицу базы данных параметров пользователя
                Toast.makeText(Registration.this, "Регистрация успешная", Toast.LENGTH_SHORT).show();

                Cursor cursor = db.query("users", null, null, null, null, null, null);
                int i=cursor.getCount();
                Log.d("myLog", "после записи i = " + i);

                cursor.close();

                Intent intent=new Intent(Registration.this, MainActivity.class);
                startActivity(intent); finish();

                } catch (Exception e){ Toast.makeText(Registration.this, "Регистрация не прошла. Повторить еще раз", Toast.LENGTH_SHORT).show();}

            }
        });
        dbHelper.close();
    }

    @Override
    public void  onBackPressed (){
        try {
            Intent intent = new Intent(Registration.this, MainActivity.class);
            startActivity(intent);
            finish();

        } catch (Exception e) {

        }
    }

}
