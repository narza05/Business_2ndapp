package com.example.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.business.HRemployeebackend.AddEmployee;
import com.example.business.HRemployeebackend.employeeAdapter;
import com.example.business.HRemployeebackend.employeeDAO;
import com.example.business.HRemployeebackend.employeeDatabase;
import com.example.business.HRemployeebackend.employeesUserEntity;

import java.util.List;

public class HrFrag1_AllEmployees extends Fragment {
    ImageView addemployee;
    RecyclerView allemployeelist;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.hr_employeefragment,container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addemployee = view.findViewById(R.id.add_employee);

        allemployeelist = view.findViewById(R.id.recyclerview_allemployee);
        allemployeelist.setLayoutManager(new LinearLayoutManager(getContext()));

        getemployeedata();



        addemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEmployee.class);
                startActivity(intent);
            }
        });
    }

    public void getemployeedata() {
        employeeDatabase employeeDatabase = Room.databaseBuilder(getContext(), employeeDatabase.class, "employee_data").allowMainThreadQueries().build();
        employeeDAO employeeDAO = employeeDatabase.employeeDAO();
        List<employeesUserEntity> list = employeeDAO.getalluser();
        employeeAdapter employeeAdapter = new employeeAdapter(list);
        allemployeelist.setAdapter(employeeAdapter);
    }

}
