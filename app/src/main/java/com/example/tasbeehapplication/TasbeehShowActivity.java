package com.example.tasbeehapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

public class TasbeehShowActivity extends AppCompatActivity{ //implements View.OnClickListener {
    RecyclerView recyclerView;
    ArrayList<Tasbeeh> tasbeehArrayList = new ArrayList<>();
    TasbeehShowRecyclerViewAdapter tasbeehShowRecyclerViewAdapter;
    private DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasbeeh_show);
        Logger.addLogAdapter(new AndroidLogAdapter());
        recyclerView = findViewById(R.id.tasbeehshow_recyclerview);
        tasbeehShowRecyclerViewAdapter = new TasbeehShowRecyclerViewAdapter(tasbeehArrayList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tasbeehShowRecyclerViewAdapter);
 //       recyclerView.setOnClickListener(this);
        insert();
    }

    private void insert() {
        Tasbeeh listOfTasbeeh = new Tasbeeh(R.drawable.alhumdulilah);
        tasbeehArrayList.add(listOfTasbeeh);
        databaseQueryClass.insertTasbeeh(listOfTasbeeh);

        listOfTasbeeh = new Tasbeeh(R.drawable.allahhoakbar);
        tasbeehArrayList.add(listOfTasbeeh);
        databaseQueryClass.insertTasbeeh(listOfTasbeeh);

        listOfTasbeeh = new Tasbeeh(R.drawable.subhanallah);
        tasbeehArrayList.add(listOfTasbeeh);
        databaseQueryClass.insertTasbeeh(listOfTasbeeh);

        listOfTasbeeh = new Tasbeeh(R.drawable.austagfirullah);
        tasbeehArrayList.add(listOfTasbeeh);
        databaseQueryClass.insertTasbeeh(listOfTasbeeh);

    }

//    @Override
//    public void onClick(View view) {
//        Intent intent = new Intent(this,TasbeehMainActivity.class);
////        intent.putExtra("tasbeehimage", tasbeeh.getImage());
////        intent.putExtra("tasbeehcount", tasbeeh.getCount());
//
//        startActivity(intent);
//    }
}