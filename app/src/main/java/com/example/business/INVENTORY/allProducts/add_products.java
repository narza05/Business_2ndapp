//package com.example.business.INVENTORY.allProducts;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import android.Manifest;
//import android.content.ContentValues;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.business.R;
//
//public class add_products extends AppCompatActivity {
//       ImageView productimage;
//       TextView productname,productdescription,productcategory,productprice,productquantity;
//       Button addproduct;
//       int SELECT_IAMGE_CODE = 1;
//
//       private static final int CAMERA_REQUEST_CODE = 200;
//       private static final int STORAGE_REQUEST_CODE = 300;
//
//       private static final int IAMGE_PICK_GELLERY_CODE = 400;
//       private static final int IAMGE_PICK_CAMERA_CODE = 500;
//
//       private String[] cameraPermissions;
//       private String[] storagePermissions;
//
//       private Uri image_uri;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_products);
////        productimage = findViewById(R.id.product_image);
//        productname = findViewById(R.id.product_name_text);
//        productdescription = findViewById(R.id.product_description_text);
//        productcategory = findViewById(R.id.product_category_text);
//        productprice = findViewById(R.id.product_price_text);
//        productquantity = findViewById(R.id.product_quantity_text);
//        addproduct = findViewById(R.id.add_product_button);
//
//        addproduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent,"title"),SELECT_IAMGE_CODE);
//            }
//        });
//
//
//        productimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showImageDialog();
//            }
//        });
//
//    }
//
//    private void showImageDialog() {
//        String[] options = {"Camera","Gellery"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Pick Image").setItems(options, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (which == 0){
//                    if (checkcamerapermission()){
//                        pickFromCamera();
//                    }
//                    else {
//                        requestcamerapermission();
//                    }
//                }
//                else {
//                    if (checkstoragepermission()){
//                        pickFromGellery();
//                    }
//                    else {
//                        requeststoragepermission();
//                    }
//                }
//            }
//        });
//
//    }
//
//    private void pickFromGellery(){
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("images/*");
//        startActivityForResult(intent,IAMGE_PICK_GELLERY_CODE);
//    }
//
//    private void pickFromCamera(){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(MediaStore.Images.Media.TITLE,"Temp_Image_Title");
//        contentValues.put(MediaStore.Images.Media.DESCRIPTION,"Temp_Image_Description");
//        image_uri = getContentResolver().insertProduct(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
//        startActivityForResult(intent, IAMGE_PICK_CAMERA_CODE);
//    }
//
//    private boolean checkstoragepermission(){
//        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
//        return result;
//    }
//
//    private void requeststoragepermission(){
//        ActivityCompat.requestPermissions(this,storagePermissions,STORAGE_REQUEST_CODE);
//    }
//
//    private boolean checkcamerapermission(){
//        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
//
//        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
//        return result && result1;
//    }
//
//    private void requestcamerapermission(){
//        ActivityCompat.requestPermissions(this,cameraPermissions,CAMERA_REQUEST_CODE);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==1){
//            Uri uri = data.getData();
//            productimage.setImageURI(uri);
//        }
//    }
//}