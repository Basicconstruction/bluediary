package com.luckyxmoible.mymemo;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DiaryListFragment extends Fragment {
    private ArrayList<Diary> mDiaries = new ArrayList<Diary>();
    private RecyclerView mRecyclerView;
    private DiaryRecyclerViewAdapter mDiaryAdapter = new DiaryRecyclerViewAdapter(mDiaries);
    public DiaryListFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       View view = inflater.inflate(R.layout.fragment_diary_list,container,false);
       mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
       return view;
    }
    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        Context context = view.getContext();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(mDiaryAdapter);
    }
    public void setDiaries(List<Diary> diaries){
        for(Diary diary:diaries){
            if(!mDiaries.contains(diary)){
                mDiaries.add(diary);
                mDiaryAdapter.notifyItemInserted(mDiaries.indexOf(diary));
            }
        }
    }
}
