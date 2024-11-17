package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Food;

public interface IFoodRepository extends JpaRepository<Food, Integer>{
    //Basic CRUD operations are already added, no need for other methods
    Page<Food> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
