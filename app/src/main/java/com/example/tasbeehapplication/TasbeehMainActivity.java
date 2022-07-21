package com.example.tasbeehapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TasbeehMainActivity extends AppCompatActivity {

    Button add,sub,reset;
    TextView counter;
    ImageView imageView;
    String image;
    String count2;
    DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasbeeh_main);
        add = findViewById(R.id.addcountbtn);
//        sub = findViewById(R.id.subcountbtn);
        reset = findViewById(R.id.resetbtn);
        imageView = findViewById(R.id.imageView);
        counter = findViewById(R.id.counttextview);
        Intent intent = getIntent();
        image = intent.getStringExtra("tasbeehimage");
        count2 = intent.getStringExtra("tasbeehcount");
        setCount();
 //       imageView.setImageIcon(Icon.createWithContentUri(intent.getStringExtra("tasbeehimage")));
        setImage();
    }

    private void setCount() {
       counter.setText(count2);
    }

    private void setImage() {
        int image2 = Integer.parseInt(image);
        imageView.setImageResource(image2);
    }

    public void addCount(View view) {
        int count = Integer.parseInt(counter.getText().toString().trim());
        count = count+1;
        Tasbeeh tasbeeh = new Tasbeeh(Integer.parseInt(image),String.valueOf(count));
        databaseQueryClass.updateTasbeehCounter(tasbeeh);
        counter.setText(String.valueOf(count));
    }


    public void resetCount(View view) {
        int count = 0;
        Tasbeeh tasbeeh = new Tasbeeh(Integer.parseInt(image),String.valueOf(count));
        databaseQueryClass.updateTasbeehCounter(tasbeeh);
        counter.setText(String.valueOf(count));
    }
}