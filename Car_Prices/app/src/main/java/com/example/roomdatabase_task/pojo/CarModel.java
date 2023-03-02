package com.example.roomdatabase_task.pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CAR_TABLE")
public class CarModel {
  @PrimaryKey(autoGenerate = true)
  public int id;

  @ColumnInfo(name = "TYPE_COLUMN")
    public String type;

  @ColumnInfo(name = "MODEL_COLUMN")
    public String model;

  @ColumnInfo(name = "COLOR_COLUMN")
    public String color;

  @ColumnInfo(name = "PRICE_COLUMN")
    public float price;
}
