package com.example.business.INVENTORY.Orders.OrderByTyping;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.room.Room;

import com.example.business.HRemployeebackend.AddEmployeeDialog;
import com.example.business.R;

import java.util.List;

public class UpdateWishlistItemDialog extends AppCompatDialogFragment {
    EditText updateproductname, updateproductcategory, updateproductprice, updateproductquantity, updateproductdescription;
    AddEmployeeDialog.examplelistner listener;
    Context context;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.update_wishlistitem_dialog,null);

        AddByTyping addByTyping = new AddByTyping();
        context = getActivity();
        DatabaseTyping databaseTyping = Room.databaseBuilder(context.getApplicationContext(),DatabaseTyping.class,"product_by_typing").allowMainThreadQueries().build();
        DAOtyping daOtyping = databaseTyping.daotyping();

        builder.setView(view)
                .setTitle("Update wishlist item")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String productname = updateproductname.getText().toString();
                        String productdescription = updateproductdescription.getText().toString();
                        String productcategory = updateproductcategory.getText().toString();
                        String productprice = updateproductprice.getText().toString() + " Rupee";
                        String productquantity = updateproductquantity.getText().toString();

                        if (productname.isEmpty() && productquantity.isEmpty()){
                            System.out.println("");
                        }

                        else{
                            daOtyping.update(productname, productdescription, productcategory, productprice, productquantity);
                            Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        updateproductname = view.findViewById(R.id.updateitem_name);
        updateproductcategory = view.findViewById(R.id.updateitem_category);
        updateproductprice = view.findViewById(R.id.updateitem_price);
        updateproductquantity = view.findViewById(R.id.updateitem_quantity);
        updateproductdescription = view.findViewById(R.id.updateitem_description);
        return builder.create();



    }
}
