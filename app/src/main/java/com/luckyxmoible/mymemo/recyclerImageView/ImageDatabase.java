package com.luckyxmoible.mymemo.recyclerImageView;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.luckyxmoible.mymemo.DiaryTypeConverters;

@Database(entities={ImageStorage.class},version=1,exportSchema = false)
@TypeConverters({DiaryTypeConverters.class})
public abstract class ImageDatabase extends RoomDatabase {
    public abstract ImageUriDao imageUriDao();
}
