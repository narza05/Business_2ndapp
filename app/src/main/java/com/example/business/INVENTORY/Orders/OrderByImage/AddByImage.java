package com.example.business.INVENTORY.Orders.OrderByImage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.business.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AddByImage extends AppCompatActivity {
    TextView name,price,quantity;
    Button addproductbutton;
    Button donebutton;
    ImageView imageByimage;
    RecyclerView wishlist_byimage;
    Toolbar toolbarByimage;
    Uri uri;
    Bitmap imagetostore;
    DatabaseImage DB;

    int SELECT_IMAGE_CODE = 1;
    int PICK_IMAGE = 100;


    int STORAGE_PERMISSION_CODE = 23;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_by_image);
        toolbarByimage = findViewById(R.id.toolbar_byimage);
        setSupportActionBar(toolbarByimage);


        name = findViewById(R.id.product_name_text);
        price = findViewById(R.id.product_price_text);
        quantity = findViewById(R.id.product_quantity_text);
        addproductbutton = findViewById(R.id.add_product_byimage);
        donebutton = findViewById(R.id.DoneByImage);
        imageByimage = findViewById(R.id.image_byimage);
        wishlist_byimage = findViewById(R.id.wishlist_byimage);
        wishlist_byimage.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DB = new DatabaseImage(this);



        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        imageByimage = findViewById(R.id.image_byimage);
        //creating database
        db = this.openOrCreateDatabase("test.db", Context.MODE_PRIVATE,null);
        //creating table for storing image
        db.execSQL("create table if not exists imageTb ( image blob )");


//        imageByimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK,Uri.parse("contents://media/internal/images/media"));
//                startActivityForResult(intent,PICK_IMAGE);
//            }
//        });

        donebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = DB.getuser();
                if (cursor.getCount() == 0){
                    Toast.makeText(AddByImage.this, "No image", Toast.LENGTH_SHORT).show();
                }
                else {
                    while (cursor.moveToNext()){
                        byte[] imagebyte = cursor.getBlob(0);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imagebyte,0,imagebyte.length);
                        imageByimage.setImageBitmap(bitmap);
                    }
                }
            }
        });

        addproductbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeimage();
            }
        });


        imageByimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"title"),SELECT_IMAGE_CODE);
            }
        });

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.inventory_menu_addproduct,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.addprodudct_bytyping){
//            Intent intent = new Intent(AddByImage.this, AddByTyping.class);
//            startActivity(intent);
//            return true;
//        }
//        else if (id == R.id.addprodudct_byimage){
//
//            return true;
//        }
//        else if (id == R.id.addprodudct_byaudio){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_IMAGE_CODE){
            uri = data.getData();
            System.out.println("got uri");
            try {
                imagetostore = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
//                imageByimage.setImageBitmap(imagetostore);
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }


//            String x = getdpath(uri);
//            System.out.println(x);

//            if (DB.insertimage(x)){
//                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
//                imageByimage.setImageURI(uri);
//            }
//            else {
//                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
//            }

        }
    }

    public String getdpath(Uri uri){
        if (uri == null){
            return null;
        }
        else {
            String[] projection = {MediaStore.Images.Media.DATA};
//            Cursor cursor = managedQuery(uri,projection,null,null,null);
            Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

            if (cursor != null){
                System.out.println(cursor);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String a = cursor.getString(column_index);
//                System.out.println(a);
                return a;
            }
            else {
                return uri.getPath();
            }
        }
    }

    public void storeimage(){
        DB.insertimage(imagetostore);
    }




}