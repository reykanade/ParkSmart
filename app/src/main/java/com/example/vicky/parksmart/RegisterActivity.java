package com.example.vicky.parksmart;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.firebase.client.Firebase;


public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText name;
    private EditText password;
    private EditText rpassword;
    private EditText phoneNo;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_register);
        firebaseAuth=FirebaseAuth.getInstance();
        name=(EditText) findViewById(R.id.editText4);
        email=(EditText) findViewById(R.id.editText1);
        password=(EditText) findViewById(R.id.editText2);
        rpassword=(EditText) findViewById(R.id.editText3);
        phoneNo=(EditText) findViewById(R.id.editText5);
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    finish();
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    public void registerUser(View view){
        final String emailstring=email.getText().toString().trim();
        final String uName = name.getText().toString().trim();
        final String mobNo = phoneNo.getText().toString().trim();
        String passwordString=password.getText().toString().trim();
        String rpasswordString=rpassword.getText().toString().trim();
        if(TextUtils.isEmpty(emailstring)){
            Toast.makeText(this,"fields are empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passwordString)){
            Toast.makeText(this,"fields are empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(passwordString.equals(rpasswordString)==false){
            Toast.makeText(this,"passwords didnt match", Toast.LENGTH_SHORT).show();
            return;
        }
        if(passwordString.equals(rpasswordString)==true){
            firebaseAuth.createUserWithEmailAndPassword(emailstring,passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                        User user=new User(uName,mobNo,emailstring);
                        String s=emailstring;
                        s=s.substring(0,s.lastIndexOf("."));
                        Firebase firebase=new Firebase("https://parksmart-414fd.firebaseio.com/Users");
                        firebase=firebase.child(s);
                        firebase.setValue(user);
                        Toast.makeText(RegisterActivity.this,""+user.getName() +", You have successfully registered.",Toast.LENGTH_LONG).show();
                      //  startMainActivity();
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Registration not successful. Try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}