package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Food;
import com.example.demo.entity.dto.MacroTotalFood;
import com.example.demo.repository.IFoodRepository;
import com.example.demo.repository.IDailyLogRepository;
import com.example.demo.services.FoodService;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {

    @Mock
    private IFoodRepository foodRepository;

    @Mock
    private IDailyLogRepository dailyLogRepository;

    @InjectMocks
    private FoodService foodService;

    // Test for getAllFoods
    @Test
    void testGetAllFoods() {
        // Arrange
        List<Food> mockFoods = List.of(
                new Food(1, "Apple", 1, 2, 3, "Fruit"),
                new Food(2, "Banana", 2, 3, 1, "Fruit")
        );
        when(foodRepository.findAll()).thenReturn(mockFoods);

        // Act
        List<Food> result = foodService.getAllFoods();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Apple", result.get(0).getName());
        verify(foodRepository, times(1)).findAll();
    }

    // Test for getFoodById
    @Test
    void testGetFoodById_FoodExists() {
        // Arrange
        Food mockFood = new Food(1, "Apple", 1, 2, 3, "Fruit");
        when(foodRepository.findById(1)).thenReturn(Optional.of(mockFood));

        // Act
        Optional<Food> result = foodService.getFoodById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Apple", result.get().getName());
        verify(foodRepository, times(1)).findById(1);
    }

    @Test
    void testGetFoodById_FoodDoesNotExist() {
        // Arrange
        when(foodRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Optional<Food> result = foodService.getFoodById(1);

        // Assert
        assertTrue(result.isEmpty());
        verify(foodRepository, times(1)).findById(1);
    }

    // Test for calculateTotalMacros
    @Test
    void testCalculateTotalMacros() {
        // Arrange
        Food mockFood1 = new Food(1, "Apple", 1, 2, 3, "Fruit");
        mockFood1.setKcals(50);
        Food mockFood2 = new Food(2, "Banana", 2, 3, 1, "Fruit");
        mockFood2.setKcals(75);
        when(foodRepository.findById(1)).thenReturn(Optional.of(mockFood1));
        when(foodRepository.findById(2)).thenReturn(Optional.of(mockFood2));

        // Act
        MacroTotalFood result = foodService.calculateTotalMacros(List.of(1, 2));

        // Assert
        assertEquals(125, result.getTotalKcal());
        assertEquals(3, result.getTotalProtein());
        assertEquals(5, result.getTotalCarbs());
        assertEquals(4, result.getTotalFats());
        verify(foodRepository, times(2)).findById(anyInt());
    }

    // Test for addFoodToDailyLog
    @Test
    void testAddFoodToDailyLog_FoodExists() {
        // Arrange
        Food mockFood = new Food(1, "Apple", 1, 2, 3, "Fruit");
        when(foodRepository.findById(1)).thenReturn(Optional.of(mockFood));

        // Act
        foodService.addFoodToDailyLog(1, 100);

        // Assert
        verify(dailyLogRepository, times(1)).save(any());
    }

    @Test
    void testAddFoodToDailyLog_FoodDoesNotExist() {
        // Arrange
        when(foodRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            foodService.addFoodToDailyLog(1, 100);
        });

        assertEquals("Food not found", exception.getMessage());
        verify(dailyLogRepository, never()).save(any());
    }
}
