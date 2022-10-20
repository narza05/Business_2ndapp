package com.example.business.HRemployeebackend;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface employeeDAO {
    @Insert
    void insert(employeesUserEntity employees);

    @Query("SELECT EXISTS (SELECT * FROM employeesUserEntity WHERE employeeId = :empId)")
    Boolean is_exists(int empId);

    @Query("SELECT * FROM employeesUserEntity")
    List<employeesUserEntity> getalluser();

    @Query("DELETE FROM employeesUserEntity WHERE employeeId = :empId")
    void delete(int empId);


}
