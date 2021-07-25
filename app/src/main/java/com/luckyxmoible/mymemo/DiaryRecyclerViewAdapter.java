package com.luckyxmoible.mymemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luckyxmoible.mymemo.databinding.ListItemDiaryBinding;

import java.util.List;

public class DiaryRecyclerViewAdapter extends
        RecyclerView.Adapter<DiaryRecyclerViewAdapter.ViewHolder> {

    private final List<Diary> mDiaries;

    public DiaryRecyclerViewAdapter(List<Diary> Diaries ) {
        mDiaries = Diaries;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListItemDiaryBinding binding = ListItemDiaryBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Diary diary = mDiaries.get(position);
        holder.binding.setDiary(diary);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mDiaries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Diary Diary;
        public final ListItemDiaryBinding binding;

        public ViewHolder(ListItemDiaryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
/*
        ListItemDiaryBinding binding = ListItemDiaryBinding.inflate(
                LayoutInflater.from(parent.getContext(),parent,false);
        )
        return new ViewHolder(binding);
 */