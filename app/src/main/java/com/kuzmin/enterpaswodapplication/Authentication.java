package com.kuzmin.enterpaswodapplication;

import static com.kuzmin.enterpaswodapplication.DBHelper.NAME_USER;
import static com.kuzmin.enterpaswodapplication.DBHelper.PASS_USER;

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

public class Authentication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_name);
        TextView textView3=(TextView)findViewById(R.id.textView3);
        EditText editName=(EditText)findViewById(R.id.editTextPersonName);
        EditText editPassword=(EditText)findViewById(R.id.editTextPassword);
        Button button_enter=(Button) findViewById(R.id.button_enter);


        DBHelper dbHelper=new DBHelper(this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor;
       // db.delete(DBHelper.USER_TABLE, null, null);

        cursor = db.query("users", null, null, null, null, null, null);
        if(cursor.moveToNext()) {
            int index_name = cursor.getColumnIndex("name");
            int index_email = cursor.getColumnIndex("email");
            int index_pass = cursor.getColumnIndex("pass");

            do {
                Log.d("myLog", "запись в базе " + cursor.getString(index_name) + " " +
                        cursor.getString(index_email) + " " + cursor.getInt(index_pass));
                textView3.append(cursor.getString(index_name));
                textView3.append("\n");
            } while (cursor.moveToNext());
        }
        Log.d("myLog", "всего записей в таблице = "+ cursor.getCount());
        cursor.close();

        button_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                try {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();


                    Cursor cursor = db.query(DBHelper.USER_TABLE, null, NAME_USER+"=?", new String[]{editName.getText().toString()}, null, null, null);





                        Log.d("myLog", "запись в базе " + cursor.getCount());
                        int ind_name=cursor.getPosition();
                    if (cursor.getCount() != 0) {
                        cursor=null;
                        cursor=db.query(DBHelper.USER_TABLE, null, PASS_USER+"=?", new String[]{editPassword.getText().toString()}, null, null, null);
                                if(cursor.getCount()!=0) {
                                    if (cursor.getPosition() == ind_name) {
                                        Log.d("myLog", "запись в базе " + cursor.getPosition()+" "+ ind_name);
                                        Toast.makeText(Authentication.this, "Пользователь определен.", Toast.LENGTH_SHORT).show();
                                        cursor.close();
                                        Intent intent = new Intent(Authentication.this, Service.class);
                                        startActivity(intent); finish();
                                    } else {

                                        Toast.makeText(Authentication.this, "Не правильная пара логин пароль. Поропробуте еще раз", Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(Authentication.this, "Не правильная пара логин пароль. Поропробуте еще раз", Toast.LENGTH_SHORT).show();
                                }
                    } else {
                        cursor.close();
                        Toast.makeText(Authentication.this, "Пользователь не определен. Пройдите регистрацию и поропробуте еще раз", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Authentication.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (Exception e) {
                    Toast.makeText(Authentication.this, "Не удалось открыть базу данных.", Toast.LENGTH_SHORT).show();
                }


            }

        });
        dbHelper.close();


    }
    @Override
    public void  onBackPressed (){
        try {
            Intent intent = new Intent(Authentication.this, MainActivity.class);
            startActivity(intent);
            finish();

        } catch (Exception e) {

        }
    }
}
