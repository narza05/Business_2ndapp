package com.example.business.HRemployeebackend;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.business.R;

import java.util.List;

public class employeeAdapter extends RecyclerView.Adapter<employeeAdapter.employeeviewholder> {
    List<employeesUserEntity> Users;

    public employeeAdapter(List<employeesUserEntity> users) {
        Users = users;
    }

    @NonNull
    @Override
    public employeeviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_employee_data_layout,parent,false);
        return new employeeviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull employeeviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.id.setText(String.valueOf(Users.get(position).getEmployeeId()) + ":");
//        holder.id.setText(String.valueOf(Users.get(position)));
        holder.name.setText(String.valueOf(Users.get(position).getEmployeeName()));
        holder.email.setText(String.valueOf(Users.get(position).getEmployeeEmail()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employeeDatabase employeeDatabase = Room.databaseBuilder(holder.id.getContext(), employeeDatabase.class, "employee_data").allowMainThreadQueries().build();
                employeeDAO employeeDAO = employeeDatabase.employeeDAO();
                employeeDAO.delete(Integer.parseInt(String.valueOf(Users.get(position).getEmployeeId())));
                Users.remove(position);
                notifyDataSetChanged();
                Toast.makeText(holder.id.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Users.size();
    }

    class employeeviewholder extends RecyclerView.ViewHolder {
        TextView id,name,email;
        ImageView delete;
        public employeeviewholder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idtext);
            name = itemView.findViewById(R.id.nametext);
            email = itemView.findViewById(R.id.emailtext);
            delete = itemView.findViewById(R.id.deleteicon);
        }
    }
}
