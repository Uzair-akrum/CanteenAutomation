package com.example.canteenautomation.Models;

import java.io.Serializable;

public class Student implements Serializable {
    private String email,paasword,name,gender,department,program,mobile,address,stuentId,balance;

    public Student() {
    }

    public Student(String email, String paasword, String name, String gender, String department, String program, String mobile, String address, String stuentId, String balance) {
        this.email = email;
        this.paasword = paasword;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.program = program;
        this.mobile = mobile;
        this.address = address;
        this.stuentId = stuentId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaasword() {
        return paasword;
    }

    public void setPaasword(String paasword) {
        this.paasword = paasword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getStuentId() {
        return stuentId;
    }

    public void setStuentId(String stuentId) {
        this.stuentId = stuentId;
    }
}
