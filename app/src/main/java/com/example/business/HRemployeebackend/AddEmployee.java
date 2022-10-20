package com.example.business.HRemployeebackend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.business.R;

import java.util.List;

public class AddEmployee extends AppCompatActivity {
    TextView idtext;
    TextView nametext;
    TextView emailtext;
    Button addemployeeButton;
    Button updateemployeeButton;
    Button daleteemployeeButton;
    TextView text;
    RecyclerView employeelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        idtext = findViewById(R.id.employeeId);
        nametext = findViewById(R.id.employeeName);
        emailtext = findViewById(R.id.employeeEmail);
        addemployeeButton = findViewById(R.id.addemployee_button);
        updateemployeeButton = findViewById(R.id.updateemployee_button);
        daleteemployeeButton = findViewById(R.id.deleteemployee_button);
//        text = findViewById(R.id.textdata);

        getemployeedata();

        addemployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new bgthread().start();
                String ID = idtext.getText().toString();
                String NAME = nametext.getText().toString();
                String EMAIL = emailtext.getText().toString();


                employeeDatabase employeeDatabase = Room.databaseBuilder(getApplicationContext(), employeeDatabase.class, "employee_data").allowMainThreadQueries().build();
                employeeDAO employeeDAO = employeeDatabase.employeeDAO();

                if (!ID.isEmpty() && !NAME.isEmpty() && !EMAIL.isEmpty()) {
                    Boolean check = employeeDAO.is_exists(Integer.parseInt(ID));
                    if (check == false) {
                        employeeDAO.insert(new employeesUserEntity(Integer.parseInt(ID), NAME, EMAIL));
                        idtext.setText("");
                        nametext.setText("");
                        emailtext.setText("");
                        Toast.makeText(AddEmployee.this, "Inserted", Toast.LENGTH_SHORT).show();
                        getemployeedata();
                    } else {
//                    idtext.setText("");
//                    nametext.setText("");
//                    emailtext.setText("");
                        Toast.makeText(AddEmployee.this, "Id already exists", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddEmployee.this, "Empty field", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void getemployeedata() {
        employeeDatabase employeeDatabase = Room.databaseBuilder(getApplicationContext(), employeeDatabase.class, "employee_data").allowMainThreadQueries().build();
        employeeDAO employeeDAO = employeeDatabase.employeeDAO();
        employeelist = findViewById(R.id.recyclerview_addemployee);
        employeelist.setLayoutManager(new LinearLayoutManager(this));

        List<employeesUserEntity> list = employeeDAO.getalluser();
        employeeAdapter employeeAdapter = new employeeAdapter(list);
        employeelist.setAdapter(employeeAdapter);
    }



//    class bgthread extends Thread {
//
//        @Override
//        public void run() {
//            super.run();
//            employeeDatabase employeeDatabase = Room.databaseBuilder(getApplicationContext(), employeeDatabase.class, "employee_data").build();
//            employeeDAO employeeDAO = employeeDatabase.employeeDAO();
//            employeeDAO.insert(new employeesUser(Integer.parseInt(idtext.getText().toString()), nametext.getText().toString(), emailtext.getText().toString()));
//            idtext.setText("");
//            nametext.setText("");
//            emailtext.setText("");
//        }
//    }
}