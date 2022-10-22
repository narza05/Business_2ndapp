package com.example.business.INVENTORY.Orders.OrderByTyping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.business.R;

import java.util.List;

public class AddByTyping extends AppCompatActivity {
    TextView name,description,category,price,quantity;
    Button addproductbutton;
    RecyclerView wishlist_bytyping;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_by_typing);

        name = findViewById(R.id.product_name_text);
        description = findViewById(R.id.product_description_text);
        category = findViewById(R.id.product_category_text);
        price = findViewById(R.id.product_price_text);
        quantity = findViewById(R.id.product_quantity_text);
        addproductbutton = findViewById(R.id.add_product_button);
        wishlist_bytyping = findViewById(R.id.wishlist_bytyping);
        wishlist_bytyping.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        DatabaseTyping databaseTyping = Room.databaseBuilder(getApplicationContext(),DatabaseTyping.class,"product_by_typing").allowMainThreadQueries().build();
        DAOtyping daOtyping = databaseTyping.daotyping();

        List<EntityTyping> list = daOtyping.getaddedproducts();
//        AdapterTyping adapterTyping = new AdapterTyping(list);
//        wishlist_bytyping.setAdapter(adapterTyping);


        addproductbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productname = name.getText().toString();
                String productdescription = description.getText().toString();
                String productcategory = category.getText().toString();
                String productprice = price.getText().toString() + " Rupee";
                String productquantity = quantity.getText().toString();



                daOtyping.insertProduct(new EntityTyping(productname,productdescription,productcategory,productprice,productquantity));
                Toast.makeText(AddByTyping.this, "Sent", Toast.LENGTH_SHORT).show();
                List<EntityTyping> list = daOtyping.getaddedproducts();
                AdapterTyping adapterTyping = new AdapterTyping(list);
                wishlist_bytyping.setAdapter(adapterTyping);

//                daOtyping.deleteAll();




            }
        });

    }
}