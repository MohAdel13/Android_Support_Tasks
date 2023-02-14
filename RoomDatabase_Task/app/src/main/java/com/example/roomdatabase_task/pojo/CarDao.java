package com.example.roomdatabase_task.pojo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CarDao {

    @Insert
    void insertCar(CarModel c);

    @Query("SELECT * FROM CAR_TABLE")
    List<CarModel> getAllCars();

    @Update
    void updateCar(CarModel c);

    @Delete
    void deleteCar(CarModel c);
}
