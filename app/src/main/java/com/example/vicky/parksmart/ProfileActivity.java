package com.example.vicky.parksmart;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView name;
    private TextView username;
    private TextView contact;
    private Firebase ref;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name=(TextView)findViewById(R.id.textView5);
        username=(TextView)findViewById(R.id.textView6);
        contact=(TextView)findViewById(R.id.textView7);

        databaseHelper=new DatabaseHelper(this);
        Cursor cursor=databaseHelper.getAllData();
        cursor.moveToNext();
        String userKey=cursor.getString(1);

        Firebase.setAndroidContext(this);

        ref=new Firebase(getString(R.string.firebase_link)+"/Users/"+userKey);
        Log.v("E_value","Data:xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx "+userKey);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user=dataSnapshot.getValue(User.class);
                if(user!=null) {
                    Log.v("E_value", "Data: xxxxxx" + user.getName());
                    name.setText("Name: " + user.getName());
                    username.setText("UserName: " + user.getEmail());
                    contact.setText("Contact No.: "+user.getMobileNo());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
