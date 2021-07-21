package com.luckyxmoible.mymemo;

import androidx.room.Room;
import android.content.Context;

public class DiaryDatabaseAccessor {
    private static DiaryDatabase DiaryDatabaseInstance;
    private static final String DIARY_DB_NAME="diary_db";

    private DiaryDatabaseAccessor(){}

    public static DiaryDatabase getInstance(Context context){
        if(DiaryDatabaseInstance == null){
            DiaryDatabaseInstance = Room.databaseBuilder(context,DiaryDatabase.class,DIARY_DB_NAME).build();
        }
        return DiaryDatabaseInstance;
    }
}
