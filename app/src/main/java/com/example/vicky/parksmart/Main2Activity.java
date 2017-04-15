package com.example.vicky.parksmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Main2Activity extends AppCompatActivity  {

    private String p_space;
    private Firebase mref,fref;
    private String button_id;
    private int block_id;
    private ImageButton imgButton1,imgButton2,imgButton3,imgButton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final List<Integer> arrayList=new ArrayList<>();
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
        mref=new Firebase("https://parksmart-414fd.firebaseio.com/spotData");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                p_space=dataSnapshot.getValue(String.class);
                arrayList.clear();

                //the following code converts p_space string to integer array no[]
                Log.v("E_value", "firebase dataaaaaaaaaaaaaaaaaaaaaaa=");
                char []c=p_space.toCharArray();
                String str="";
                for(int i=0;i<c.length;i++){
                    while(i<c.length&&c[i]!=','){
                        str=str+Character.toString(c[i]);
                        i++;
                    }
                    arrayList.add(Integer.parseInt(str));
                    Log.v("E_value", "true of falseeeeeeeeeeeeee="+Integer.parseInt(str));
                    str="";
                }
                //now we will set the image of image button depending the availability of space
                Log.v("E_value", "true of falseeeeeeeeeeeeee=");
                if(arrayList.contains(block_id)){
                    imgButton1.setImageResource(R.drawable.blank);
                    imgButton1.setTag(R.drawable.blank);
                }
                else{
                    imgButton1.setImageResource(R.drawable.occupied);
                    imgButton1.setTag(R.drawable.occupied);
                }
                if(arrayList.contains(block_id+1)){
                    imgButton2.setImageResource(R.drawable.blank);
                    imgButton2.setTag(R.drawable.blank);
                }
                else {
                    imgButton2.setImageResource(R.drawable.occupied);
                    imgButton2.setTag(R.drawable.occupied);
                }
                if(arrayList.contains(block_id+2)){
                    imgButton3.setImageResource(R.drawable.blank);
                    imgButton3.setTag(R.drawable.blank);
                }
                else {
                    imgButton3.setImageResource(R.drawable.occupied);
                    imgButton3.setTag(R.drawable.occupied);
                }
                if(arrayList.contains(block_id+3)){
                    imgButton4.setImageResource(R.drawable.blank);
                    imgButton4.setTag(R.drawable.blank);
                }
                else {
                    imgButton4.setImageResource(R.drawable.occupied);
                    imgButton4.setTag(R.drawable.occupied);
                }
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    View.OnClickListener imgButtonHandler1 = new View.OnClickListener() {

        public void onClick(View v) {
            if((Integer)imgButton1.getTag()==R.drawable.occupied){
                Toast.makeText(Main2Activity.this,"The spot is reserved",Toast.LENGTH_SHORT).show();
            }
            else if((Integer)imgButton1.getTag()==R.drawable.blank){
                imgButton1.setImageResource(R.drawable.occupied);
                mref.push().setValue(block_id);
                imgButton1.setTag(R.drawable.occupied);
            }
        }
    };
    View.OnClickListener imgButtonHandler2 = new View.OnClickListener() {

        public void onClick(View v) {
            if((Integer)imgButton2.getTag()==R.drawable.occupied){
                Toast.makeText(Main2Activity.this,"The spot is reserved",Toast.LENGTH_SHORT).show();
            }
            else if((Integer)imgButton2.getTag()==R.drawable.blank){
                imgButton2.setImageResource(R.drawable.occupied);
                mref.push().setValue(block_id+1);
                imgButton2.setTag(R.drawable.occupied);
            }
        }
    };
    View.OnClickListener imgButtonHandler3 = new View.OnClickListener() {
        public void onClick(View v) {
            if((Integer)imgButton3.getTag()==R.drawable.occupied){
                Toast.makeText(Main2Activity.this,"The spot is reserved",Toast.LENGTH_SHORT).show();
            }
            else if((Integer)imgButton3.getTag()==R.drawable.blank){
                imgButton3.setImageResource(R.drawable.occupied);
                mref.push().setValue(block_id+2);
                imgButton3.setTag(R.drawable.occupied);
            }
        }
    };
    View.OnClickListener imgButtonHandler4 = new View.OnClickListener() {

        public void onClick(View v) {
            if((Integer)imgButton4.getTag()==R.drawable.occupied){
                Toast.makeText(Main2Activity.this,"The spot is reserved",Toast.LENGTH_SHORT).show();
            }
            else if((Integer)imgButton4.getTag()==R.drawable.blank){
                imgButton4.setImageResource(R.drawable.occupied);
                mref.push().setValue(block_id+3);
                imgButton4.setTag(R.drawable.occupied);
            }
        }
    };
}