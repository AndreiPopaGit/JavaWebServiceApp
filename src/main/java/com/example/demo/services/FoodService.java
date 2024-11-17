package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.example.demo.entity.DailyLog;
import com.example.demo.entity.Food;
import com.example.demo.entity.dto.MacroTotalFood;
import com.example.demo.repository.IDailyLogRepository;
import com.example.demo.repository.IFoodRepository;

@Component
public class FoodService implements IFoodService{

    private IFoodRepository foodRepository;
    private IDailyLogRepository dailyLogRepository;

    @Autowired
    public FoodService(IFoodRepository foodRepository, IDailyLogRepository dailyLogRepository) {
        this.foodRepository = foodRepository;
        this.dailyLogRepository = dailyLogRepository;
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @Override
    public Optional<Food> getFoodById(int id) {
        return foodRepository.findById(id);
    }

    @Override
   public Food saveFood(Food food)
   {
        return foodRepository.save(food);
   }

   @Override
   public void deleteFoodById(int id)
   {
    foodRepository.deleteById(id);
   }

@Override
public MacroTotalFood calculateTotalMacros(List<Integer> foodIds) {
    
    Integer totalProtein = 0;
    Integer totalCarbs = 0;
    Integer totalFats = 0;
    Integer totalCalories = 0;

    for(Integer foodId : foodIds)
    {
        Food food = foodRepository.findById(foodId).orElse(null);
        if(food != null)
        {
            totalCalories += food.getKcals();
            totalProtein += food.getProtein();
            totalCarbs += food.getCarbs();
            totalFats += food.getFats();
        }
    }

    return new MacroTotalFood(totalCalories, totalProtein, totalCarbs, totalFats);
}

@Override
public void addFoodToDailyLog(int foodId, int quantity) {
    Food food = foodRepository.findById(foodId).orElseThrow(() -> new IllegalArgumentException("Food not found"));
    DailyLog log = new DailyLog(food, quantity, LocalDate.now());
    dailyLogRepository.save(log);
}

    @Override
    public List<DailyLog> getDailyLog(LocalDate date) {
        return dailyLogRepository.findByDate(date);
    }
@Override
public Page<Food> getFoods(int page, int size) {
    return foodRepository.findAll(PageRequest.of(page, size));
}

@Override
public Page<Food> searchFoods(String name, int page, int size) {
    return foodRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page, size));
}

}
