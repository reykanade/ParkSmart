package com.example.vicky.parksmart;


public class ParkingSlot {

    private int slotnumber;
    private boolean isOccupied;
    private String TimeIn;
    private String TimeOut;
    private String username;

    public ParkingSlot(int slotnumber, boolean isOccupied, String timeIn, String timeOut, String username) {
        this.slotnumber = slotnumber;
        this.isOccupied = isOccupied;
        TimeIn = timeIn;
        TimeOut = timeOut;
        this.username = username;
    }
    public ParkingSlot(){}
    public int getSlotnumber() {
        return slotnumber;
    }


    public void setSlotnumber(int slotnumber) {
        this.slotnumber = slotnumber;
    }

    public String toString(){
        return ""+slotnumber+" "+isOccupied+ "  "+TimeIn+" "+ TimeOut+" "+ username;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String getTimeIn() {
        return TimeIn;
    }

    public void setTimeIn(String timeIn) {
        TimeIn = timeIn;
    }

    public String getTimeOut() {
        return TimeOut;
    }

    public void setTimeOut(String timeOut) {
        TimeOut = timeOut;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
