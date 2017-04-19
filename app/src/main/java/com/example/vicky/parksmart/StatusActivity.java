package com.example.vicky.parksmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatusActivity extends AppCompatActivity {

    private TextView parkingslot;
    private TextView date;
    private TextView time;
    private Firebase mref;
    private String uKey;
    DatabaseHelper databaseHelper;
    private Boolean bool;
    private UserBooking ub;
    private ParkingSlot ps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        databaseHelper=new DatabaseHelper(this);
        uKey=databaseHelper.getUserKey();

        parkingslot=(TextView) findViewById(R.id.textView3);
        date=(TextView) findViewById(R.id.textView10);
        time=(TextView) findViewById(R.id.textView11);

        Firebase.setAndroidContext(this);

        mref=new Firebase(getString(R.string.firebase_link)+"/UserBookings/"+uKey);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("data","" +dataSnapshot.getValue());
                ub=dataSnapshot.getValue(UserBooking.class);
                if(ub.getHasbooked()){
                    Firebase fb=new Firebase(getString(R.string.firebase_link)+"/spotData/"+(ub.getSpotNo()-1));
                    fb.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ps=dataSnapshot.getValue(ParkingSlot.class);
                            if(ps.isOccupied()) {
                                parkingslot.setText(getString(R.string.parking_slot_no) + " " + ps.getSlotnumber());
                                String s = ps.getTimeIn();
                                String dt = s.substring(0, s.lastIndexOf(" "));
                                String tm = s.substring(s.lastIndexOf(" "), s.length());
                                date.setText(getString(R.string.date) + " " + dt);
                                time.setText(getString(R.string.time) + " " + tm);
                            }
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }
                else{
                    Log.v("data","in else");
                    parkingslot.setText("");
                    time.setText("");
                    date.setText("");
                    date.setText(getString(R.string.Parking_status));
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
    private String getTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
    public void removeVehicle(View view){
        if(ub.getHasbooked()) {
            Firebase fbase=new Firebase(getString(R.string.firebase_link) +"/UserBookings/"+uKey);
            UserBooking userBooking=new UserBooking(false,0);
            fbase.setValue(userBooking);
            fbase = new Firebase(getString(R.string.firebase_link) + "/spotData/" + (ub.getSpotNo() - 1));
            ParkingSlot pkSlot= new ParkingSlot((ub.getSpotNo()),false,null,null,null);
            fbase.setValue(pkSlot);
            String timeOut=getTime();
            UserLog ul=new UserLog(ps.getSlotnumber(),ps.getTimeIn(),timeOut,getTimeDifference(ps.getTimeIn(),timeOut));
            fbase=new Firebase(getString(R.string.firebase_link)+"/UserLogs/"+uKey);
            fbase.push().setValue(ul);

        }
    }
    public String getTimeDifference(String tIn,String tOut){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
        String s="";
        try {

            Date date1 = simpleDateFormat.parse(tIn);
            Date date2 = simpleDateFormat.parse(tOut);

            s= printDifference(date1, date2);

        } catch (ParseException e) {
            e.printStackTrace();

        }
        return s;
    }
    public String printDifference(Date startDate, Date endDate){
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;

        String s=elapsedDays+"days"+elapsedHours+"hours"+elapsedMinutes+"minutes";
        return  s;
    }
}