package com.example.vicky.parksmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class Main2Activity extends AppCompatActivity  {

    ImageButton imgButton1,imgButton2,imgButton3,imgButton4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imgButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imgButton1.setOnClickListener(imgButtonHandler1);
        imgButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imgButton2.setOnClickListener(imgButtonHandler2);
        imgButton3 = (ImageButton) findViewById(R.id.imageButton3);
        imgButton3.setOnClickListener(imgButtonHandler3);
        imgButton4 = (ImageButton) findViewById(R.id.imageButton4);
        imgButton4.setOnClickListener(imgButtonHandler4);
    }

    View.OnClickListener imgButtonHandler1 = new View.OnClickListener() {

        public void onClick(View v) {
              imgButton1.setImageResource(R.drawable.occupied);
        }
    };
    View.OnClickListener imgButtonHandler2 = new View.OnClickListener() {

        public void onClick(View v) {
            imgButton2.setImageResource(R.drawable.occupied);
        }
    };

    View.OnClickListener imgButtonHandler3 = new View.OnClickListener() {

        public void onClick(View v) {
            imgButton3.setImageResource(R.drawable.occupied);
        }
    };
    View.OnClickListener imgButtonHandler4 = new View.OnClickListener() {

        public void onClick(View v) {
            imgButton4.setImageResource(R.drawable.occupied);
        }
    };
}
