package com.example.demo.entity.dto;

public class MacroTotalFood {

    private int totalKcal;
    private int totalProtein;
    private int totalCarbs;
    private int totalFats;

    public MacroTotalFood(int totalKcal, int totalProtein, int totalCarbs, int totalFats) {
        this.totalKcal = totalKcal;
        this.totalProtein = totalProtein;
        this.totalCarbs = totalCarbs;
        this.totalFats = totalFats;
    }
    
    public int getTotalKcal() {
        return totalKcal;
    }
    public int getTotalProtein() {
        return totalProtein;
    }
    public int getTotalCarbs() {
        return totalCarbs;
    }
    public int getTotalFats() {
        return totalFats;
    }
    
}
