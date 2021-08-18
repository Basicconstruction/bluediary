package com.luckyxmoible.mymemo;

import android.annotation.SuppressLint;
import android.location.Location;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/*
 * 主要数据类，用作存储数据，app主要构建对象，依赖于这个对象进行与数据库，ui等的交互。
 * 一个Diary即是一个日记对象。
 * 对象数组存储到数据库，前端ui从数据库中获取到对象数组并展示出来。
 * */
@Entity
public class Diary {
    @NonNull
    @PrimaryKey
    public Date date;
    public String textContent;
    public String title;
    public String password;
    public String location;
    public Vector<Uri> uris;
    public boolean isLocked = false;

    @Ignore
    public Diary(String title,String textContent){
        this.title = title;
        this.textContent = textContent;
        this.date = new Date();
        this.uris = new Vector<>(0);
        this.location = "";
        this.isLocked = false;
    }

    public Diary(String title,String textContent,Vector<Uri> uris,String location,boolean isLocked,String password){
        this.title = title;
        this.textContent = textContent;
        this.date = new Date();
        this.uris = uris;
        this.location = location;
        this.isLocked = isLocked;
        this.password = password;
    }
    @Ignore
    public Diary(String textContent, @NonNull Date date){
        this.textContent = textContent;
        this.date = date;
        this.title = "";
        this.uris = new Vector<>(0);
        this.location = "";
        this.isLocked = false;
        this.password="";
    }
    @Ignore
    public Diary(){
        this.title = "good day";
        this.textContent = "今天也是美好的一天！";
        this.date = new Date();
        this.location = "";
        this.isLocked = false;;
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
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("/MM.dd/yyyy");
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
    public String getContentSummary(){
        if(this.textContent.length()<10){
            return this.textContent;
        }else{
            return this.textContent.substring(0,8)+"...";
        }
    }
    public String getUriLength(){
        return this.uris.size()+"";
    }
    public Uri getPrimeUri(){
        if(this.uris!= null&&this.uris.elementAt(0)!=null){
            return this.uris.elementAt(0);
        }else{
            return null;
        }

    }

}

/*
如果基本的控件也有clickListener,在控件上添加clickListener，主展示页面展示时间，标题和图片作为摘要。点击后进入一个 activity.
展示详细内容。---finish
属性： 日期，地址，文本，标题，图片
下一步:
    添加或优化编辑，删除
    添加密码组件
    添加图片   --？找到文件复制到res目录，获取引用。
扩展:
    删除选择列表构造模式建立
    html in android--多态化UI组件，设计复杂浏览模式。
* */
