package com.example.vicky.parksmart;

/**
 * Created by vicky on 17/04/2017.
 */

public class UserBooking {
    private Boolean hasbooked;
    private int spotNo;

    public UserBooking(Boolean b,int spotNO) {
        this.hasbooked = b;
        this.spotNo=spotNO;
    }

    public UserBooking(){}

    public Boolean getHasbooked() {
        return hasbooked;
    }

    public void setHasbooked(Boolean hasbooked) {
        this.hasbooked = hasbooked;
    }

    public int getSpotNo() {
        return spotNo;
    }

    public void setSpotNo(int spotNo) {
        this.spotNo = spotNo;
    }
}