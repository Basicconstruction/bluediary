package com.luckyxmoible.mymemo;

/*
* Room Database 是底层SQLite连接的主要访问点。
* 它还必须包含一个抽象方法，该方法会返回数据访问对象（DAO）以及数据库将包含的实体列表。
* */

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities={Diary.class},version=1)
public abstract class DiaryDatabase extends RoomDatabase {
    public abstract DiaryDAO diaryDAO();
}
