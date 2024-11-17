package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Food")
public class Food {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="foodID")
    private int foodID;

    @Column(name="Name")
    private String name;

    @Column(name="Kcals")
    private Integer Kcals;

    @Column(name="Protein")
    private int Protein;

    @Column(name="Carbs")
    private int Carbs;

    @Column(name="Fats")
    private int Fats;

    @Column(name="Category")
    private String Category;

    public int getFoodID() {
        return foodID;
    }

    public String getName() {
        return name;
    }

    public int getProtein() {
        return Protein;
    }

    public int getCarbs() {
        return Carbs;
    }

    public int getFats() {
        return Fats;
    }

    public String getCategory() {
        return Category;
    }

    public void setfoodID(int foodID) {
        this.foodID = foodID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProtein(int protein) {
        Protein = protein;
    }

    public void setCarbs(int carbs) {
        Carbs = carbs;
    }

    public void setFats(int fats) {
        Fats = fats;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public Integer getKcals() {
        return Kcals;
    }

    public void setKcals(Integer kcals) {
        Kcals = kcals;
    }

    public Food() {
        super();
    }

    public Food(int foodID, String name, int protein, int carbs, int fats, String category) {
        this.foodID = foodID;
        this.name = name;
        Protein = protein;
        Carbs = carbs;
        Fats = fats;
        Category = category;
    }

    @Override
    public String toString() {
        return "Food [FoodID=" + foodID + ", Name=" + name + ", Protein=" + Protein + ", Carbs=" + Carbs + ", Fats="
                + Fats + ", Category=" + Category + "]";
    }
        

}
