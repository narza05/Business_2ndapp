package com.example.business.HRchatbackend;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {chatUserEntity.class},version = 1)
public abstract class chatDatabase extends RoomDatabase {
    public abstract chatDAO chatDAO();
}
