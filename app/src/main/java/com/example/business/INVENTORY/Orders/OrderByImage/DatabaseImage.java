package com.example.business.INVENTORY.Orders.OrderByImage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DatabaseImage extends SQLiteOpenHelper {
    Context context;
    ByteArrayOutputStream byteArrayOutputStream;
    byte[] imageinbyte;




    public DatabaseImage(Context context) {
        super(context, "imageDB.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE images(id integer primary key autoincrement, img blob not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS images");
    }



    public  void insertimage(Bitmap imagebitmap){
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imagetostorebitmap = imagebitmap;

        byteArrayOutputStream = new ByteArrayOutputStream();
        imagetostorebitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        imageinbyte = byteArrayOutputStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put("img",imageinbyte);

        long check = db.insert("images",null,contentValues);
        if (check != -1){
//            Toast.makeText(context.getApplicationContext(), "Done insertion", Toast.LENGTH_SHORT).show();
            System.out.println("Done insertion");
        }
        else {
            System.out.println("failed insertion");
//            Toast.makeText(context.getApplicationContext(), "failed insertion", Toast.LENGTH_SHORT).show();
        }











//        try {
//            FileInputStream fs = new FileInputStream(a);
//            byte[] imagebyte = new byte[fs.available()];
//            fs.read(imagebyte);
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("img",imagebyte);
//            db.insert("images",null,contentValues);
//            fs.close();
//            return true;
//
//        }
//
//        catch (IOException e) {
//            e.printStackTrace();
//            return  false;
//        }
    }

    public Cursor getuser(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM images", null);
        return cursor;

    }


}
