package com.example.business.HRchatbackend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.business.R;

import java.util.List;

public class chatAdapter extends RecyclerView.Adapter<chatAdapter.chatviewholder>{


    List<chatUserEntity> chats;

    public chatAdapter(List<chatUserEntity> chats) {
        this.chats = chats;
    }

    @NonNull
    @Override
    public chatviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_leftchat_data_layout,parent,false);
        return new chatviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chatviewholder holder, int position) {
            holder.chatleft.setText(String.valueOf(chats.get(position).getChatText()));
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    class chatviewholder extends RecyclerView.ViewHolder {
        TextView chatleft;
        public chatviewholder(@NonNull View itemView) {
            super(itemView);
            chatleft = itemView.findViewById(R.id.leftchatmsgtext);
        }
    }

}
