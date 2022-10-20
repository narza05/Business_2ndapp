package com.example.business;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.business.HRchatbackend.chatAdapter;
import com.example.business.HRchatbackend.chatDAO;
import com.example.business.HRchatbackend.chatDatabase;
import com.example.business.HRchatbackend.chatUserEntity;
import com.example.business.HRemployeebackend.employeeDAO;

import java.util.List;

public class HrFrag3_chat extends Fragment {
    TextView msg;
    ImageButton sendbutton;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.hr_chatfragment,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        msg = view.findViewById(R.id.chattext);
        sendbutton = view.findViewById(R.id.sendchattext);
        recyclerView = view.findViewById(R.id.recyclerview_chat);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getchat();

        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msgtext = msg.getText().toString();

                chatDatabase chatDatabase = Room.databaseBuilder(getContext(), chatDatabase.class,"chat_data").allowMainThreadQueries().build();
                chatDAO chatDAO = chatDatabase.chatDAO();

                if (!msgtext.isEmpty()){
                    chatDAO.insert(new chatUserEntity(msgtext));
                    msg.setText("");
//                    id++;
                    getchat();
                    Toast.makeText(getContext(), "Sent", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "Field empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getchat(){
        chatDatabase chatDatabase = Room.databaseBuilder(getContext(), chatDatabase.class,"chat_data").allowMainThreadQueries().build();
        chatDAO chatDAO = chatDatabase.chatDAO();
        List<chatUserEntity> msglist = chatDAO.getchat();
        chatAdapter chatAdapter = new chatAdapter(msglist);
        recyclerView.setAdapter(chatAdapter);
    }

}
