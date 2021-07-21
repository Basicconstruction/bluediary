package com.luckyxmoible.mymemo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import androidx.annotation.NonNull;
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
    public Diary(String textContent){
        this.textContent = textContent;
    }
    @Ignore
    public Diary(){
        this.textContent = "今天也是美好的一天！";
    }
}
