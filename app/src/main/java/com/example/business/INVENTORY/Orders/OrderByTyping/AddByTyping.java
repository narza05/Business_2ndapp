package com.example.business.INVENTORY.Orders.OrderByTyping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.business.INVENTORY.Orders.OrderByImage.AddByImage;
import com.example.business.R;

import java.util.List;

public class AddByTyping extends AppCompatActivity  {
    TextView name,description,category,price,quantity;
    Button addproductbutton, donewishlist;
    RecyclerView wishlist_bytyping;
    Toolbar toolbarBytyping;
    AddByImage addByImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_by_typing);
        toolbarBytyping = findViewById(R.id.toolbar_bytyping);
        setSupportActionBar(toolbarBytyping);

        addByImage = new AddByImage();
        name = findViewById(R.id.product_name_text);
        description = findViewById(R.id.product_description_text);
        category = findViewById(R.id.product_category_text);
        price = findViewById(R.id.product_price_text);
        quantity = findViewById(R.id.product_quantity_text);
        addproductbutton = findViewById(R.id.add_product_bytyping);
        donewishlist = findViewById(R.id.done_wishlist);
        wishlist_bytyping = findViewById(R.id.wishlist_bytyping);
        wishlist_bytyping.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DatabaseTyping databaseTyping = Room.databaseBuilder(getApplicationContext(),DatabaseTyping.class,"product_by_typing").allowMainThreadQueries().build();
        DAOtyping daOtyping = databaseTyping.daotyping();
        List<EntityTyping> list = daOtyping.getaddedproducts();
        AdapterTyping adapterTyping = new AdapterTyping(AddByTyping.this, list);
        wishlist_bytyping.setAdapter(adapterTyping);


        addproductbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productname = name.getText().toString();
                String productdescription = description.getText().toString();
                String productcategory = category.getText().toString();
                String productprice = price.getText().toString() + " Rupee";
                String productquantity = quantity.getText().toString();

                if (productname.isEmpty() && productquantity.isEmpty()){
                    Toast.makeText(AddByTyping.this, "Empty fields", Toast.LENGTH_SHORT).show();
                }

                else{
                    daOtyping.insertProduct(new EntityTyping(productname, productdescription, productcategory, productprice, productquantity));
                    Toast.makeText(AddByTyping.this, "Sent", Toast.LENGTH_SHORT).show();
                    List<EntityTyping> list = daOtyping.getaddedproducts();
                    AdapterTyping adapterTyping = new AdapterTyping(AddByTyping.this, list);
                    wishlist_bytyping.setAdapter(adapterTyping);

                    name.setText("");
                    description.setText("");
                    category.setText("");
                    price.setText("");
                    quantity.setText("");

//                daOtyping.deleteAll();
                }



            }
        });


        donewishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmwishlisttyping();

//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//                sendIntent.setType("text/plain");
//
//                Intent shareIntent = Intent.createChooser(sendIntent, null);
//                startActivity(shareIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inventory_menu_addproduct,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addprodudct_bytyping){
            return true;
        }
        else if (id == R.id.addprodudct_byimage){
            Intent intent = new Intent(AddByTyping.this, AddByImage.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.addprodudct_byaudio){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void opendialog(){
        UpdateWishlistItemDialog updateWishlistItemDialog = new UpdateWishlistItemDialog();
        updateWishlistItemDialog.show(getSupportFragmentManager(),"opendialog");
    }

    @Override
    protected void onResume() {
        super.onResume();
        DatabaseTyping databaseTyping = Room.databaseBuilder(getApplicationContext(),DatabaseTyping.class,"product_by_typing").allowMainThreadQueries().build();
        DAOtyping daOtyping = databaseTyping.daotyping();
        List<EntityTyping> list = daOtyping.getaddedproducts();
        AdapterTyping adapterTyping = new AdapterTyping(AddByTyping.this, list);
        wishlist_bytyping.setAdapter(adapterTyping);
    }

    public void confirmwishlisttyping(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(AddByTyping.this,CompleteWishlistByTyping.class);
                        startActivity(new Intent(AddByTyping.this,CompleteWishlistByTyping.class));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}