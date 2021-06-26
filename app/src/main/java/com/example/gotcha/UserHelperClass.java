package com.example.gotcha;

public class UserHelperClass {

    String Email,PhoneNo;

    public UserHelperClass(String email, String phoneNo) {
        Email = email;
        PhoneNo = phoneNo;
    }

    public UserHelperClass() {

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }
}
