package com.example.demo.services;

import com.example.demo.entity.DailyLog;
import com.example.demo.entity.Food;
import com.example.demo.entity.dto.MacroTotalFood;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface IFoodService {
    
    List<Food> getAllFoods();
    Optional <Food> getFoodById(int id);
    Food saveFood (Food food);
    void deleteFoodById(int id);
    MacroTotalFood calculateTotalMacros(List<Integer> foodIds);
    void addFoodToDailyLog(int foodId, int quantity);
    List<DailyLog> getDailyLog(LocalDate date);
    Page<Food> getFoods(int page, int size);
    Page<Food> searchFoods(String Name, int page, int size);

}
