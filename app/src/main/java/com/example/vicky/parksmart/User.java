package com.example.vicky.parksmart;

/**
 * Created by vicky on 02/04/2017.
 */

public class User {
    private  String name;
    private  String email;
    private  String mobileNo;

    public User(String un,String mobNo, String emailID)
    {
        email=emailID;
        mobileNo=mobNo;
        name=un;

    }
    public User(){}

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobNo) {
        mobileNo = mobNo;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String emailID) {
        email = emailID;
    }
    public  String getName() {
        return name;
    }
    public void setName(String user) { name = user;   }

}
