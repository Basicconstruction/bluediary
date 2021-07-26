package com.luckyxmoible.mymemo;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
/*
* @wcx DAO是用于定义Room Database 交互的类，其中包含了用于插入，删除，更新和查询数据库的方法
* */
@Dao
public interface DiaryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertDiary(List<Diary> diaries);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertDiary(Diary diary);

    @Update
    public void updateDiary(Diary... diary);

    @Update
    public void updateDiary(Diary diary);

    @Delete
    public void deleteDiary(Diary diary);

    @Delete
    public void deleteTwoDiaries(Diary diary1,Diary diary2);

    @Query("DELETE FROM diary")
    public void deleteALLDiaries();

    @Query("SELECT * FROM diary ORDER BY date DESC")// ORDER BY textContent DESC
    public LiveData<List<Diary>> loadAllDiaries();

    @Query("SELECT * FROM diary WHERE textContent = :textContent")
    public Diary loadDiaryByTextContent(String textContent);

    @Query("SELECT * FROM Diary WHERE textContent IN(:textContents)")
    public List<Diary> findByTextContents(String[] textContents);

    @Query("SELECT * FROM diary")
    public LiveData<List<Diary>> monitorAllDiaries();
}
