package com.example.business.INVENTORY.Orders.OrderByTyping;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAOtyping {
    @Insert
    void insertProduct(EntityTyping entityTyping);

    @Query("SELECT * FROM EntityTyping")
    List<EntityTyping> getaddedproducts();

//    @Query("DELETE FROM EntityTyping WHERE productname = :name")
//    void deleteAll(String name);

//    @Query("DELETE FROM EntityTyping WHERE productname = :name")
//    void deleteProduct(int name);

}
