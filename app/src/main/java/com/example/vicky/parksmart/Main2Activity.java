package com.example.vicky.parksmart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Main2Activity extends AppCompatActivity  {

    private String p_space;
    private Firebase mref,fref;
    private String button_id;
    private int block_id;
    private ImageButton imgButton1,imgButton2,imgButton3,imgButton4;
    private DatabaseHelper databaseHelper;
    private String uKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        databaseHelper =new DatabaseHelper(this);
        uKey=databaseHelper.getUserKey();
        Firebase.setAndroidContext(this);
        final List<Integer> arrayList=new ArrayList<>();
        Intent intent = getIntent();
        button_id=intent.getStringExtra("button_id");
        switch(button_id){
            case "com.example.vicky.parksmart:id/button5":
                block_id=0;
                break;
            case "com.example.vicky.parksmart:id/button6":
                block_id=4;
                break;
            case "com.example.vicky.parksmart:id/button7":
                block_id=8;
                break;
            case "com.example.vicky.parksmart:id/button8":
                block_id=12;
                break;
            default:
        }
        imgButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imgButton1.setOnClickListener(imgButtonHandler1);
        imgButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imgButton2.setOnClickListener(imgButtonHandler2);
        imgButton3 = (ImageButton) findViewById(R.id.imageButton3);
        imgButton3.setOnClickListener(imgButtonHandler3);
        imgButton4 = (ImageButton) findViewById(R.id.imageButton4);
        imgButton4.setOnClickListener(imgButtonHandler4);

        mref=new Firebase(getString(R.string.firebase_link)+"/UserBookings/"+uKey);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserBooking userBooking =dataSnapshot.getValue(UserBooking.class);
                if(userBooking.getHasbooked()){
                    imgButton1.setEnabled(false);
                    imgButton2.setEnabled(false);
                    imgButton3.setEnabled(false);
                    imgButton4.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //firebase connection and reading data from firebase
        final ProgressDialog loading= ProgressDialog.show(this, "Loading" ,"Please Wait...",false,false);
        Firebase.setAndroidContext(this);
        mref=new Firebase("https://parksmart-414fd.firebaseio.com/spotData");
        mref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.v("child", "child added ");
                Log.v("time",""+getTime());
               ParkingSlot p =dataSnapshot.getValue(ParkingSlot.class);
                updateSlot(p);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.v("child", "child changed ");
                ParkingSlot p =dataSnapshot.getValue(ParkingSlot.class);
                updateSlot(p);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.v("child", "child removed ");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.v("child", "child moved ");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.v("child", "child cancel ");
            }
            public void updateSlot(ParkingSlot slot )
            {
                int slotNumber=slot.getSlotnumber()-block_id;
                Log.v("v",slot.isOccupied()+"   "+slotNumber);
                if(slotNumber<=4)
                {
                switch (slotNumber)
                {
                    case 1:
                        if(!slot.isOccupied()){
                            imgButton1.setImageResource(R.drawable.blank);
                            imgButton1.setTag(R.drawable.blank);
                        }
                        else{
                            imgButton1.setImageResource(R.drawable.occupied);
                            imgButton1.setTag(R.drawable.occupied);
                            imgButton1.setEnabled(true);
                        }
                        break;
                    case 2:
                        if(!slot.isOccupied()){
                            imgButton2.setImageResource(R.drawable.blank);
                            imgButton2.setTag(R.drawable.blank);
                        }
                        else {
                            imgButton2.setImageResource(R.drawable.occupied);
                            imgButton2.setTag(R.drawable.occupied);
                            imgButton2.setEnabled(true);
                        }
                        break;
                    case 3:
                        if(!slot.isOccupied()){
                            imgButton3.setImageResource(R.drawable.blank);
                            imgButton3.setTag(R.drawable.blank);
                        }
                        else {
                            imgButton3.setImageResource(R.drawable.occupied);
                            imgButton3.setTag(R.drawable.occupied);
                            imgButton3.setEnabled(true);
                        }
                        break;
                    case 4:
                        if(!slot.isOccupied())
                        {
                            imgButton4.setImageResource(R.drawable.blank);
                            imgButton4.setTag(R.drawable.blank);
                        }
                        else {
                            imgButton4.setImageResource(R.drawable.occupied);
                            imgButton4.setTag(R.drawable.occupied);
                            imgButton4.setEnabled(true);
                        }
                        break;
                    default:
                }
                loading.dismiss();
            }}
        });
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
    private String getTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    View.OnClickListener imgButtonHandler1 = new View.OnClickListener() {

        public void onClick(View v) {
            if((Integer)imgButton1.getTag()==R.drawable.occupied){
                Toast.makeText(Main2Activity.this,"The spot is reserved",Toast.LENGTH_SHORT).show();
            }
            else if((Integer)imgButton1.getTag()==R.drawable.blank){
                    mref = mref.child("" + block_id);
                    String timeIn = getTime();
                    ParkingSlot pSlot = new ParkingSlot(block_id + 1, true, timeIn, " ", uKey);
                    mref.setValue(pSlot);
                    UserBooking ub=new UserBooking(true,block_id+1);
                    mref = new Firebase(getString(R.string.firebase_link) + "/UserBookings/" + uKey);
                    mref.setValue(ub);
                    imgButton1.setImageResource(R.drawable.occupied);
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
                mref=mref.child(""+(block_id+1));
                String timeIn=getTime();
                ParkingSlot pSlot =new ParkingSlot(block_id+2,true,timeIn," ",uKey);
                mref.setValue(pSlot);
                UserBooking ub=new UserBooking(true,block_id+2);
                mref = new Firebase(getString(R.string.firebase_link) + "/UserBookings/" + uKey);
                mref.setValue(ub);
                imgButton2.setImageResource(R.drawable.occupied);
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
                mref=mref.child(""+(block_id+2));
                String timeIn=getTime();
                ParkingSlot pSlot =new ParkingSlot(block_id+3,true,timeIn," ",uKey);
                mref.setValue(pSlot);
                UserBooking ub=new UserBooking(true,block_id+3);
                mref = new Firebase(getString(R.string.firebase_link) + "/UserBookings/" + uKey);
                mref.setValue(ub);
                imgButton3.setImageResource(R.drawable.occupied);
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
                mref=mref.child(""+(block_id+3));
                String timeIn=getTime();
                ParkingSlot pSlot =new ParkingSlot(block_id+4,true,timeIn," ",uKey);
                mref.setValue(pSlot);
                UserBooking ub=new UserBooking(true,block_id+4);
                mref = new Firebase(getString(R.string.firebase_link) + "/UserBookings/" + uKey);
                mref.setValue(ub);
                imgButton4.setImageResource(R.drawable.occupied);
                imgButton4.setTag(R.drawable.occupied);
            }
        }
    };
}