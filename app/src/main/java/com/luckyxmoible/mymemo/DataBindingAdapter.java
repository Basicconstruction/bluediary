package com.luckyxmoible.mymemo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class DataBindingAdapter {
    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView view,int resource){
        view.setImageResource(resource);
    }
}
