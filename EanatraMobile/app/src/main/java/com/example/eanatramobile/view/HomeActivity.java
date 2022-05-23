package com.example.eanatramobile.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eanatramobile.R;
import com.example.eanatramobile.modele.Cours;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        ListView listView = findViewById(R.id.listView);
//        List<Object> objectList = new ArrayList<>();
//        for(int i=0 ; i< 10  ; i++){
//            objectList.add("coucou de "+i);
//        }
//
//        ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<Object>(this,R.layout.cours,objectList);
//        listView.setAdapter(arrayAdapter);
     }

     private void creerList(){
         ArrayList<Cours> cours = new ArrayList<>();
//         for ()
     }
}