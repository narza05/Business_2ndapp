package com.example.business.HRemployeebackend;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {employeesUserEntity.class},version = 1)
public abstract class employeeDatabase extends RoomDatabase {
    public abstract employeeDAO employeeDAO();
}
