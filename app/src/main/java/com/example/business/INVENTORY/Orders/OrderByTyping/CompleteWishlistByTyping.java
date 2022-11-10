package com.example.business.INVENTORY.Orders.OrderByTyping;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.business.R;

import java.text.DateFormat;
import java.util.Calendar;

public class CompleteWishlistByTyping extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    ImageView duedate;
    TextView wishlistduedatetext;
    Button cancelwishlist, savewishlist, sharewishlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_wishlist_by_typing);
        duedate = findViewById(R.id.duedatewishlist);
        cancelwishlist = findViewById(R.id.cancelwishlistbutton);
        savewishlist = findViewById(R.id.savewishlistbutton);
        sharewishlist = findViewById(R.id.sharewishlistbutton);

        duedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment duedate = new WishlistDuedateFragment();
                duedate.show(getSupportFragmentManager(),"DueDatePicker");
            }
        });

        cancelwishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmactionwishlist();
            }
        });


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String duedate = DateFormat.getDateInstance().format(c.getTime());

        wishlistduedatetext = findViewById(R.id.duedatetext);
        wishlistduedatetext.setText(duedate);
    }

    public void confirmactionwishlist(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CompleteWishlistByTyping.this);
        builder.setTitle("Delete wishlist")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

}