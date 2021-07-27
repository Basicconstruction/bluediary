package com.luckyxmoible.mymemo;

import android.annotation.SuppressLint;
import android.location.Location;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import androidx.annotation.NonNull;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 主要数据类，用作存储数据，app主要构建对象，依赖于这个对象进行与数据库，ui等的交互。
 * 一个Diary即是一个日记对象。
 * 对象数组存储到数据库，前端ui从数据库中获取到对象数组并展示出来。
 * */
@Entity
public class Diary {
    @NonNull
    @PrimaryKey
    public String textContent;
    public String title;
    public Date date;
    public Location location;
    public String imagePath;
    public int imageRes;
    @Ignore
    public URL imageUrl;



    public Diary(String title,String textContent){
        this.title = title;
        this.textContent = textContent;
        this.date = new Date();
        this.location = new Location("China");
        this.imagePath = "/";
        this.imageRes = 0;
    }
    @Ignore
    public Diary(String textContent,Date date){
        this.textContent = textContent;
        //this.date = date;
    }
    @Ignore
    public Diary(){
        this.textContent = "今天也是美好的一天！";
        //this.date = new Date();
    }
    public String getLocation(){
        return "河南-开封";
    }
    public String getTimeInfo(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(this.date);
    }
    public static String getDateTime(){
        Date date = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("/dd.MM/yyyy");
        String dateStr = sdf.format(date);
        String ds = date.toString().substring(0,3);
        switch (ds){
            case "Mon":
                ds = "星期一";
                break;
            case "Tue":
                ds = "星期二";
                break;
            case "Wed":
                ds = "星期三";
                break;
            case "Thu":
                ds = "星期四";
                break;
            case "Fri":
                ds = "星期五";
                break;
            case "Sat":
                ds = "星期六";
                break;
            case "Sun":
                ds = "星期日";
                break;
            default:
                ds = "error";
        }
        return ds+dateStr;
    }
}
/*
如果基本的控件也有clickListener,在控件上添加clickListener，主展示页面展示时间，标题和图片作为摘要。点击后进入一个 activity.
展示详细内容。
属性： 日期，地址，文本，标题，图片
* */
