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

    @Query("DELETE FROM EntityTyping WHERE productname = :productname")
    void deleteall(String productname);

    @Query("UPDATE EntityTyping SET " +
            "product_description=:description ," +
            "product_category=:category ," +
            "product_price=:price ," +
            "product_quantity=:quantity " +
            "WHERE " +
            "productname = :name")
     void update(String name, String description, String category, String price, String quantity);

//    @Query("DELETE FROM EntityTyping WHERE productname = :name")
//    void deleteProduct(int name);

}
