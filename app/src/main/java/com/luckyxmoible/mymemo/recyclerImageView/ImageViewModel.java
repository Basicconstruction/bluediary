package com.luckyxmoible.mymemo.recyclerImageView;

import android.app.Application;
import android.net.Uri;
import android.text.style.ImageSpan;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class ImageViewModel extends AndroidViewModel{
    public static final String TAG = "ImageListUpdate";
    private LiveData<List<ImageStorage>> imageStorages;
    public ImageViewModel(Application application){
        super(application);//application
    }
    public LiveData<List<ImageStorage>> getImageStorages(){
        if(imageStorages==null){
            imageStorages = ImageUriDatabaseAccessor.getInstance(getApplication()).imageUriDao().loadAllImageStorages();
        }
        return imageStorages;
    }
}
