package com.kuzmin.enterpaswodapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.RemoteMessage;

public class Service extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_push);


    }


    @Override
    public void  onBackPressed (){
        try {
            Intent intent = new Intent(Service.this, MainActivity.class);
            startActivity(intent);
            finish();

        } catch (Exception e) {

        }
    }
}
