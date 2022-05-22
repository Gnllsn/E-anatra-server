package com.example.eanatramobile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.eanatramobile.R;
import com.google.gson.Gson;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        SharedPreferences memory = getApplicationContext().getSharedPreferences("data_user", Context.MODE_PRIVATE);
//        Gson gson = new Gson() ;
//        HashMap<String,Object> data_store = gson.fromJson(memory.getString("user",""),HashMap.class);
//        Log.d("MESSAGE "," \n\n\n\n "+data_store.get("user")+"");

    }
}