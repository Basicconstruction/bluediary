package com.luckyxmoible.mymemo;

import android.annotation.SuppressLint;
import android.app.Application;
import android.location.Location;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.Date;
import java.util.List;


public class DiaryViewModel extends AndroidViewModel {
    public static final String TAG = "DiaryUpdate";
    private LiveData<List<Diary>> diaries;
    public DiaryViewModel(Application application){
        super(application);//application
    }
    public LiveData<List<Diary>> getDiaries(){
        if(diaries==null){
            diaries = DiaryDatabaseAccessor
                    .getInstance(getApplication())
                    .diaryDAO().loadAllDiaries();
        }

        return diaries;
    }


}
