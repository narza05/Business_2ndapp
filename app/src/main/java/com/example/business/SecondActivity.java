package com.example.business;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.business.INVENTORY.allProducts.add_products;

public class SecondActivity extends AppCompatActivity {

    private Button hr_Button,inventory_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        hr_Button = findViewById(R.id.hrBtn);
        inventory_button = findViewById(R.id.InventoryBtn);
        hr_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, HrTabbedActivity.class);
                startActivity(intent);
            }
        });

        inventory_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, add_products.class);
                startActivity(intent);
            }
        });

    }
}