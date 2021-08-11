package com.luckyxmoible.mymemo.recyclerImageView;

import android.content.Context;

import androidx.room.Room;

import com.luckyxmoible.mymemo.DiaryDatabase;

public class ImageUriDatabaseAccessor {
    private static ImageDatabase ImageDatabaseInstance;
    private static final String IMAGE_DB_NAME="image_db";

    private ImageUriDatabaseAccessor(){}

    public static ImageDatabase getInstance(Context context){
        if(ImageDatabaseInstance == null){
            ImageDatabaseInstance = Room.databaseBuilder(context,ImageDatabase.class,IMAGE_DB_NAME).build();
        }
        return ImageDatabaseInstance;
    }
}