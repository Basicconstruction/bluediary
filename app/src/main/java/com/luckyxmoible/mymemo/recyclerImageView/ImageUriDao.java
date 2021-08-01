package com.luckyxmoible.mymemo.recyclerImageView;


import android.net.Uri;

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
public interface ImageUriDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertImageStorage(List<ImageStorage> imageStorages);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertImageStorage(ImageStorage imageStorage);

    @Update
    public void updateImageStorage(ImageStorage... imageStorages);

    @Update
    public void updateImageStorage(ImageStorage imageStorage);

    @Delete
    public void deleteImageStorage(ImageStorage imageStorage);

    @Query("DELETE FROM imageStorage")
    public void deleteALLImageStorages();

    @Query("SELECT * FROM imagestorage ORDER BY uris DESC")// ORDER BY textContent DESC
    public LiveData<List<ImageStorage>> loadAllImageStorages();

    @Query("SELECT * FROM imagestorage")
    public LiveData<List<ImageStorage>> monitorAllImageStorages();
}
