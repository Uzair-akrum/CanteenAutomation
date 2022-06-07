package com.example.canteenautomation.Models;

import java.io.Serializable;

public class FoodItem implements Serializable {
    private String  name,description,cost,time,foodId;

    public FoodItem(){
        //Empty constructor
    }

    public FoodItem(String name, String description, String cost, String time, String foodId) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.time = time;
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
