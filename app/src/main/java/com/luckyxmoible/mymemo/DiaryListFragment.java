package com.luckyxmoible.mymemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
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
    private static final String TAG = "LENGTH OF diaries in DiaryListFragment:---";
    private final ArrayList<Diary> mDiaries = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private final DiaryRecyclerViewAdapter mDiaryAdapter = new DiaryRecyclerViewAdapter(mDiaries);
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
    @SuppressLint("LongLogTag")
    public void setDiaries(List<Diary> diaries){
        mDiaries.clear();
//        mDiaryAdapter.notifyDataSetChanged();
        ArrayList<String> md = new ArrayList<>();
        for(int i = 0;i < mDiaries.size();++i){
            md.add(mDiaries.get(i).getTimeInfo());
        }
        for(Diary diary:diaries){
            if(!md.contains(diary.getTimeInfo())){
                mDiaries.add(diary);
//                mDiaryAdapter.notifyItemInserted(mDiaries.indexOf(diary));
            }
        }
//        Log.d("length of diaries in db ", diaries.size()+" ");
//        StringBuilder s = new StringBuilder();
//        for(Diary diary:diaries){
//            s.append(diary.getTimeInfo()).append(" ").append(diary.getContentSummary()).append(" ").append("\n");
//        }
//        Log.d("which are", s.toString());
//        Log.d("length of mDiaries in fragment ", mDiaries.size()+" ");
//        s = new StringBuilder();
//        for(Diary diary:mDiaries){
//            s.append(diary.getTimeInfo()).append(" ").append(diary.getContentSummary()).append("\n");
//        }
//        Log.d("which are", s.toString());
        mDiaryAdapter.notifyDataSetChanged();
    }
    @SuppressLint("LongLogTag")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        diaryViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(DiaryViewModel.class);
        //监视数据变化
        diaryViewModel.getDiaries().observe(this, diaries -> {
            if(diaries!=null){
                setDiaries(diaries);
                Log.d(TAG, "载入数据");
            }
        });

    }

}
