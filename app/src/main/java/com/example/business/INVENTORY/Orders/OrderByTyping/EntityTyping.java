package com.example.business.INVENTORY.Orders.OrderByTyping;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EntityTyping {

    @PrimaryKey
    @NonNull
    public String productname ;
    @ColumnInfo(name = "product_description")
    public String productdescription;
    @ColumnInfo(name = "product_category")
    public String productcategory;
    @ColumnInfo(name = "product_price")
    public String productprice;
    @ColumnInfo(name = "product_quantity")
    public String productquantity;

    public EntityTyping(String productname, String productdescription, String productcategory, String productprice, String productquantity) {
        this.productname = productname;
        this.productdescription = productdescription;
        this.productcategory = productcategory;
        this.productprice = productprice;
        this.productquantity = productquantity;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public String getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getProductquantity() {
        return productquantity;
    }

    public void setProductquantity(String productquantity) {
        this.productquantity = productquantity;
    }
}
