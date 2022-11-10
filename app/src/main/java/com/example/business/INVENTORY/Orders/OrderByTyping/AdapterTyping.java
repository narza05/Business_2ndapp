package com.example.business.INVENTORY.Orders.OrderByTyping;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.business.HRemployeebackend.employeeDAO;
import com.example.business.HRemployeebackend.employeeDatabase;
import com.example.business.INVENTORY.Orders.OrderByImage.AddByImage;
import com.example.business.R;

import java.util.List;

public class AdapterTyping extends RecyclerView.Adapter<AdapterTyping.myviewholder> {

    List<EntityTyping> list;
    Context context;

    public AdapterTyping(Context c,List<EntityTyping> list) {
        this.context = c;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_layout_typing, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.productname.setText(list.get(position).getProductname());
        holder.productquantity.setText(list.get(position).getProductquantity());
        holder.productprice.setText(list.get(position).getProductprice());
        holder.productdescription.setText(list.get(position).getProductdescription());
        holder.UpdateWishlistitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = (FragmentActivity)(context);
                FragmentManager fm = activity.getSupportFragmentManager();
                UpdateWishlistItemDialog alertDialog = new UpdateWishlistItemDialog();
                alertDialog.show(fm, "fragment_alert");
                notifyDataSetChanged();

//                DatabaseTyping databaseTyping = Room.databaseBuilder(holder.productname.getContext(), DatabaseTyping.class, "product_by_typing").allowMainThreadQueries().build();
//                DAOtyping daOtyping = databaseTyping.daotyping();
//                daOtyping.update(list.get(position).getProductname(), list.get(position).getProductdescription(), list.get(position).getProductcategory(), list.get(position).getProductprice(), list.get(position).getProductquantity());
//                notifyDataSetChanged();
//                Toast.makeText(holder.productname.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        holder.DeleteWishlistitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.productname.getContext());
                builder.setMessage("Are you sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseTyping databaseTyping = Room.databaseBuilder(holder.productname.getContext(), DatabaseTyping.class, "product_by_typing").allowMainThreadQueries().build();
                                DAOtyping daOtyping = databaseTyping.daotyping();
                                daOtyping.deleteall(list.get(position).getProductname());
                                list.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(holder.productname.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
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
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class myviewholder extends RecyclerView.ViewHolder {
        TextView productname, productquantity, productprice, productdescription;
        ImageView UpdateWishlistitem, DeleteWishlistitem;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            productname = itemView.findViewById(R.id.product_name);
            productquantity = itemView.findViewById(R.id.product_quantity);
            productprice = itemView.findViewById(R.id.product_price);
            productdescription = itemView.findViewById(R.id.product_description);
            UpdateWishlistitem = itemView.findViewById(R.id.update_wishlistitem);
            DeleteWishlistitem = itemView.findViewById(R.id.delete_wishlistitem);
        }
    }




}
