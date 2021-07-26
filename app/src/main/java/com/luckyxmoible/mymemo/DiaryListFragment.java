package com.luckyxmoible.mymemo;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DiaryListFragment extends Fragment {
    private ArrayList<Diary> mDiaries = new ArrayList<Diary>();
    private RecyclerView mRecyclerView;
    private DiaryRecyclerViewAdapter mDiaryAdapter = new DiaryRecyclerViewAdapter(mDiaries);
    protected DiaryViewModel diaryViewModel;
    public DiaryListFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       View view = inflater.inflate(R.layout.fragment_diary_list,container,false);
       mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
       return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        Context context = view.getContext();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(mDiaryAdapter);
    }
    public void setDiaries(List<Diary> diaries){
        mDiaries.clear();
        mDiaryAdapter.notifyDataSetChanged();
        for(Diary diary:diaries){
            if(!mDiaries.contains(diary)){
                mDiaries.add(diary);
                mDiaryAdapter.notifyItemInserted(mDiaries.indexOf(diary));
            }
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        diaryViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(DiaryViewModel.class);
        diaryViewModel.getDiaries().observe(this,new Observer<List<Diary>>(){
            @Override
            public void onChanged(@Nullable List<Diary> diaries){
                if(diaries!=null){
                    setDiaries(diaries);
                }
            }
        });

    }

}
