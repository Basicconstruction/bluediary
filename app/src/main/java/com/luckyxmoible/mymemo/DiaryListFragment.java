package com.luckyxmoible.mymemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class DiaryListFragment extends Fragment {
    private ArrayList<Diary> mDiaries = new ArrayList<Diary>();
    public DiaryListFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
}
