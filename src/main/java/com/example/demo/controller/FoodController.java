package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.DailyLog;
import com.example.demo.entity.Food;
import com.example.demo.entity.dto.MacroTotalFood;
import com.example.demo.entity.dto.PaginatedResponse;
import com.example.demo.services.IFoodService;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    IFoodService foodService;

    @Autowired
    public FoodController(IFoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable int id) {
        return foodService.getFoodById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Food createFood(@RequestBody Food food) {
        return foodService.saveFood(food);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable int id) {
        foodService.deleteFoodById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/totalMacro")
    public ResponseEntity<MacroTotalFood> calculateTotalMacros(@RequestBody List<Integer> foodIds) {
        MacroTotalFood totals = foodService.calculateTotalMacros(foodIds);
        return ResponseEntity.ok(totals);
    }

    @PostMapping("/dailyLog")
    public ResponseEntity<String> addFoodToDailyLog(@RequestParam int foodId, @RequestParam int quantity) {
        foodService.addFoodToDailyLog(foodId, quantity);
        return ResponseEntity.ok("Food added to daily log succsessfully");
    }

    @GetMapping("/dailyLog")
    public ResponseEntity<List<DailyLog>> getDailyLog(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<DailyLog> dailyLog = foodService.getDailyLog(date);
        return ResponseEntity.ok(dailyLog);
    }
    // @GetMapping("/foods")
    // public Page<Food> getFoods(
    // @RequestParam(defaultValue = "0") int page,
    // @RequestParam(defaultValue = "5") int size){
    // return foodService.getFoods(page,size);
    // }

    // @GetMapping("/foods")
    // public ResponseEntity<Page<Food>> getFoods(
    // @RequestParam(required = false, defaultValue = "") String Name, // Use
    // lowercase 'name'
    // @RequestParam(defaultValue = "0") int page,
    // @RequestParam(defaultValue = "20") int size) {
    // Page<Food> foods = foodService.searchFoods(Name, page, size); // Pass 'name'
    // to the service
    // return ResponseEntity.ok(foods);
    // }

    @GetMapping("/foods")
    public ResponseEntity<PaginatedResponse<Food>> getFoods(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<Food> foods = foodService.searchFoods(name, page, size);

        PaginatedResponse<Food> response = new PaginatedResponse<>();
        response.setContent(foods.getContent());
        response.setPage(foods.getNumber());
        response.setSize(foods.getSize());
        response.setTotalElements(foods.getTotalElements());
        response.setTotalPages(foods.getTotalPages());
        response.setLast(foods.isLast());

        return ResponseEntity.ok(response);
    }

}
