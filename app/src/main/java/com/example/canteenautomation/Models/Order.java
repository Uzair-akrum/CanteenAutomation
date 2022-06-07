package com.example.canteenautomation.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private String orderId,status,studentID,foodId,dateOfOrder;

    public Order() {
    }

    public Order(String orderId, String status, String studentID, String foodId, String dateOfOrder) {
        this.orderId = orderId;
        this.status = status;
        this.studentID = studentID;
        this.foodId = foodId;
        this.dateOfOrder = dateOfOrder;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }
}
