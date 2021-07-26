package com.luckyxmoible.mymemo;

import android.location.Location;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;

public class DiaryTypeConverters {
    @TypeConverter
    public static Date dateFromTimestamp(Long value){
        return value == null?null:new Date(value);
    }
    @TypeConverter
    public static Long dateToTimestamp(Date date){
        return date == null?null:date.getTime();
    }
    @TypeConverter
    public static String locationToString(Location location){
        return "河南-开封";
    }
    @TypeConverter
    public static Location StringToLocation(String str){
        return new Location("China");
    }
}
