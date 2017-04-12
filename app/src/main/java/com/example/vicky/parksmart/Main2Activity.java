package com.example.vicky.parksmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class Main2Activity extends AppCompatActivity  {

    private String p_space;
    private Firebase mref;
    private int no[]=new int[16];
    private String button_id;
    private int block_id;
    private ImageButton imgButton1,imgButton2,imgButton3,imgButton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        button_id=intent.getStringExtra("button_id");
        switch(button_id){
            case "com.example.vicky.parksmart:id/button5":
                block_id=1;
                break;
            case "com.example.vicky.parksmart:id/button6":
                block_id=5;
                break;
            case "com.example.vicky.parksmart:id/button7":
                block_id=9;
                break;
            case "com.example.vicky.parksmart:id/button8":
                block_id=13;
                break;
        }

        //firebase connection and reading data from firebase
        Firebase.setAndroidContext(this);
        mref=new Firebase("https://parksmart-414fd.firebaseio.com/sensorData");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("E_value", "insideeeeeeeeeeeeeeeeeeeeee");
                p_space=dataSnapshot.getValue(String.class);

                //the following code converts p_space string to integer array no[]
                Log.v("E_value", "firebase dataaaaaaaaaaaaaaaaaaaaaaa="+p_space);
                char []c=p_space.toCharArray();
                int j=0;
                String str="";
                for(int i=0;i<c.length;i++){
                    while(i<c.length&&c[i]!=','){
                        str=str+Character.toString(c[i]);
                        i++;
                    }
                    no[j]=Integer.parseInt(str);
                    str="";
                }

                //
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


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