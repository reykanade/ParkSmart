package com.example.vicky.parksmart;

/**
 * Created by vicky on 17/04/2017.
 */

public class UserLog {
    private int spotNo;
    private String timeIn;
    private String timeOut;
    private String totalTime;


    public UserLog(int spotNo, String timeIn, String timeOut, String totalTime) {
        this.spotNo = spotNo;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.totalTime = totalTime;
    }

    public UserLog(){}

    public int getSpotNo() {
        return spotNo;
    }

    public void setSpotNo(int spotNo) {
        this.spotNo = spotNo;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }
}
