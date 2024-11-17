package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DailyLog;

import java.util.*;
import java.time.*;

public interface IDailyLogRepository extends JpaRepository <DailyLog, Integer> {
    List<DailyLog> findByDate(LocalDate date);
    List<DailyLog> findByDateAndFood_FoodID(LocalDate date, int FoodID);
}
