package com.example.myapplication;

public class PaymentProvoder {
    private String PayID , HouseID , date, phone;

    public PaymentProvoder(String payID, String houseID, String date, String phone) {
        PayID = payID;
        HouseID = houseID;
        this.date = date;
        this.phone = phone;
    }

    public PaymentProvoder() {
    }


    public String getPayID() {
        return PayID;
    }

    public void setPayID(String ppayID) {
        PayID = ppayID;
    }

    public String getHouseID() {
        return HouseID;
    }

    public void setHouseID(String houseID) {
        HouseID = houseID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
