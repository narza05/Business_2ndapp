package com.example.business.INVENTORY.Orders.OrderByTyping;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = EntityTyping.class,version = 1)
public abstract class DatabaseTyping extends RoomDatabase {
    public abstract DAOtyping daotyping();
}
