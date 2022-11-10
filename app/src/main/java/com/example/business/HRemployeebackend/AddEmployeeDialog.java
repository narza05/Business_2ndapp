package com.example.business.HRemployeebackend;

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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.room.Room;

import com.example.business.HrFrag1_AllEmployees;
import com.example.business.R;

public class AddEmployeeDialog extends AppCompatDialogFragment {
    EditText employeeid;
    EditText employeename;
    EditText employeeemail;
    examplelistner listener;
    Context context;
    HrFrag1_AllEmployees hrFrag1_allEmployees;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_employee_dialog,null);
        context = getActivity();

        employeeDatabase employeeDatabase = Room.databaseBuilder(context.getApplicationContext(), employeeDatabase.class, "employee_data").allowMainThreadQueries().build();
        employeeDAO employeeDAO = employeeDatabase.employeeDAO();

        hrFrag1_allEmployees = new HrFrag1_AllEmployees();


        builder.setView(view)
                .setTitle("Add Employee")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ID = employeeid.getText().toString();
                        String NAME = employeename.getText().toString();
                        String EMAIL = employeeemail.getText().toString();
//                        listener.appytext(ID,NAME,EMAIL);

                        if (!ID.isEmpty() && !NAME.isEmpty() && !EMAIL.isEmpty()) {
                            Boolean check = employeeDAO.is_exists(Integer.parseInt(ID));
                            if (check == false) {
                                employeeDAO.insert(new employeesUserEntity(Integer.parseInt(ID), NAME, EMAIL));
                                Toast.makeText(getContext(), "Inserted", Toast.LENGTH_SHORT).show();
//                                getemployeedata();
//                                hrFrag1_allEmployees.getemployeedata();

                            } else {
                                Toast.makeText(getContext(), "Id already exists", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Empty field", Toast.LENGTH_SHORT).show();
                        }




                    }
                });

        employeeid = view.findViewById(R.id.emp_id);
        employeename = view.findViewById(R.id.emp_name);
        employeeemail = view.findViewById(R.id.emp_email);
        return builder.create();
    }


    @Override
    public void onResume() {
        super.onResume();
        try {
            listener = (examplelistner) context;
            System.out.println("listner");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        try {
//            listner = (listner) context;
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

    public interface examplelistner{
        void appytext(String id,String name, String email);
    }
}
