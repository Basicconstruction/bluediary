package com.luckyxmoible.mymemo;

import android.annotation.SuppressLint;
import android.location.Location;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;
import java.util.Vector;

public class DiaryTypeConverters {
    private static final String TAG = "HELLO IN TYPECONVENTER: ";

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

    @SuppressLint("LongLogTag")
    @TypeConverter
    public static String UriToString(@Nullable Vector<Uri> uri){
        String s_uri = new String("");
        String hasNull = "--No";
        if(uri==null||uri.size() == 0||uri.elementAt(0)==null){
            Log.d(TAG, "storaged "+s_uri+hasNull);
            Log.d(TAG,s_uri.length()+"");
            //Log.d(TAG,StringToUri(s_uri)[0].toString());
            Log.d(TAG,s_uri.length()+"");
            return "";
        }
        for(Uri uri_0 : uri){
            if(uri_0==null){
                hasNull = "---yes";
                continue;
            }
            Log.d(TAG, uri_0.toString());
            s_uri = s_uri + uri_0.toString()+"&&&";
        }
        Log.d(TAG, "storaged "+s_uri+hasNull);
        Log.d(TAG,s_uri.length()+"");
        Log.d(TAG,StringToUri(s_uri).elementAt(0).toString());
        Log.d(TAG,s_uri.length()+"");
        return s_uri;
    }
    @SuppressLint("LongLogTag")
    @TypeConverter
    public static Vector<Uri> StringToUri(String s_uri){
        //return new Uri[0];
        if(s_uri.equals("")){
            Log.d(TAG, "载入: no useful uri");
            return new Vector<Uri>(0);
        }
        int fi = 0;
        int ct = 0;
        while(s_uri.indexOf("&&&",fi)!=-1&&fi<s_uri.length()){
            fi = s_uri.indexOf("&&&",fi);
            fi+=3;
            ct += 1;
        }
        Vector<Uri> uri = new Vector<Uri>();
        fi = 0;
        for(int j = ct;j>0;j--){
            uri.add(Uri.parse(s_uri.substring(fi, s_uri.indexOf("&&&", fi))));
            fi = s_uri.indexOf("&&&",fi) + 3;
        }
        Log.d(TAG, "载入: some useful uri"+uri.size());
        return uri;
    }
}
