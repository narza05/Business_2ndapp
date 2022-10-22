package com.example.business.INVENTORY.Orders.OrderByTyping;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.business.R;

import java.util.List;

public class AdapterTyping extends RecyclerView.Adapter<AdapterTyping.myviewholder>{

    List<EntityTyping> list;

    public AdapterTyping(List<EntityTyping> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_layout_typing,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.productname.setText(list.get(position).getProductname());
        holder.productquantity.setText(list.get(position).getProductquantity());
        holder.productprice.setText(list.get(position).getProductprice());
        holder.productdescription.setText(list.get(position).getProductdescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }






    class myviewholder extends RecyclerView.ViewHolder {
        TextView productname,productquantity,productprice,productdescription;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            productname = itemView.findViewById(R.id.product_name);
            productquantity = itemView.findViewById(R.id.product_quantity);
            productprice = itemView.findViewById(R.id.product_price);
            productdescription = itemView.findViewById(R.id.product_description);
        }
    }
}
