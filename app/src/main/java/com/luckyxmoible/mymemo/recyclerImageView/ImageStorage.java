package com.luckyxmoible.mymemo.recyclerImageView;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Vector;

@Entity
public class ImageStorage {
    @NonNull
    @PrimaryKey
    public int key = 1;
    public Vector<Uri> uris;

    public ImageStorage(Vector<Uri> uris){
        this.uris = uris;
    }
    @Ignore
    public ImageStorage(){
        this.uris = new Vector<Uri>();
    }
}
