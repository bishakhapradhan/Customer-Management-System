package com.example.bit6thsem.CustomerModel;

import java.io.Serializable;

public class CustomerData implements Serializable {
    private String firstname;
    private String lastname;
    private String mobile_email;
    private String password;
    private String year;
    private String month;
    private String day;
    private String gender;
    private String country;


    public CustomerData(String firstname,
                        String lastname,
                        String mobile_email,
                        String password,
                        String year,
                        String month,
                        String day,
                        String gender,
                        String country) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile_email = mobile_email;
        this.password = password;
        this.year = year;
        this.month = month;
        this.day = day;
        this.gender = gender;
        this.country = country;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile_email() {
        return mobile_email;
    }

    public void setMobile_email(String mobile_email) {
        this.mobile_email = mobile_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
