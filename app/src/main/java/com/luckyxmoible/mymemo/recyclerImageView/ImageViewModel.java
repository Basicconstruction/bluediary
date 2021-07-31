package com.luckyxmoible.mymemo.recyclerImageView;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class ImageViewModel extends AndroidViewModel{
    public static final String TAG = "ImageListUpdate";
    private LiveData<List<Uri>> uris;
    public ImageViewModel(Application application){
        super(application);//application
    }
    public LiveData<List<Uri>> getUris(){
        return uris;
    }
}
