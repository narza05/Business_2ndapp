package com.example.business.HRchatbackend;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface chatDAO {
    @Insert
    void insert(chatUserEntity chatUserEntity);

    @Query("SELECT * FROM chatUserEntity")
    List<chatUserEntity> getchat();

}
