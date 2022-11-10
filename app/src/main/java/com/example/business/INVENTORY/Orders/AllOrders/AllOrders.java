package com.example.business.INVENTORY.Orders.AllOrders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.business.INVENTORY.Orders.OrderByImage.AddByImage;
import com.example.business.INVENTORY.Orders.OrderByTyping.AddByTyping;
import com.example.business.R;

public class AllOrders extends AppCompatActivity {
    Toolbar toolbarAllorders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders);

        toolbarAllorders = findViewById(R.id.toolbar_allorders);
        setSupportActionBar(toolbarAllorders);



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
            Intent intent = new Intent(AllOrders.this, AddByTyping.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.addprodudct_byimage){
            Intent intent = new Intent(AllOrders.this, AddByImage.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.addprodudct_byaudio){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}