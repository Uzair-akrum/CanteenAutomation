package com.example.canteenautomation.Models;

import java.io.Serializable;

public class Canteen implements Serializable {
   private String email,pass,managmentName,handler,mobile,noOfWorkers,address,canteenId,balance;

   public Canteen(){
       balance="0";
   }

    public Canteen(String email, String pass, String managmentName, String handler, String mobile, String noOfWorkers, String address, String canteenId, String balance) {
        this.email = email;
        this.pass = pass;
        this.managmentName = managmentName;
        this.handler = handler;
        this.mobile = mobile;
        this.noOfWorkers = noOfWorkers;
        this.address = address;
        this.canteenId = canteenId;
        this.balance = balance;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(String canteenId) {
        this.canteenId = canteenId;
    }

    public String getManagmentName() {
        return managmentName;
    }

    public void setManagmentName(String managmentName) {
        this.managmentName = managmentName;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNoOfWorkers() {
        return noOfWorkers;
    }

    public void setNoOfWorkers(String noOfWorkers) {
        this.noOfWorkers = noOfWorkers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
