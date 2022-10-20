package com.example.business.HRchatbackend;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class chatUserEntity {
//    @PrimaryKey
//    public  int chatId;
    @PrimaryKey(autoGenerate = true)
    public int chatId;

    @ColumnInfo(name = "chats")
    public String chatText;

    public chatUserEntity(String chatText) {
//        this.chatId = chatId;
        this.chatText = chatText;
    }

//    public int getChatId() {
//        return chatId;
//    }
//
//    public void setChatId(int chatId) {
//        this.chatId = chatId;
//    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }
}
