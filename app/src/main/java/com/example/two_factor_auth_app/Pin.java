package com.example.two_factor_auth_app;

public class Pin {

    private int pin;
   private String phone_no;

    public Pin(){

    }

    public Pin(String phoneno, int pin1) {
        this.phone_no = phoneno;
        this.pin = pin1;
    }


    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}
